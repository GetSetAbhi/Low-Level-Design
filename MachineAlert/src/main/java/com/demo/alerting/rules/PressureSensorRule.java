package com.demo.alerting.rules;

import com.demo.alerting.Machine;

public class PressureSensorRule extends SensorRule {

	public PressureSensorRule(int upperbound, int lowerbound) {
		super(upperbound, lowerbound);
	}

	@Override
	public String getEventMessageForAlert(Machine machine) {
		return "The Pressure for machine " + machine.name + " is not between decided bounds";
	}

	@Override
	public boolean canTrigger(Machine machine) {
		if (machine.currentPressure < this.getLowerBound() || machine.currentPressure > this.getUpperBound()) {
			return true;
		}
		return false;
	}

}