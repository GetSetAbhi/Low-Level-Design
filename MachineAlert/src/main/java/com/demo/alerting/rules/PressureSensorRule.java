package com.demo.alerting.rules;

import com.demo.alerting.Machine;
import com.demo.alerting.sensor.Sensor;
import com.demo.alerting.sensor.SensorType;

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
		Sensor sensor = machine.getSensor(SensorType.PRESSURE);
		if (sensor != null) {
			long value = sensor.getValue();
			if (value < this.lowerBound || value > this.upperBound) {
				return true;
			}
		}
		return false;
	}

}