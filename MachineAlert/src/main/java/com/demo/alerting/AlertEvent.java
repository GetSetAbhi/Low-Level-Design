package com.demo.alerting;

public class AlertEvent {

	public String message;
	public Machine machine;
	
	public AlertEvent(String message, Machine machine) {
		this.message = message;
		this.machine = machine;
	}
}
