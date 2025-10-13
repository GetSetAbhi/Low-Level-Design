package com.demo.airline;

import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Seat {
	public String seatId;
	public String seatLabel;
	public boolean isBooked;
	public Lock lock;
	
	public Seat(String label) {
		this.seatId = UUID.randomUUID().toString();
		this.seatLabel = label;
		this.isBooked = false;
		this.lock = new ReentrantLock();
	}

	@Override
	public String toString() {
		return "Seat ["+ this.seatId +", seatLabel=" + seatLabel + ", isBooked=" + isBooked + "]";
	}
}
