package com.demo.splitwise;

import java.util.Collections;
import java.util.List;

public class DemoMain {

	public static void main(String[] args) throws Exception {

		User abhi = new User("Abhishek");
		User palak = new User("Palak");
		
		TransactionService ts = new TransactionService();
		
		Expense expense = ts.createExpense(abhi, 1000, SplitType.EQUAL, List.of(palak), Collections.EMPTY_LIST);
		
		ts.registerExpenses(expense);
		
		abhi.showBalanceSheet();
		
		palak.showBalanceSheet();
	}

}
