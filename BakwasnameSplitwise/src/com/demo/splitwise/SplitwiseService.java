package com.demo.splitwise;

import java.util.List;

import com.demo.splitwise.split.Split;
import com.demo.splitwise.split.SplitType;
import com.demo.splitwise.strategy.SplitFactory;
import com.demo.splitwise.strategy.SplitStrategy;

public class SplitwiseService {
	
	private BalanceSheet balanceSheet;

	public SplitwiseService(BalanceSheet sheet) {
		this.balanceSheet = sheet;
	}
	
	public Expense createExpense(User payer, List<Split> splits, double totalAmount, SplitType type) {
		Expense expense = new Expense(payer, totalAmount, splits, type);
		for (Split split : expense.getSplits()) {
			split.setLender(payer);
		}
		return expense;
	}
	
	public void addExpense(Expense expense) {
		SplitStrategy strategy = SplitFactory.getStrategy(expense.getSplitType());
		List<Split> validatedSplits = strategy.getSplits(expense);
		for (Split split : validatedSplits) {
			if (!split.getDebitor().equals(expense.getPayingUser())) {
				balanceSheet.addBalance(split);
			}
		}
	}
	
	public void showBalances() {
		this.balanceSheet.showBalances();
	}
}
