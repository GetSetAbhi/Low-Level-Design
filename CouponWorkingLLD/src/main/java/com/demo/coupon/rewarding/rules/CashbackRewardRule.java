package com.demo.coupon.rewarding.rules;

import com.demo.coupon.Cart;
import com.demo.coupon.rewarding.Cashback;
import com.demo.coupon.rewarding.Reward;

public class CashbackRewardRule implements RewardRule {

	private Reward reward;
	private double thresholdAmount;
	
	public CashbackRewardRule(double thresholdAmount, double discountAmount) {
		this.thresholdAmount = thresholdAmount;
		this.reward = new Cashback(discountAmount);
	}
	
	@Override
	public boolean match(Cart cart) {
		return cart.getTotalCartValueWithoutDiscount() >= thresholdAmount;
	}

	@Override
	public Reward getReward() {
		return this.reward;
	}
}
