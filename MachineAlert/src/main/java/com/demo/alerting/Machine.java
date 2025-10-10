package com.demo.alerting;

public class Machine {

	public String name;
	public int currentTemprature;
	public int currentPressure;
	
	public Machine(String name, int temparature, int pressure) {
		this.name = name;
		this.currentTemprature = temparature;
		this.currentPressure = pressure;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentTemprature() {
		return currentTemprature;
	}

	public void setCurrentTemprature(int currentTemprature) {
		this.currentTemprature = currentTemprature;
	}

	public int getCurrentPressure() {
		return currentPressure;
	}

	public void setCurrentPressure(int currentPressure) {
		this.currentPressure = currentPressure;
	}
	
	
}
