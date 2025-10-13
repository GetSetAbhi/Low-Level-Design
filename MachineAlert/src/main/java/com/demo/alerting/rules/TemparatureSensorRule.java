package com.demo.alerting.rules;

import com.demo.alerting.Machine;
import com.demo.alerting.sensor.Sensor;
import com.demo.alerting.sensor.SensorType;

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
		Sensor sensor = machine.getSensor(SensorType.TEMPERATURE);
		if (sensor != null) {
			long value = sensor.getValue();
			if (value < this.lowerBound || value > this.upperBound) {
				return true;
			}
		}
		return false;
	}

}
