package com.demo.splitwise.strategy;

import java.util.List;

import com.demo.splitwise.Expense;
import com.demo.splitwise.Split;

public interface SplitStrategy {

	public List<Split> getSplitsForExpense(Expense expense);
}
