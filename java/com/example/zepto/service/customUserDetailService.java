package com.example.zepto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.zepto.model.customUserDetails;
import com.example.zepto.model.user;
import com.example.zepto.repository.userRepository;


@Service
public class customUserDetailService implements UserDetailsService{

	@Autowired
	userRepository userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<user> user= userRepo.findUserByEmail(email);
		user.orElseThrow(()-> new UsernameNotFoundException("User not found!:-("));
		
		return user.map(customUserDetails::new).get();
	}
	

}
