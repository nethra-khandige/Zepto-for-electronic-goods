package com.example.zepto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zepto.model.product;
import com.example.zepto.repository.productRepository;

@Service
public class productService {

	@Autowired
	productRepository productRepo;
	public List<product> getAllProduct(){ return productRepo.findAll();}
	 
	public void addProduct(product Product) {
		productRepo.save(Product);
	}
	public void removeProductById(long id) {
		productRepo.deleteById(id);
	}
	public Optional<product> getProductById(long id){
		return productRepo.findById(id);
	}
    public List<product> getAllProductsByShopId(Long id) {
        return productRepo.findAllByShop_id(id);
    }
    


	
}
