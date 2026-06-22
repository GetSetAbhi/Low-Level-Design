package com.demo.locker;

import java.util.UUID;

public class LockerAssignment {

	private String assignemntId;
	private Locker locker;
	private int assignmentCode;
	private Package packege;
	private Status status;
	
	
	public LockerAssignment(Locker locker, int assignmentCode, Package packege) {
		super();
		this.assignemntId = UUID.randomUUID().toString();
		this.locker = locker;
		this.assignmentCode = assignmentCode;
		this.packege = packege;
		this.status = Status.ASSIGNED;
	}


	public String getAssignemntId() {
		return assignemntId;
	}


	public Locker getLocker() {
		return locker;
	}


	public int getAssignmentCode() {
		return assignmentCode;
	}


	public Package getPackege() {
		return packege;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public void markAssigned() {
		this.status = Status.ASSIGNED;
	}
	
	public void markRedeemed() {
		this.status = Status.REDEEMED;
	}
}
