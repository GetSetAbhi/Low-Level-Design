package com.demo.alerting;

public class NotificationService {

	public void sendAlert(AlertEvent event) {
		System.out.println("[Alert] " + event.machine.name + " : " + event.message);
	}
}
