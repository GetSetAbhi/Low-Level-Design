package com.demo.evlevtor.state;

import com.demo.evlevtor.Direction;
import com.demo.evlevtor.Elevator;
import com.demo.evlevtor.Request;
import com.demo.evlevtor.RequestType;

public class DownState implements ElevatorState {

	@Override
	public void move(Elevator elevator) {
		if (elevator.downRequest.isEmpty()) {
			elevator.stay();
			return;
		}
		
		elevator.currentFloor -= 1;
		System.out.println(elevator.elevatorId + " is now at floor " + elevator.currentFloor);
		Request request = elevator.downRequest.first();
		if (request.targetFloor == elevator.currentFloor) {
			elevator.downRequest.removeFirst();
			if (request.type == RequestType.EXTERNAL) {
				System.out.println(elevator.elevatorId + " taking passenger at floor " + elevator.currentFloor);
			} else {
				System.out.println(elevator.elevatorId + " dropping passenger at floor " + elevator.currentFloor);
			}
		}
	}

	@Override
	public void addRequest(Elevator elevator, Request request) {
		if (request.targetFloor <= elevator.currentFloor) {
			elevator.downRequest.add(request);
		} else {
			elevator.upRequest.add(request);
		}
	}

	@Override
	public Direction getDirection() {
		// TODO Auto-generated method stub
		return Direction.DOWN;
	}

}
