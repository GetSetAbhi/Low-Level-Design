package com.demo.splitwise;

import java.util.Map;

public class User {
	public String name;
	public BalanceSheet balanceSheet;
 
	public User(String name) {
		this.name = name;
		this.balanceSheet = new BalanceSheet();
	}
	
	public void showBalanceSheet() {
		for (Map.Entry<User, Double> entry : this.balanceSheet.balances.entrySet()) {
			if (entry.getValue() > 0 ) {
				System.out.println("You need to pay " + entry.getKey().name + " " + entry.getValue());
			} else {
				System.out.println(entry.getKey().name + " owes you " + Math.abs(entry.getValue()));
			}
		}
	}
}


