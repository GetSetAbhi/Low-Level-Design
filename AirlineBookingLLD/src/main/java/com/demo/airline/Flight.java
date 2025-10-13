package com.demo.airline;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Flight {
	public int flightId;
	public String name;
	public String source;
	public String desination;
	public Map<String, FlightInstance> flightInstanceMap;
	
	public Flight(String name, String source, String destination) {
		this.flightId = UUID.randomUUID().hashCode();
		this.source = source;
		this.desination = destination;
		
		this.flightInstanceMap = new HashMap<>();
		for (int i = 0; i < 5; i++) {
			LocalDateTime departure = LocalDateTime.of(2025, 8, 12 + i, 20, 30);
			LocalDateTime arrival = LocalDateTime.of(2025, 8, 12 + i, 20 + 2, 30);
			FlightInstance flightInstance = new FlightInstance(this.name, departure, arrival);
			flightInstanceMap.put(flightInstance.flightInstanceId, flightInstance);
		}
	}
}
