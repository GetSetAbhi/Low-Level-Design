package com.demo.alerting.sensor;

public class Sensor {
	public SensorType sensorType;
	public long value;
	
	public Sensor(SensorType type, long value) {
		this.sensorType = type;
		this.value = value;
	}
	
	public SensorType getSensorType() {
		return sensorType;
	}
	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}	
}
