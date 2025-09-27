package com.demo.evlevtor.state;

import com.demo.evlevtor.Direction;
import com.demo.evlevtor.Elevator;
import com.demo.evlevtor.Request;


public interface ElevatorState {
	void move(Elevator elevator);
    void addRequest(Elevator elevator, Request request);
    Direction getDirection();
}
