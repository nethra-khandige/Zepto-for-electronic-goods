package com.example.zepto.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "Product")
public class product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id")
	private Long id; 
	
	@Column(name="name")
	private String name;
	
//    // Relationship with ProductShop entity (many-to-many with Shop)
//    @OneToMany(mappedBy = "product")
//    private Set<productShop> productShops;
	
	@Column(name="Description")
	private String description;

	@Column(name="price")
	private double price;
	
	@Column(name="image")
	private String imageName;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id",referencedColumnName="shop_id")
    private shop shop; 
//    @ManyToMany(mappedBy = "products")
//    private Set<shop> shops = new HashSet<>();
    

	public Long getId() {
		return id;
	}

	public shop getShop() {
		return shop;
	}

	public void setShop(shop shop) {
		this.shop = shop;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


}
