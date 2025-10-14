package com.demo.parking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demo.parking.payment.PaymentMode;
import com.demo.parking.payment.PaymentService;
import com.demo.parking.spot.CarParkingSpot;
import com.demo.parking.spot.MotorCycleParkingSpot;
import com.demo.parking.spot.ParkingSpot;
import com.demo.parking.spot.TruckParkingSpot;
import com.demo.parking.vehicle.Vehicle;

public class ParkingLot {
	List<ParkingSpot> parkingSpots;
	public PaymentService paymentService;

	public ParkingLot() {
		this.parkingSpots = new ArrayList<>();
		this.paymentService = new PaymentService();

		for (int i = 0; i < 5; i++) {
			this.parkingSpots.add(new CarParkingSpot());
		}

		for (int i = 0; i < 5; i++) {
			this.parkingSpots.add(new MotorCycleParkingSpot());
		}

		for (int i = 0; i < 5; i++) {
			this.parkingSpots.add(new TruckParkingSpot());
		}
	}

	public ParkingTicket parkVehicle(Vehicle vehicle) throws Exception {
		Optional<ParkingSpot> parkingSpotOpt = this.parkingSpots.stream()
				.filter(p -> p.isOccupied() == false && p.getType() == vehicle.getVehicleType()).findFirst();
		if (parkingSpotOpt.isPresent()) {
			ParkingSpot spot = parkingSpotOpt.get();
			spot.setVehicle(vehicle);
			LocalDateTime time = LocalDateTime.now();
			ParkingTicket ticket = new ParkingTicket(vehicle, time);
			return ticket;
		} else {
			throw new Exception("No Parking Spot Available");
		}
	}
	
	public void exit(ParkingTicket ticket, Vehicle vehicle) throws Exception {
		Optional<ParkingSpot> parkingSpotOpt = this.parkingSpots.stream()
				.filter(p -> p.isOccupied() == true && p.vehicle.getId().equals(vehicle.getId())).findFirst();
		if (parkingSpotOpt.isPresent()) {
			ParkingSpot spot = parkingSpotOpt.get();
			spot.setVehicle(null);
			LocalDateTime time = ticket.parkingStartTime.plusHours(2);
			ticket.setParkingEndTime(time);
			Duration diff = Duration.between(ticket.parkingStartTime, ticket.parkingEndTime);
			long duration = diff.toHours();
			double totalAmount = spot.getPerHourCost()*duration;
			ticket.setAmount(totalAmount);
		} else {
			throw new Exception("Vehicle with plate " + vehicle.plateNumber + " is not parked in parking lot");
		}
	}
	
	public void pay(ParkingTicket ticket, PaymentMode paymentMode) {
		if (ticket.getPaymentStatus() == PaymentStatus.UNPAID) {
			this.paymentService.pay(ticket.amount, paymentMode, ticket);
		}
	}
}
