package com.demo.alerting;

import java.util.ArrayList;
import java.util.List;

import com.demo.alerting.rules.SensorRule;

public class SensorEngine {

	public List<SensorRule> rules;
	
	public SensorEngine() {
		this.rules = new ArrayList<>();
	}
	
	public void addRule(SensorRule rule) {
		this.rules.add(rule);
	}
	
	public List<AlertEvent> getAlertsForMachines(List<Machine> machines) {
		List<AlertEvent> alerts = new ArrayList<>();
		for (SensorRule rule : rules) {
			for (Machine machine : machines) {
				if (rule.canTrigger(machine)) {
					String message = rule.getEventMessageForAlert(machine);
					AlertEvent event = new AlertEvent(message, machine);
					alerts.add(event);
				}
			}
		}
		return alerts;
	}
}
