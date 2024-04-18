package com.example.zepto.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "Shop")
public class shop {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="shop_id")
	private Long id; 
	
	@Column(name="name")
	private String shopName;
	
	@Column(name="Location")
	private String location;
	
//	@OneToMany(mappedBy = "shop")
//	private Set<productShop> productShops;
//    @ManyToMany
//    private Set<product> products = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

//	public Set<product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(Set<product> products) {
//		this.products = products;
//	}

}
