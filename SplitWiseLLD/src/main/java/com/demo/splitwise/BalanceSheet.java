package com.demo.splitwise;

import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {
	Map<User, Double> balances;
	
	public BalanceSheet() {
		this.balances = new HashMap<>();
	}
}
