package com.demo.coupon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.demo.coupon.rewarding.Coupon;
import com.demo.coupon.rewarding.Reward;

public class Cart {

	private User user;
	private List<CartItem> cartItems;
	private List<Reward> rewards;
	private Lock lock;

	public Cart(User user) {
		super();
		this.user = user;
		this.cartItems = new ArrayList<>();
		this.rewards = new ArrayList<>();
		this.lock = new ReentrantLock();
	}

	public void addProduct(Product product) {
		this.cartItems.add(new CartItem(product, product.getPrice()));
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotalCartValueWithoutDiscount() {
		return this.cartItems.stream().map(item -> item.getPrice()).reduce((a, b) -> a + b).get();
	}

	public List<Coupon> getAllCoupons() {
		return this.rewards.stream().filter(item -> item instanceof Coupon).map(item -> (Coupon) item)
				.toList();
	}
	
	public synchronized double getFinalCheckoutPrice() {
		double amount = this.getTotalCartValueWithoutDiscount();
		double checkOutAmount = amount;
		for (Coupon coupon : this.getAllCoupons()) {
			double discountAmount = (coupon.getDiscountPercentage() / 100)*amount;
			if (checkOutAmount - discountAmount > 0) {
				checkOutAmount -= discountAmount;
				System.out.println("Discount of amount " + discountAmount + " applied to cart");
			}
		}
		return checkOutAmount;
	}

	public List<Reward> getRewards() {
		return this.rewards;
	}

	public void addReward(Reward reward) {
		this.lock.lock();
		try {
			this.rewards.add(reward);
		} finally {
			this.lock.unlock();
		}
	}
}
