package com.demo.alerting;

import com.demo.alerting.rules.PressureSensorRule;
import com.demo.alerting.rules.TemparatureSensorRule;

public class Demo {

	public static void main(String[] args) {
		
		Machine m1 = new Machine("M1", 90, 50);
		
		Machine m2 = new Machine("M2", 100, 150);
		
		Machine m3 = new Machine("M3", 10, 40);
		
		SensorEngine engine = new SensorEngine();
		
		engine.addRule(new TemparatureSensorRule(50, 70));
		engine.addRule(new PressureSensorRule(30, 60));
		
		MonitoringService monitoringService = new MonitoringService(engine, new NotificationService());
		
		monitoringService.registerMachine(m1);
		monitoringService.registerMachine(m2);
		monitoringService.registerMachine(m3);
		
		monitoringService.start();
	}

}
