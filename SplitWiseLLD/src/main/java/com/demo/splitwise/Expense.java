package com.demo.splitwise;

import java.util.List;

public class Expense {
	public User paidBy;
	public List<User> others;
	public List<Double> values;
	public double totalAmount;
	public List<Split> splits;
	public SplitType type;
	
	public Expense(User paidBy, double total, SplitType type, List<User> others) {
		this.paidBy = paidBy;
		this.others = others;
		this.totalAmount = total;
		this.type = type;
	}
	
	public void setValues(List<Double> values) {
		this.values = values;
	}
}
