package com.demo.bakwasnama;

public class OrderService {

	public Order createOrder() {
		return new Order();
	}
	
	public void addItemToOrder(Order order, MenuItem menuItem, int quantity) {
		OrderItem item = new OrderItem(menuItem, quantity);
		order.addItem(item);
	}
}
