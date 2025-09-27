package com.demo.evlevtor;

import java.util.List;
import java.util.Optional;

interface ElevatorSelectionStrategy {
	public Optional<Elevator> getElevator(Request request, List<Elevator> elevators); 
}

class NearestElevatorStrategy implements ElevatorSelectionStrategy {

	@Override
	public Optional<Elevator> getElevator(Request request, List<Elevator> elevators) {
		
		int min_score = Integer.MAX_VALUE;
		Elevator elevator = null;
		
		for (Elevator e : elevators) {
			if (isSuitable(e, request)) {
				int diff = Math.abs(request.targetFloor - e.currentFloor);
				if (diff < min_score) {
					min_score = diff;
					elevator = e;
				}
			}
		}
		
		return Optional.ofNullable(elevator);
	}

	private boolean isSuitable(Elevator e, Request request) {
		if (e.getDirection() == Direction.IDLE) {
			return true;
		}
		
		if (e.getDirection() == Direction.UP && request.targetFloor >= e.currentFloor) {
			return true;
		}
		
		if (e.getDirection() == Direction.DOWN && request.targetFloor <= e.currentFloor) {
			return true;
		}
		return false;
	}
	
}
