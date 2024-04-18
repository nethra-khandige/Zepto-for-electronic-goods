package com.example.zepto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.zepto.global.OrderManager;
import com.example.zepto.service.deliveryDriverService;

@Controller
public class deliveryDriverController {

	@Autowired
	deliveryDriverService ddService;
	
//	@GetMapping("/deliveryDriver")
//	public String ddHome() {
//		return "orderStatusUpdate";
//	}
	
	@GetMapping("/deliveryDriver")
	public String getOrderStatus(Model model) {
	    String orderStatus = OrderManager.getInstance().getStatus();  // Get status from OrderManager
	    model.addAttribute("orderStatus", orderStatus);
	    return "orderStatusUpdate";
	}

	    @GetMapping("/updateStatus/{status}")
	    public String updateStatus(@PathVariable String status) {
	        OrderManager.getInstance().setStatus(status);  // Update status using singleton
	        // Additional logic to update order data in your database (replace with your implementation)

	        // Redirect to a success page or display a confirmation message
	        return "redirect:/orderStatusUpdate";
	    }
    
    @GetMapping("/track-order")
    public String trackOrder(Model model) {
        OrderManager orderManager = OrderManager.getInstance();  // Get OrderManager instance
        String orderStatus = orderManager.getStatus();  // Get status from OrderManager
        model.addAttribute("orderStatus", orderStatus);
        return "trackOrder";  // Name of your track order view template
    }
}
