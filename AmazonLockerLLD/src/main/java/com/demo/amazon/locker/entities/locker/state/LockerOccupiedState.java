package com.demo.amazon.locker.entities.locker.state;

import com.demo.amazon.locker.LockerStatus;
import com.demo.amazon.locker.entities.OrderPackage;

public class LockerOccupiedState implements LockerState {

	@Override
	public OrderPackage getPackage(Locker locker, String otp) {
		if (locker.getOtp().equals(otp)) {
			System.out.println("Locker with id : " + locker.getLockerId() + " is unlocked");
			locker.setExpireBy(null);
			locker.setLockerFreeState();
			locker.setOtp(null);
			return locker.getOrderPackage();
		}
		throw new RuntimeException("Wrong otp for Locker with id " + locker.getLockerId() +".");
	}

	@Override
	public void placePackage(Locker locker, OrderPackage orderPackage, String otp) {
		throw new RuntimeException("Locker with id " + locker.getLockerId() +" is occupied.");
	}

	@Override
	public LockerStatus getLockerStatus() {
		return LockerStatus.OCCUPIED;
	}
}
