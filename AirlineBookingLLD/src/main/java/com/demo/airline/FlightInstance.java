package com.demo.airline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class FlightInstance {
	public String flightInstanceId;
	public String name;
	public LocalDateTime departureTime;
	public LocalDateTime arrivalTime;
	public Map<String, Seat> seatMap;
	
	public FlightInstance(String name,LocalDateTime depaDateTime, LocalDateTime arriDateTime) {
		this.name = name;
		this.flightInstanceId = UUID.randomUUID().toString();
		this.arrivalTime = arriDateTime;
		this.departureTime = depaDateTime;
		this.seatMap = new HashMap<>();
		for (int i = 0; i < 12; i++) {
			int a = (int) 'a';
			char c = (char) (a + i);
			Seat seat = new Seat(String.valueOf(c));	
			this.seatMap.put(seat.seatId, seat);
		}
	}

	@Override
	public String toString() {
		String message = "FlightInstance ["+this.flightInstanceId +" ,departureTime=" + date(departureTime) + ", arrivalTime=" + date(arrivalTime) + "]";
		System.out.println(message);
		if (this.seatMap.size() > 0) {
			List<Seat> seats = seatMap.values().stream().sorted((a, b) -> a.seatLabel.compareTo(b.seatLabel)).collect(Collectors.toList());
			for (Seat seat: seats) {
				System.out.println(seat);
			}
		}
		return "";
	}
	
	private String date(LocalDateTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return time.format(formatter);
	}

	public List<Seat> getSeats(List<String> seatIdList) {
		Set<String> seatSet = new HashSet<>(seatIdList);
		return this.seatMap.values().stream().filter(item -> seatSet.contains(item.seatLabel)).collect(Collectors.toList());
	}
}
