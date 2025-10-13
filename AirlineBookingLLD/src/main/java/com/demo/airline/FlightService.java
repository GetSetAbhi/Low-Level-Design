package com.demo.airline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightService {

	public Map<Integer, Flight> flightMap;

	public FlightService() {
		this.flightMap = new HashMap<>();
		Flight f1 = new Flight("Air India", "IXJ", "DEL");
		Flight f2 = new Flight("Air India", "IXJ", "BOM");
		Flight f3 = new Flight("Air India", "IXJ", "HYD");
		Flight f4 = new Flight("Air India", "DEL", "HYD");
		putFlights(f1);
		putFlights(f2);
		putFlights(f3);
		putFlights(f4);
	}

	private void putFlights(Flight f) {
		this.flightMap.put(f.flightId, f);
	}

	public List<FlightInstance> getFlights(String source, String destination) {
		return this.flightMap.values().stream()
				.filter(flight -> flight.source.equals(source) && flight.desination.equals(destination))
				.flatMap(f -> f.flightInstanceMap.values().stream()).collect(Collectors.toList());
	}

	public FlightInstance getFlightInstance(String flightInstanceId) {
		Optional<FlightInstance> flightOptional = this.flightMap.values().stream().flatMap(f -> f.flightInstanceMap.values().stream())
				.filter(f -> f.flightInstanceId.equals(flightInstanceId)).findFirst();
		if (flightOptional.isPresent()) {
			return flightOptional.get();
		}
		return null;
	}

}
