package com.demo.main;

import com.demo.coupon.Cart;
import com.demo.coupon.Product;
import com.demo.coupon.User;
import com.demo.coupon.enums.ProductCategory;
import com.demo.coupon.rewarding.engine.RewardEngine;

public class DemoTest {

	public static void main(String[] args) {
		User user = new User("Abhishek");
		
		Product washing = new Product("LG Washingmachine", 50, ProductCategory.ELECTRONICS);
		Product frier = new Product("Airfryer", 30, ProductCategory.KITCHEN);
		
		Cart cart = new Cart(user);
		cart.addProduct(washing);
		cart.addProduct(frier);
		
		RewardEngine rewardEngine = new RewardEngine();
		
		rewardEngine.applyRewards(cart);
		
		System.out.println("Final checkout : " + cart.getFinalCheckoutPrice());
	}

}
