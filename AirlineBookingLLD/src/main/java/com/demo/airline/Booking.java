package com.demo.airline;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

enum BookingStatus {
	CONFIRMED, CANCELLED, OUTDATED
}

public class Booking {

	public String bookingId;
	public User user;
	public List<Seat> seatsBooked;
	public LocalDateTime bookingDate;
	public FlightInstance flight;
	public BookingStatus bookingStatus;
	
	public Booking(User user, FlightInstance flight, List<Seat> seatsBooked) {
		super();
		this.bookingId = UUID.randomUUID().toString();
		this.user = user;
		this.flight = flight;
		this.seatsBooked = seatsBooked;
		this.bookingDate = LocalDateTime.now();
		this.bookingStatus = BookingStatus.CONFIRMED;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
}
