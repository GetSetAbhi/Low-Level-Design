package com.demo.airline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {

	private FlightService flightService;
	
	public Map<String, Booking> bookingsMap;
	
	public BookingService(FlightService flightService) {
		this.flightService = flightService;
		this.bookingsMap = new HashMap<>();
	}

	public Booking createBooking(String flightInstanceId, List<String> seatIdList, User user) {
		FlightInstance flightInstance = flightService.getFlightInstance(flightInstanceId);
		List<Seat> acquiredLocks = new ArrayList<>();
		try {
			if (flightInstance != null) {
				List<Seat> seats = flightInstance.getSeats(seatIdList);
				if (seats != null && !seats.isEmpty()) {
					List<Seat> sortedSeats = new ArrayList<>(seats);
					sortedSeats.sort((a, b) -> a.seatLabel.compareTo(b.seatLabel));
					for (Seat seat : sortedSeats) {
						seat.lock.lock();
						acquiredLocks.add(seat);
						if (seat.isBooked) {
							throw new RuntimeException("seat " + seat.seatLabel + " on flight " + flightInstance.name
									+ " is already booked");
						}
						seat.isBooked = true;
					}
					Booking booking = new Booking(user, flightInstance, sortedSeats);
					user.bookings.add(booking);
					this.bookingsMap.put(booking.bookingId, booking);
					return booking;
				}
			}
			throw new RuntimeException("No FLight found");
		} finally {
			int n = acquiredLocks.size();
			if (n > 0) {
				for (int i = n - 1; i >= 0; i--) {
					acquiredLocks.get(i).lock.unlock();
				}
			}
		}
	}
}
