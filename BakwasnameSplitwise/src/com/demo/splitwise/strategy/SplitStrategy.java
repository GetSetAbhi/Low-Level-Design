package com.demo.splitwise.strategy;

import java.util.List;

import com.demo.splitwise.Expense;
import com.demo.splitwise.split.Split;

public interface SplitStrategy {

	List<Split> getSplits(Expense expense);
}
