package com.demo.coupon;

public class CartItem {
	
	private Product product;
	private double price;
	
	public CartItem(Product product, double price) {
		super();
		this.product = product;
		this.price = price;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
