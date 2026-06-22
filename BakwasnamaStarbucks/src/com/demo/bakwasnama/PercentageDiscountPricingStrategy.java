package com.demo.bakwasnama;

public class PercentageDiscountPricingStrategy implements PricingStrategy {

	private double percentage;
	
	public PercentageDiscountPricingStrategy(double percentage) {
		this.percentage = percentage;
	}
	
	@Override
	public double calculatePrice(Order order) {
		double totalPrice = order.getTotalPrice();
		double discount = totalPrice * (percentage / 100);
		return Math.max(0, totalPrice - discount);
	}

}
