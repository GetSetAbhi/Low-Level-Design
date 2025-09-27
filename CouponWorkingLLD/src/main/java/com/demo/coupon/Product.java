package com.demo.coupon;

import com.demo.coupon.enums.ProductCategory;

public class Product {
	
	private String name;
	private ProductCategory category;
	private double price;
	
	public Product(String name, double price, ProductCategory category) {
		super();
		this.name = name;
		this.category = category;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
