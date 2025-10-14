package com.demo.parking.spot;

import com.demo.parking.vehicle.VehicleType;

public class CarParkingSpot extends ParkingSpot {

	@Override
	public VehicleType getType() {
		return VehicleType.CAR;
	}

	@Override
	public Double getPerHourCost() {
		return 20.0;
	}

}
