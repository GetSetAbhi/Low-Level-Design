package com.demo.coupon.rewarding.engine;

import java.util.ArrayList;
import java.util.List;

import com.demo.coupon.Cart;
import com.demo.coupon.enums.ProductCategory;
import com.demo.coupon.rewarding.Reward;
import com.demo.coupon.rewarding.rules.CashbackRewardRule;
import com.demo.coupon.rewarding.rules.CouponRewardRule;
import com.demo.coupon.rewarding.rules.RewardRule;

public class RewardEngine {

	private List<RewardRule> rules;
	
	public RewardEngine() {
		this.rules = new ArrayList<>();
		// 5% cashback on min cart value of 20
		RewardRule cashbackRewardRule = new CashbackRewardRule(20, 5);
		
		// 10% discount if cart contains an item from Electronics
		RewardRule couponRewardRule = new CouponRewardRule(ProductCategory.ELECTRONICS, 10);
		this.rules.add(cashbackRewardRule);
		this.rules.add(couponRewardRule);
	}
	
	public void applyRewards(Cart cart) {
		for (RewardRule rule : rules) {
			if (rule.match(cart)) {
				Reward reward = rule.getReward();
				reward.applyReward(cart);
			}
		}
	}
}
