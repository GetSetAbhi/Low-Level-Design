package com.demo.splitwise.strategy;

import java.util.ArrayList;
import java.util.List;

import com.demo.splitwise.Expense;
import com.demo.splitwise.Split;
import com.demo.splitwise.User;

public class EqualSplitStrategy implements SplitStrategy {

	@Override
	public List<Split> getSplitsForExpense(Expense expense) {
		int totalUsers = expense.others.size() + 1;
		double amountPerPerson = expense.totalAmount / totalUsers;
		List<Split> splits = new ArrayList<>();
		for (User user: expense.others) {
			Split split = new Split(user, amountPerPerson);
			splits.add(split);
		}
		return splits;
	}
}
