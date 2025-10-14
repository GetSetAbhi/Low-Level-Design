package com.demo.parking;

import com.demo.parking.payment.PaymentMode;
import com.demo.parking.vehicle.Car;
import com.demo.parking.vehicle.Vehicle;

public class DEMOMAIN {

	public static void main(String[] args) {
		Vehicle vehicle = new Car("1BCD11", "111");
		
		ParkingLot parkingLot = new ParkingLot();
		
		try {
			ParkingTicket ticket = parkingLot.parkVehicle(vehicle);
			
			parkingLot.exit(ticket, vehicle);
			
			parkingLot.pay(ticket, PaymentMode.CASH);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
