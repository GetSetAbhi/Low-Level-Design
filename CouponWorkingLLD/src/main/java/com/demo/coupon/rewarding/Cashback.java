package com.demo.coupon.rewarding;

import com.demo.coupon.Cart;
import com.demo.coupon.User;

public class Cashback implements Reward {

	private double cashbackPercentage;
	
	public Cashback(double cashbackPercentage) {
		this.cashbackPercentage = cashbackPercentage;
	}
	
	@Override
	public void applyReward(Cart cart) {
		if (!cart.getRewards().contains(this)) {
			User user = cart.getUser();
			double amount = cart.getTotalCartValueWithoutDiscount();
			double cashbackAmount = ((cashbackPercentage / 100)*amount);
			user.addAmountToWallet(cashbackAmount);
			cart.addReward(this);
		}
	}
}
