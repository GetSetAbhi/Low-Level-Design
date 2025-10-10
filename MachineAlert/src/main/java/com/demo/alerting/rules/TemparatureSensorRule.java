package com.demo.alerting.rules;

import com.demo.alerting.Machine;

public class TemparatureSensorRule extends SensorRule {

	public TemparatureSensorRule(int upperbound, int lowerbound) {
		super(upperbound, lowerbound);
	}

	@Override
	public String getEventMessageForAlert(Machine machine) {
		return "The temparature for machine " + machine.name + " is not between decided bounds";
	}

	@Override
	public boolean canTrigger(Machine machine) {
		if (machine.currentTemprature < this.getLowerBound() || machine.currentTemprature > this.getUpperBound()) {
			return true;
		}
		return false;
	}

}
