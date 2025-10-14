package com.demo.parking.spot;

import com.demo.parking.vehicle.VehicleType;

public class TruckParkingSpot extends ParkingSpot {

	@Override
	public VehicleType getType() {
		return VehicleType.TRUCK;
	}

	@Override
	public Double getPerHourCost() {
		return 30d;
	}

}
