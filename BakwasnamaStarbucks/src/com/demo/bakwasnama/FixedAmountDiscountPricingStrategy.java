package com.demo.bakwasnama;

public class FixedAmountDiscountPricingStrategy implements PricingStrategy {

	private double discountAmount;
	
	public FixedAmountDiscountPricingStrategy(int disountAmount) {
		this.discountAmount = disountAmount;
	}
	
	@Override
	public double calculatePrice(Order order) {
		return Math.max(0, order.getTotalPrice() - this.discountAmount);
	}

}
