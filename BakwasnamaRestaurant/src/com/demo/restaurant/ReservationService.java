package com.demo.restaurant;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReservationService {

	private final Restaurant restaurant;

	private final TableAllocationStrategy strategy;

	public ReservationService(Restaurant restaurant, TableAllocationStrategy strategy) {

		this.restaurant = restaurant;
		this.strategy = strategy;
	}

	public Reservation reserve(String customerName, int partySize, LocalDateTime startTime, LocalDateTime endTime) {

		if (partySize <= 0) {
			throw new IllegalArgumentException("Invalid party size");
		}

		Table table = strategy.allocate(partySize, startTime, endTime, restaurant.getTables(), restaurant.getReservations().values());

		if (table == null) {
			throw new RuntimeException("No table available");
		}

		String id = UUID.randomUUID().toString();

		Reservation reservation = new Reservation(id, customerName, partySize, startTime, endTime, table);

		restaurant.getReservations().put(id, reservation);

		return reservation;
	}

	public void cancel(String reservationId) {

		if (!restaurant.getReservations().containsKey(reservationId)) {

			throw new RuntimeException("Reservation not found");
		}

		restaurant.getReservations().remove(reservationId);
	}
}
