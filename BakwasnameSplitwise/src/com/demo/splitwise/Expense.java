package com.demo.splitwise;

import java.util.List;

import com.demo.splitwise.split.Split;
import com.demo.splitwise.split.SplitType;

public class Expense {

	private User payingUser;
	private Double total;
	private List<Split> splits;
	private SplitType splitType;
	
	public Expense(User payingUser, Double total, List<Split> splits, SplitType type) {
		super();
		this.payingUser = payingUser;
		this.total = total;
		this.splits = splits;
		this.splitType = type;
	}
	
	public User getPayingUser() {
		return payingUser;
	}
	public Double getTotal() {
		return total;
	}

	public List<Split> getSplits() {
		return splits;
	}

	public void setSplits(List<Split> splits) {
		this.splits = splits;
	}

	public SplitType getSplitType() {
		return splitType;
	}
	
	
	
}
