package com.example.zepto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.zepto.model.product;

public interface productRepository extends JpaRepository<product,Long> {

	List<product> findAllByShop_id(long id);
}
