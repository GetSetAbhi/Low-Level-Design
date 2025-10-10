package com.demo.alerting.rules;

import com.demo.alerting.Machine;

public abstract class SensorRule {
	public int upperBound;
	public int lowerBound;
	
	public SensorRule(int upperbound, int lowerbound) {
		this.upperBound = upperbound;
		this.lowerBound = lowerbound;
	}

	public abstract String getEventMessageForAlert(Machine machine);
	public abstract boolean canTrigger(Machine machine);
	
	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	public int getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}
}
