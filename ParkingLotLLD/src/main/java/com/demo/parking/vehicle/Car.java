package com.demo.parking.vehicle;

public class Car extends Vehicle {

	public Car(String plateNumber, String chassisNumber) {
		super(plateNumber, chassisNumber);
	}

	@Override
	public VehicleType getVehicleType() {
		return VehicleType.CAR;
	}

}
