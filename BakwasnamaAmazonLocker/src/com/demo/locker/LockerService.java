package com.demo.locker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LockerService {

	public Map<Size, List<Locker>> lockers;
	private LockerAllocationStrategy strategy;
	private Map<Integer, Locker> assignedLockers;
	
	public LockerService(LockerAllocationStrategy strategy) {
		this.lockers = new HashMap<>();
		this.strategy = strategy;
		this.assignedLockers = new HashMap<>();
	}
	
	public void createLocker(int id, Size size) {
		List<Locker> lockerList = this.lockers.computeIfAbsent(size, k -> new ArrayList<>());
		lockerList.add(new Locker(id, size));
	}
	
	public LockerAssignment assignLocker(Package packege) {
		
		Locker locker = this.strategy.getSuitableLocker(lockers, packege);
		
		if (locker == null) {
			throw new IllegalArgumentException("No SUitable Locker Available");
		}
		
		locker.markOccupied();
		int assignmentCode = UUID.randomUUID().version();
		LockerAssignment assignment = new LockerAssignment(locker, assignmentCode, packege);
		this.assignedLockers.put(assignmentCode, locker);
		System.out.println("Locker assigned Successfully");
		return assignment;
	}
	
	public void openLocker(LockerAssignment assignment) {
		if (assignment.getStatus() == Status.REDEEMED) {
			throw new IllegalStateException("This package is already retrieved");
		}
		int code = assignment.getAssignmentCode();
		if (!assignedLockers.containsKey(code)) {
			throw new IllegalArgumentException("This key is invalid");
		}
		Locker locker = this.assignedLockers.get(code);
		
		if (locker.getLockerId() != assignment.getLocker().getLockerId()) {
			throw new IllegalAccessError("You are opening incorrect locker");
		}
		assignment.markRedeemed();
		locker.markAvailable();
		this.assignedLockers.remove(code);
		System.out.println("Successfully retrieved the package");
	}
}
