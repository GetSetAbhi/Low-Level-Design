package com.demo.parking;

import java.time.LocalDateTime;

import com.demo.parking.vehicle.Vehicle;

public class ParkingTicket {

	public Vehicle vehicle;
	public LocalDateTime parkingStartTime;
	public LocalDateTime parkingEndTime;
	public PaymentStatus paymentStatus;
	public double amount;
	
	public ParkingTicket(Vehicle vehicle, LocalDateTime parkingStartTime) {
		super();
		this.vehicle = vehicle;
		this.parkingStartTime = parkingStartTime;
		this.paymentStatus = PaymentStatus.UNPAID;
	}

	public LocalDateTime getParkingEndTime() {
		return parkingEndTime;
	}

	public void setParkingEndTime(LocalDateTime parkingEndTime) {
		this.parkingEndTime = parkingEndTime;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
