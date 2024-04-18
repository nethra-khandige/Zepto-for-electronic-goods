package com.example.zepto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.zepto.model.shop;

import com.example.zepto.repository.shopRepository;

@Service
public class shopService {

	@Autowired
	shopRepository shopRepo;
	public List<shop> getAllShop(){ return shopRepo.findAll();}

//    public shop getShopById(shop shop) {
//        return shopRepo.findById(shop)
//                .orElseThrow(() -> new RuntimeException("Shop not found"));
//    }
    public Optional<shop> getShopById(long id) {
        return shopRepo.findById(id);
    }
    public void addShop(shop Shop) {
    	shopRepo.save(Shop);
    }
    
    public void removeShopByID(Long id) {
    	shopRepo.deleteById(id);
    }

}
