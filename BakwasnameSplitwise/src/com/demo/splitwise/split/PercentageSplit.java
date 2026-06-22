package com.demo.splitwise.split;

import com.demo.splitwise.User;

public class PercentageSplit extends Split {

	private double percent;
	
	public PercentageSplit() {
		super();
	}

	public PercentageSplit(User debitor, Double percent) {
		super(debitor);
		this.percent = percent;
	}

	public double getPercent() {
		return percent;
	}

	@Override
	public SplitType getSplitType() {
	
		return SplitType.PERCENTAGE;
	}

}
