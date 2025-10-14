package com.demo.parking.spot;

import com.demo.parking.vehicle.Vehicle;
import com.demo.parking.vehicle.VehicleType;

public abstract class ParkingSpot {

	public Vehicle vehicle;
	public double perHourCost;
	
	public boolean isOccupied() {
		return this.vehicle != null;
	}
	
	public abstract VehicleType getType();
	
	public abstract Double getPerHourCost();

	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
