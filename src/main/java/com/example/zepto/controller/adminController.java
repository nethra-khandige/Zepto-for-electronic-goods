package com.example.zepto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.zepto.dto.shopDTO;
import com.example.zepto.model.shop;
import com.example.zepto.repository.shopRepository;
import com.example.zepto.service.shopService;

@Controller
public class adminController {
	
	@Autowired
	shopRepository shopRepo;
	@Autowired
	shopService shopService;
	
	@GetMapping("/admin")
	public String adminHome(Model model) {
		model.addAttribute("shops", shopService.getAllShop());
		return "shops";
	}
	
	@GetMapping("/admin/shop/add")
	public String shopAddGet(Model model) {
		model.addAttribute("shop",new shopDTO());
		return "shopAdd";
	}
	
	@PostMapping("/admin/shop/add")
	public String storeAddPost(@ModelAttribute("shopDTO") shop shopDTO) {
		shop newShop = new shop();
		newShop.setId(shopDTO.getId());
		newShop.setShopName(shopDTO.getShopName());
		newShop.setLocation(shopDTO.getLocation());
		shopService.addShop(newShop);
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/shop/delete/{id}")
	public String deleteShop(@PathVariable Long id) {
		shopService.removeShopByID(id);
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/shop/update/{id}")
	public String updateShop(@PathVariable Long id, Model model) {
		Optional <shop> shop=shopService.getShopById(id);
		if(shop.isPresent()) {
			model.addAttribute("shop", shop.get());
			return "shopAdd";
			
		}
		else {
			return "404";
		}
	}
}
