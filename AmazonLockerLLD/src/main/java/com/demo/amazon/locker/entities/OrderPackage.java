package com.demo.amazon.locker.entities;

import java.util.UUID;

import com.demo.amazon.locker.Size;

public class OrderPackage {
	
	private String orderPackageId;
	private Size size;
	private String name;
	private User user;

	public OrderPackage(String name, Size size) {
		this.orderPackageId = UUID.randomUUID().toString();
		this.name = name;
		this.size = size;
	}

	public String getOrderPackageId() {
		return orderPackageId;
	}

	public Size getSize() {
		return size;
	}

	public String getName() {
		return name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
