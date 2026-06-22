package com.demo.bakwasnama;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private List<OrderItem> items;
	
	public Order() {
		this.items = new ArrayList<>();
	}
	
	public void addItem(OrderItem item) {
		items.add(item);
	}
	
	public double getTotalPrice() {
		double total = 0.0;
		for (OrderItem item : items) {
			total += item.getTotalPrice();
		}
		return total;
	}
}
