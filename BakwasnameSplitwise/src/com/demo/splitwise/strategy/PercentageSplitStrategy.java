package com.demo.splitwise.strategy;

import java.util.List;

import com.demo.splitwise.Expense;
import com.demo.splitwise.split.PercentageSplit;
import com.demo.splitwise.split.Split;

public class PercentageSplitStrategy implements SplitStrategy {

	@Override
	public List<Split> getSplits(Expense expense) {
		double totalAmount = expense.getTotal();
		
		for (Split split : expense.getSplits()) {
			PercentageSplit ps = (PercentageSplit) split;
			double percent = ps.getPercent();
			double share = (totalAmount*percent) / 100;
			ps.setAmount(share);
		}
		
		return expense.getSplits();
	}

}
