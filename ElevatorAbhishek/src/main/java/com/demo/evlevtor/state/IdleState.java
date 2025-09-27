package com.demo.evlevtor.state;

import com.demo.evlevtor.Direction;
import com.demo.evlevtor.Elevator;
import com.demo.evlevtor.Request;

public class IdleState implements ElevatorState {

	@Override
	public void move(Elevator elevator) {
		if (!elevator.upRequest.isEmpty()) {
			elevator.goUp();
		} else if (!elevator.downRequest.isEmpty()) {
			elevator.goDown();
		}
	}

	@Override
	public void addRequest(Elevator elevator, Request request) {
		if (request.direction == Direction.UP) {
			elevator.upRequest.add(request);
		} else if (request.direction == Direction.DOWN) {
			elevator.downRequest.add(request);
		}
	}

	@Override
	public Direction getDirection() {
		// TODO Auto-generated method stub
		return Direction.IDLE;
	}

}
