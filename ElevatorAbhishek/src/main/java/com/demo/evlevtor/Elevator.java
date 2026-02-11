package com.demo.evlevtor;

import java.util.TreeSet;

import com.demo.evlevtor.state.ElevatorState;

public class Elevator implements Runnable {

	public int elevatorId;
	public int currentFloor;
	public ElevatorState state;
	
	public ElevatorStateContext elevatorStateContext;
	
	public TreeSet<Request> upRequest;
	public TreeSet<Request> downRequest;
	
	public Elevator(int id, ElevatorStateContext context) {
		this.elevatorId = id;
		this.elevatorStateContext = context;
		this.state = context.getIdlestate();
		this.currentFloor = 0;
		
		this.upRequest = new TreeSet<>((a, b) -> a.targetFloor - b.targetFloor);
		this.downRequest = new TreeSet<>((a, b) -> b.targetFloor - a.targetFloor);
	}

	@Override
	public void run() {
		while (true) {
			move();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addRequest(Request request) {
		this.state.addRequest(this, request);
	}
	
	public void move() {
		this.state.move(this);
	}
	
	public void goDown() {
		this.state = this.elevatorStateContext.getDownState();
	}
	
	public void goUp() {
		this.state = this.elevatorStateContext.getUpstate();
	}
	
	public void stay() {
		this.state = this.elevatorStateContext.getIdlestate();
	}
	
	public Direction getDirection() {
		return this.state.getDirection();
	}
	
}
