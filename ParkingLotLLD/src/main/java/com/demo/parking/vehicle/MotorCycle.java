package com.demo.parking.vehicle;

public class MotorCycle extends Vehicle {

	public MotorCycle(String plateNumber, String chassisNumber) {
		super(plateNumber, chassisNumber);
	}

	@Override
	public VehicleType getVehicleType() {
		return VehicleType.MOTORCYCLE;
	}

}
