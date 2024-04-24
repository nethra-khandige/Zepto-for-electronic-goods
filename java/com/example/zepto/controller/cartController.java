package com.example.zepto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.zepto.global.OrderManager;
import com.example.zepto.global.globalData;
import com.example.zepto.model.product;
import com.example.zepto.service.deliveryDriverService;
import com.example.zepto.service.productService;

@Controller
public class cartController {

	@Autowired
	productService productService;
	
	@Autowired
	deliveryDriverService ddService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable long id) {
		globalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}
	
	@GetMapping("/cart")
	public String cartGet(Model model) {
		model.addAttribute("cartCount", globalData.cart.size());
		model.addAttribute("total", globalData.cart.stream().mapToDouble(product::getPrice).sum());
		model.addAttribute("cart", globalData.cart);
		return "cart";
		
	}
	
	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index) {
		globalData.cart.remove(index);
		return"redirect:/cart";
	}
	@GetMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("total", globalData.cart.stream().mapToDouble(product::getPrice).sum());
		return "checkout";
	}
	@PostMapping("/payNow")
	public String payout(Model model) {
	    // Update order status
	    OrderManager.getInstance().setStatus("Payment Confirmed");
	    ddService.assignDeliveryDriver();  
	    return "paymentSuccess";  // Or appropriate view for payment success
	}
}
