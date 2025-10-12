package com.demo.evlevtor;

import java.util.List;
import java.util.Optional;

interface ElevatorSelectionStrategy {
	public Optional<Elevator> getElevator(Request request, List<Elevator> elevators); 
}

class NearestElevatorStrategy implements ElevatorSelectionStrategy {

	@Override
	public Optional<Elevator> getElevator(Request request, List<Elevator> elevators) {
		
		int min_time = Integer.MAX_VALUE;
		Elevator elevator = null;
		
		for (Elevator e : elevators) {
			int score = getEstimatedWaitingTime(e, request);
			if (score < min_time) {
				min_time = score;
				elevator = e;
			}
		}
		
		return Optional.ofNullable(elevator);
	}

	private int getEstimatedWaitingTime(Elevator e, Request request) {
		
		if (e.getDirection() == Direction.UP && e.currentFloor > request.targetFloor) {
			int lastFloor = e.upRequest.last().targetFloor;
			return (lastFloor - e.currentFloor) + lastFloor - request.targetFloor;
		}
		
		if (e.getDirection() == Direction.DOWN && e.currentFloor < request.targetFloor) {
			int lastFloor = e.downRequest.last().targetFloor;
			return (e.currentFloor - lastFloor) + (request.targetFloor - lastFloor);
		}
		

		
		return Math.abs(e.currentFloor - request.targetFloor);
	}
	
}
