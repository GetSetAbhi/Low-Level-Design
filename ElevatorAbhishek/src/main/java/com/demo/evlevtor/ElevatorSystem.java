package com.demo.evlevtor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ElevatorSystem {
	
	public ElevatorSelectionStrategy elevatorSelectionStrategy;
	public ExecutorService executorService = Executors.newFixedThreadPool(3);
	List<Elevator> elevators;
	
	public ElevatorSystem() {
		this.elevatorSelectionStrategy = new NearestElevatorStrategy();
		this.elevators = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			this.elevators.add(new Elevator(i + 1));
		}
	}

	public Elevator addExternalRequest(int floor, Direction direction) {
		Request request = new Request(floor, direction, RequestType.EXTERNAL);
		Optional<Elevator> elevator = this.elevatorSelectionStrategy.getElevator(request, elevators);
		if (elevator.isPresent()) {
			Elevator e = elevator.get();
			e.addRequest(request);
			return e;
		}
		return null;
	}
	
	public void addInternalRequest(Elevator elevator, int floor, Direction direction) {
		Request request = new Request(floor, direction, RequestType.INTERNAL);
		elevator.addRequest(request);
	}
	
	public void start() {
		for (Iterator iterator = elevators.iterator(); iterator.hasNext();) {
			Elevator elevator = (Elevator) iterator.next();
			this.executorService.execute(elevator);
		}
	}
}
