package com.demo.splitwise.split;

import com.demo.splitwise.User;

public abstract class Split {

	private User lender;
	private User debitor;
	private Double amount;
	
	public Split() {}
	
	public Split(User debitor) {
		super();
		this.debitor = debitor;
	}

	public User getLender() {
		return lender;
	}

	public User getDebitor() {
		return debitor;
	}

	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void setLender(User lender) {
		this.lender = lender;
	}

	public abstract SplitType getSplitType();
}
