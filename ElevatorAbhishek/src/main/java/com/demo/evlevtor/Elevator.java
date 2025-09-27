package com.demo.evlevtor;

import java.util.TreeSet;

import com.demo.evlevtor.state.DownState;
import com.demo.evlevtor.state.ElevatorState;
import com.demo.evlevtor.state.IdleState;
import com.demo.evlevtor.state.Upstate;

public class Elevator implements Runnable {

	public int elevatorId;
	public int currentFloor;
	public ElevatorState state;
	
	public ElevatorState upstate;
	public ElevatorState downState;
	public ElevatorState idlestate;
	
	public TreeSet<Request> upRequest;
	public TreeSet<Request> downRequest;
	
	public Elevator(int id) {
		this.elevatorId = id;
		this.idlestate = new IdleState();
		this.upstate = new Upstate();
		this.downState = new DownState();
		this.state = this.idlestate;
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
		this.state = this.downState;
	}
	
	public void goUp() {
		this.state = this.upstate;
	}
	
	public void stay() {
		this.state = this.idlestate;
	}
	
	public Direction getDirection() {
		return this.state.getDirection();
	}
	
}
