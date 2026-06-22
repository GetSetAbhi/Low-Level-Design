package com.demo.restaurant;

import java.time.LocalDateTime;

public class Reservation {

	private final String reservationId;
	private final String customerName;
	private final int partySize;
	private final Table table;
	private final LocalDateTime startTime;
	private final LocalDateTime endTime;

	public Reservation(String reservationId, String customerName, int partySize, LocalDateTime startTime,
			LocalDateTime endTime, Table table) {

		this.reservationId = reservationId;
		this.customerName = customerName;
		this.partySize = partySize;
		this.table = table;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getReservationId() {
		return reservationId;
	}

	public Table getTable() {
		return table;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}
	
	
}
