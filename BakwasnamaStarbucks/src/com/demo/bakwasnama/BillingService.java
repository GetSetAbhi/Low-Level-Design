package com.demo.bakwasnama;

public class BillingService {

	public double calculateTotal(Order order, PricingStrategy pricingStrategy) {
		return pricingStrategy.calculatePrice(order);
	}
}
