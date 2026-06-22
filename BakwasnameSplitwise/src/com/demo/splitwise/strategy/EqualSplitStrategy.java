package com.demo.splitwise.strategy;

import java.util.List;

import com.demo.splitwise.Expense;
import com.demo.splitwise.split.EqualSplit;
import com.demo.splitwise.split.Split;

public class EqualSplitStrategy implements SplitStrategy {

	@Override
	public List<Split> getSplits(Expense expense) {
		
		double totalAmount = expense.getTotal();
		int participantCount = expense.getSplits().size();
		
		double equalShare = totalAmount / participantCount;
		
		for (Split split : expense.getSplits()) {
			EqualSplit es = (EqualSplit) split;
			es.setAmount(equalShare);
		}
		
		return expense.getSplits();
	}

}
