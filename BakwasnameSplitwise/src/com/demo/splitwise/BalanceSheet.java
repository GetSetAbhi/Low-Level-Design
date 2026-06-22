package com.demo.splitwise;

import java.util.HashMap;
import java.util.Map;

import com.demo.splitwise.split.Split;

public class BalanceSheet {

	private Map<User, Map<User, Double>> balances;

	public BalanceSheet() {
		super();
		this.balances = new HashMap<>();
	}
	
	public void addBalance(Split split) {
		User lender = split.getLender();
		User debtor = split.getDebitor();
		double amount = split.getAmount();
		
		Map<User, Double> lenderMap = balances.computeIfAbsent(lender, k -> new HashMap<>());
		double currentAmount = lenderMap.computeIfAbsent(debtor, k -> 0.0);
		lenderMap.put(debtor, currentAmount - amount);
		
		Map<User, Double> debtMap = balances.computeIfAbsent(debtor, k -> new HashMap<>());
		double currentAmount2 = debtMap.computeIfAbsent(lender, k -> 0.0);
		debtMap.put(lender, currentAmount2 + amount);
	}
	
	public void showBalances() {
		for (User user : balances.keySet()) {
			for (Map.Entry<User, Double> e : balances.get(user).entrySet()) {
				if (e.getValue() > 0) {
					System.out.println(user.getName() + " owes " + e.getValue() + " to " + e.getKey().getName());
				}
			}
		}
	}
}
