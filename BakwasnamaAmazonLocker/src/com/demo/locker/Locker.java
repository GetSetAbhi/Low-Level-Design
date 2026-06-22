package com.demo.locker;

public class Locker {
	private int lockerId;
	private boolean isOccupied;
	private Size size;
	
	
	public Locker(int lockerId, Size size) {
		super();
		this.lockerId = lockerId;
		this.isOccupied = false;
		this.size = size;
	}
	
	public void markOccupied() {
		if (isOccupied == true) {
			throw new IllegalStateException("The Locker is already occupied");
		}
		this.isOccupied = true;
	}
	
	public void markAvailable() {
		if (isOccupied == false) {
			throw new IllegalStateException("The Locker is already unoccupied");
		}
		this.isOccupied = false;
	}
	
	
	public boolean isAvailable() {
		return !this.isOccupied;
	}
	
	public int getLockerId() {
		return this.lockerId;
	}

	public Size getSize() {
		return size;
	}
	
	
}
