package com.demo.coupon.rewarding;

import com.demo.coupon.Cart;

public class Coupon implements Reward {
	
	private double discountPercentage;
	
	public Coupon(double discountPercentage) {
		this.setDiscountPercentage(discountPercentage);
	}

	@Override
	public void applyReward(Cart cart) {
		if (!cart.getRewards().contains(this)) {
			cart.addReward(this);
		}
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
}
