package com.demo.parking.vehicle;

import java.util.UUID;

public abstract class Vehicle {

	public String id;
	public String plateNumber;
	public String chassisNumber;

	public Vehicle(String plateNumber, String chassisNumber) {
		super();
		this.id = UUID.randomUUID().toString();
		this.plateNumber = plateNumber;
		this.chassisNumber = chassisNumber;
	}

	public String getId() {
		return id;
	}

	public abstract VehicleType getVehicleType();

}
