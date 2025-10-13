package com.demo.alerting;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.demo.alerting.sensor.Sensor;
import com.demo.alerting.sensor.SensorType;

public class Machine {

	public String name;
	Map<SensorType, Sensor> sensorMap;
	
	public Machine(String name) {
		this.name = name;
		this.sensorMap = new ConcurrentHashMap<>();
	}
	
	public void addSensor(SensorType type, long value) {
		Sensor sensor = this.sensorMap.computeIfAbsent(type, k -> new Sensor(type, value));
		sensor.setValue(value);
	}
	
	public Sensor getSensor(SensorType type) {
		return this.sensorMap.getOrDefault(type, null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
