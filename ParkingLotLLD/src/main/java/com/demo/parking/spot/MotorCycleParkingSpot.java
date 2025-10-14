package com.demo.parking.spot;

import com.demo.parking.vehicle.VehicleType;

public class MotorCycleParkingSpot extends ParkingSpot {

	public MotorCycleParkingSpot() {
		super();
	}

	@Override
	public VehicleType getType() {
		// TODO Auto-generated method stub
		return VehicleType.MOTORCYCLE;
	}

	@Override
	public Double getPerHourCost() {
		return 10.0;
	}

}
