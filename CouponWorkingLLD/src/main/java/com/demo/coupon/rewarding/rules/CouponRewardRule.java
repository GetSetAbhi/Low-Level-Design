package com.demo.coupon.rewarding.rules;

import com.demo.coupon.Cart;
import com.demo.coupon.enums.ProductCategory;
import com.demo.coupon.rewarding.Coupon;
import com.demo.coupon.rewarding.Reward;

public class CouponRewardRule implements RewardRule {

	private ProductCategory productCategory;
	private Reward reward;

	public CouponRewardRule(ProductCategory category, double discountAmount) {
		this.productCategory = category;
		this.reward = new Coupon(discountAmount);
	}

	@Override
	public boolean match(Cart cart) {
		return cart.getCartItems().stream().map(item -> item.getProduct().getCategory())
				.anyMatch(category -> category.equals(this.productCategory));
	}

	@Override
	public Reward getReward() {
		return this.reward;
	}
}
