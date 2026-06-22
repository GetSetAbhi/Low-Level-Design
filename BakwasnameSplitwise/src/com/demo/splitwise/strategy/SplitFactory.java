package com.demo.splitwise.strategy;

import com.demo.splitwise.split.SplitType;

public class SplitFactory {

	public static SplitStrategy getStrategy(SplitType type) {
		switch (type) {
			case SplitType.EQUAL: {
				return new EqualSplitStrategy();
			}
			case SplitType.PERCENTAGE: {
				return new PercentageSplitStrategy();
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}
}
