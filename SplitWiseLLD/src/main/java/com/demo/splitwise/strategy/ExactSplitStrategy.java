package com.demo.splitwise.strategy;

import java.util.ArrayList;
import java.util.List;

import com.demo.splitwise.Expense;
import com.demo.splitwise.Split;
import com.demo.splitwise.User;

public class ExactSplitStrategy implements SplitStrategy {

	@Override
	public List<Split> getSplitsForExpense(Expense expense) {
		int totalUsers = expense.others.size() + 1;
		List<Split> splits = new ArrayList<>();
		
		for (int i = 0; i < expense.others.size(); i++) {
			User user = expense.others.get(i);
			double amount = expense.values.get(i);
			Split split = new Split(user, amount);
			splits.add(split);
		}
		return splits;
	}
}