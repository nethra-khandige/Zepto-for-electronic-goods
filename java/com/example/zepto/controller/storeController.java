package com.example.zepto.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.zepto.dto.productDTO;
import com.example.zepto.model.product;
import com.example.zepto.model.shop;
import com.example.zepto.repository.shopRepository;
import com.example.zepto.service.productService;
import com.example.zepto.service.shopService;

@Controller
public class storeController {
	public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	
	@Autowired
	productService productService;
	
	@Autowired
	shopService shopService;
	
	@Autowired 
	shopRepository shopRepository;
	
	@GetMapping("/store")
	public String storeHome() {
		return "store";
	}
	
	@GetMapping("/store/products")
	public String products(Model model) {
		model.addAttribute("products",productService.getAllProduct());
		return "products";
	}
	
	@GetMapping("/store/products/add")
	public String productAddGet(Model model) {
		model.addAttribute("productDTO",new productDTO());
		return "productsAdd";
	}
	
	@PostMapping("/store/products/add")
	public String productAddPost(@ModelAttribute("productDTO") productDTO productDTO,
								@RequestParam("productImage")MultipartFile file,
								@RequestParam("imgName")String imgName) throws IOException {
		product product=new product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
//		product.setShop(shopService.getShopById(productDTO.get);
//		product.setShop(shopService.getShopById(productDTO.getShopId()).get());
		Optional <shop> optionalShop=shopService.getShopById(productDTO.getShopId());
	    if (optionalShop.isPresent()) {
	        product.setShop(optionalShop.get());
	    } 
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID= file.getOriginalFilename();
			Path fileNameAndPath= Paths.get(uploadDir,imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		}else {
			imageUUID=imgName;
		}
//	    Set<shop> shops = new HashSet<>();
//	    shops.add(shop);
//	    product.setShops(shops);
	    
	    product.setImageName(imageUUID);
	    productService.addProduct(product);
		
		
		
		return "redirect:/store/products";
		
	}
	
	@GetMapping("/store/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.removeProductById(id);
		return "redirect:/store/products";
	}
 
	@GetMapping("/store/product/update/{id}")
	public String updateProduct(@PathVariable Long id, Model model) {
		product Product =productService.getProductById(id).get();
		productDTO ProductDTO = new productDTO();
		ProductDTO.setId(Product.getId());
		ProductDTO.setDescription(Product.getDescription());
		ProductDTO.setName(Product.getName());
//	    Long firstShopId = null;
//	    if (!Product.getShops().isEmpty()) {
//	        firstShopId = Product.getShops().iterator().next().getId();
//	    }
	    ProductDTO.setShopId(Product.getShop().getId());
	    ProductDTO.setImageName(Product.getImageName());
	    ProductDTO.setPrice(Product.getPrice());
	    model.addAttribute("shops", shopService.getAllShop());
	    model.addAttribute("productDTO", ProductDTO);
		return "productsAdd";
	}

	
}
	
