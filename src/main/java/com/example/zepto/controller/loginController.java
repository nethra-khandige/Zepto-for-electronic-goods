package com.example.zepto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.zepto.global.globalData;
import com.example.zepto.model.role;
import com.example.zepto.model.user;
import com.example.zepto.repository.roleRepository;
import com.example.zepto.repository.userRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class loginController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	userRepository userRepo;

	@Autowired
	roleRepository roleRepo;
	
	@GetMapping("/login")
	public String login() {
		globalData.cart.clear();
		return "login";
	}
	@GetMapping("/register")
	public String registerGet() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerPost(@ModelAttribute("user") user user, HttpServletRequest request) throws ServletException{
		  if (SecurityContextHolder.getContext().getAuthentication() != null 
			      && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			    // User already authenticated, throw exception or redirect with error message
			    return "redirect:/login?error=already-authenticated";
		  }
		String password = user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		List<role>roles = new ArrayList<>();
		roles.add(roleRepo.findById((long)2).get());
		user.setRoles(roles);
		userRepo.save(user);
		request.login(user.getEmail(), password);
		return "redirect:/";
	}
}
