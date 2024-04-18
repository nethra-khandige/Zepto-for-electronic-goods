package com.example.zepto.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.example.zepto.repository.userRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.zepto.model.role;
import com.example.zepto.model.user;
import com.example.zepto.repository.roleRepository;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired 
	userRepository userRepo;
	
	@Autowired
	roleRepository roleRepo;

	private DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		String email=token.getPrincipal().getAttributes().get("email").toString();
		if(userRepo.findUserByEmail(email).isPresent()) {
			
		}else {
			user user=new user();
			user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
			user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
			user.setEmail(email);
//			user.setLocation(token.getPrincipal().getAttributes().get("location").toString());
			user.setLocation("Bangalore");
			List<role>roles=new ArrayList<>();
			roles.add(roleRepo.findById((long) 2).get());
//			2->user
			user.setRoles(roles);
			userRepo.save(user);
			
		}
		redirectStrategy.sendRedirect(request, response, "/");
		
	}

}
