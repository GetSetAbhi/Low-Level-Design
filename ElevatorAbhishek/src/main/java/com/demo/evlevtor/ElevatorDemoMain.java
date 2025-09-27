package com.demo.evlevtor;

public class ElevatorDemoMain {

	public static void main(String[] args) {

		ElevatorSystem system = new ElevatorSystem();
		system.start();
		
		Elevator elevator = system.addExternalRequest(5, Direction.UP);
		
		if (elevator != null) {
			System.out.println(elevator.elevatorId + " is assigned");
			system.addInternalRequest(elevator, 3, Direction.DOWN);
		}
	}

}
