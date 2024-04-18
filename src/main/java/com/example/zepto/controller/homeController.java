package com.example.zepto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.zepto.global.globalData;
import com.example.zepto.service.productService;
import com.example.zepto.service.shopService;

@Controller
public class homeController {
	
	@Autowired
	shopService ShopService;
	@Autowired 
	productService ProductService;
	
	@GetMapping({"/","/home"})
	public String home(Model model) {
		model.addAttribute("cartCount", globalData.cart.size());
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("shops", ShopService.getAllShop());
		model.addAttribute("products", ProductService.getAllProduct());
		model.addAttribute("cartCount", globalData.cart.size());
		return "home";
	}
	@GetMapping("/shop/store/{id}")
	public String shopBystore(Model model, @PathVariable long id) {
		model.addAttribute("shops", ShopService.getAllShop());
		model.addAttribute("products", ProductService.getAllProductsByShopId(id));
		model.addAttribute("cartCount", globalData.cart.size());
		return "home";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable long id) {
		model.addAttribute("product", ProductService.getProductById(id).get());
		model.addAttribute("cartCount", globalData.cart.size());
		return "viewProduct";
	}

	
}
 