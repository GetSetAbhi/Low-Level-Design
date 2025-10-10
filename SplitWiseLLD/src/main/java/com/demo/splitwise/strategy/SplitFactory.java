package com.demo.splitwise.strategy;

import com.demo.splitwise.SplitType;

public class SplitFactory {

	public static SplitStrategy getSplitStrategy(SplitType type) throws Exception {
		switch (type) {
			case EQUAL:
				return new EqualSplitStrategy();
			case EXACT:
				return new ExactSplitStrategy();
			case PERCENTAGE:
				return new PercentageSplitStrategy();
			default:
				throw new Exception();
		}
	}
}
