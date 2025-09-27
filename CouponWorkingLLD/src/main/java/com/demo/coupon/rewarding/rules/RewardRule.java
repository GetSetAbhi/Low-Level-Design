package com.demo.coupon.rewarding.rules;

import com.demo.coupon.Cart;
import com.demo.coupon.rewarding.Reward;

public interface RewardRule {

	public boolean match(Cart cart);
	
	public Reward getReward();
}
