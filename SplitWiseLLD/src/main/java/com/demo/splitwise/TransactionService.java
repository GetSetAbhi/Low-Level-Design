package com.demo.splitwise;

import java.util.List;

import com.demo.splitwise.strategy.SplitFactory;
import com.demo.splitwise.strategy.SplitStrategy;

public class TransactionService {
	
	public TransactionService() {
	}

	public Expense createExpense(User user, double totalAmount, SplitType type, List<User> contributors, List<Double> values) {
		Expense expense = new Expense(user,totalAmount, type, contributors);
		expense.setValues(values);
		return expense;
	}
	
	public void registerExpenses(Expense expense) throws Exception {
		SplitStrategy strategy = SplitFactory.getSplitStrategy(expense.type);
		List<Split> splits = strategy.getSplitsForExpense(expense);
		
		User expenseBearer = expense.paidBy;
		
		for (Split split : splits) {
			double amount = split.amount;
			User owedBy = split.user;
			double existingAmount = expenseBearer.balanceSheet.balances.computeIfAbsent(owedBy, k -> 0.0d);
			existingAmount -= amount;
			expenseBearer.balanceSheet.balances.put(owedBy, existingAmount);
			double existingDebt = owedBy.balanceSheet.balances.computeIfAbsent(expenseBearer, k -> 0.0d);
			existingDebt += amount;
			owedBy.balanceSheet.balances.put(expenseBearer, existingDebt);
		}
	}
}
