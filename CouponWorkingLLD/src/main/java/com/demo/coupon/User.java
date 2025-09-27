package com.demo.coupon;

import java.util.ArrayList;
import java.util.List;

import com.demo.coupon.rewarding.Reward;

public class User {
	private String name;
	private double walletAmount;

	private List<Reward> rewards;
	
	public User(String name) {
		super();
		this.name = name;
		this.rewards = new ArrayList<>();
		this.walletAmount = 0;
	}

	public String getName() {
		return name;
	}

	public List<Reward> getRewards() {
		return rewards;
	}

	public double getWalletAmount() {
		return walletAmount;
	}

	public synchronized void addAmountToWallet(double walletAmount) {
		this.walletAmount += walletAmount;
		System.out.println("Cashback of amount " + walletAmount + " received by " + this.name);
	}
	
	
}
