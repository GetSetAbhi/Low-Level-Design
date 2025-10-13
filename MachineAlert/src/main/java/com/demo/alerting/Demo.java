package com.demo.alerting;

import com.demo.alerting.rules.PressureSensorRule;
import com.demo.alerting.rules.TemparatureSensorRule;
import com.demo.alerting.sensor.SensorType;

public class Demo {

	public static void main(String[] args) {
		
		Machine m1 = new Machine("M1");
		m1.addSensor(SensorType.TEMPERATURE,90);
		m1.addSensor(SensorType.PRESSURE,50);
		
		Machine m2 = new Machine("M2");
		m2.addSensor(SensorType.TEMPERATURE,100);
		m2.addSensor(SensorType.PRESSURE,150);
		
		Machine m3 = new Machine("M3");
		m3.addSensor(SensorType.TEMPERATURE,10);
		m3.addSensor(SensorType.PRESSURE,40);
		
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
