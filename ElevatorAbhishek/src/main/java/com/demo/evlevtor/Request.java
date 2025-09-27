package com.demo.evlevtor;

public class Request {

	public int targetFloor;
	public RequestType type;
	public Direction direction;
	
	public Request(int floor, Direction direction, RequestType type) {
		this.targetFloor = floor;
		this.type = type;
		this.direction = direction;
	}
}
