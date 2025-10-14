package com.demo.parking.vehicle;

public class Truck extends Vehicle {

	public Truck(String plateNumber, String chassisNumber) {
		super(plateNumber, chassisNumber);
	}

	@Override
	public VehicleType getVehicleType() {
		return VehicleType.TRUCK;
	}

}
