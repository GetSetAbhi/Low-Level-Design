package com.demo.amazon.locker.entities.locker.state;

import com.demo.amazon.locker.LockerStatus;
import com.demo.amazon.locker.entities.OrderPackage;

public class LockerExpiredState implements LockerState {

	@Override
	public OrderPackage getPackage(Locker locker, String otp) {
		throw new RuntimeException("Otp expired for locker with id " + locker.getLockerId() +" product return in progress.");
	}

	@Override
	public void placePackage(Locker locker, OrderPackage orderPackage, String otp) {
		throw new RuntimeException("Locker with id " + locker.getLockerId() +" can't be used to place product.");
	}

	@Override
	public LockerStatus getLockerStatus() {
		return LockerStatus.EXPIRED;
	}
}
