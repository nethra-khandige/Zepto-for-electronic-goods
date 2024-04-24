package com.example.zepto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.zepto.global.OrderManager;
import com.example.zepto.service.deliveryDriverService;

@Controller
public class deliveryDriverController {

	@Autowired
	deliveryDriverService ddService;
	
	@GetMapping("/driver")
	public String ddHome() {
		return "deliveryDriverLogin";
	}
	@PostMapping("/driver/login")
	public String login(@RequestParam long driverId, @RequestParam String name, Model model) {
		if(ddService.isValidDriver(driverId, name)) {
	        model.addAttribute("orderManager", OrderManager.getInstance()); 
			return "orderStatusUpdate";
		}
		return "deliveryDriverLogin";
	}
	
	@GetMapping("/deliveryDriver")
	public String getOrderStatus(Model model) {
	    String orderStatus = OrderManager.getInstance().getStatus();  // Get status from OrderManager
	    model.addAttribute("orderStatus", orderStatus);
	    return "orderStatusUpdate";
	}
    @GetMapping("/updateStatus/orderPickedUp")
    public String updateStatusPicked() {
        OrderManager.getInstance().setStatus("Order Picked up");  // Update status using singleton
        // Additional logic to update order data in your database (replace with your implementation)

        // Redirect to a success page or display a confirmation message
        return "redirect:/";
    }
    @GetMapping("/updateStatus/onTheWay")
    public String updateStatusOn() {
        OrderManager.getInstance().setStatus("Order On the way");  // Update status using singleton
        // Additional logic to update order data in your database (replace with your implementation)

        // Redirect to a success page or display a confirmation message
        return "redirect:/";
    }
    @PostMapping("/updateStatus/delivered")
    public String updateStatusDelivered(@RequestParam long driverId) {
        OrderManager.getInstance().setStatus("Order Delivered");  // Update status using singleton
        ddService.updateDriverAvailability(driverId);
        return "redirect:/";
    }
    
    @GetMapping("/track-order")
    public String trackOrder(Model model) {
        OrderManager orderManager = OrderManager.getInstance();  // Get OrderManager instance
        String orderStatus = orderManager.getStatus();  // Get status from OrderManager
        model.addAttribute("orderStatus", orderStatus);
        return "trackOrder";  // Name of your track order view template
    }
}
