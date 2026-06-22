package com.demo.restaurant;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface TableAllocationStrategy {

	Table allocate(int partySize, LocalDateTime startTime, LocalDateTime endTime, List<Table> tables,
			Collection<Reservation> reservations);

}

class FirstFitTableAllocationStrategy implements TableAllocationStrategy {

	@Override
	public Table allocate(int partySize, LocalDateTime startTime, LocalDateTime endTime, List<Table> tables,
			Collection<Reservation> reservations) {

		Set<Integer> occupied = reservations.stream().filter(r -> isOverlapping(startTime, endTime, r))
				.map(r -> r.getTable().getId()).collect(Collectors.toSet());

		for (Table table : tables) {

			if (occupied.contains(table.getId())) {
				continue;
			}

			if (table.getCapacity() >= partySize) {
				return table;
			}
		}

		return null;
	}

	private boolean isOverlapping(LocalDateTime startTime, LocalDateTime endTime, Reservation reservation) {
		return startTime.isBefore(reservation.getEndTime()) && endTime.isAfter(reservation.getStartTime());
	}
}