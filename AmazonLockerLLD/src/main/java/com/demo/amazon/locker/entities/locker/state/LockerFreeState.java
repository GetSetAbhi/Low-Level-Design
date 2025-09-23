package com.demo.amazon.locker.entities.locker.state;

import java.util.Date;

import com.demo.amazon.locker.LockerStatus;
import com.demo.amazon.locker.entities.OrderPackage;

public class LockerFreeState implements LockerState {

	@Override
	public OrderPackage getPackage(Locker locker, String otp) {
		throw new RuntimeException("Locker with id " + locker.getLockerId() + " doesn't have any package.");
	}

	@Override
	public void placePackage(Locker locker, OrderPackage orderPackage, String otp) {
		locker.setPackage(orderPackage);
		locker.setOtp(otp);
		long threeDaysInMillis = 3L * 24 * 60 * 60 * 1000;
		Date date = new Date(System.currentTimeMillis() + threeDaysInMillis);
		locker.setExpireBy(date);
		System.out.println("Package with id " + orderPackage.getOrderPackageId() + " and name " + orderPackage.getName()
				+ " placed in locker with id " + locker.getLockerId());
		locker.setLockerOccupiedState();
	}

	@Override
	public LockerStatus getLockerStatus() {
		return LockerStatus.FREE;
	}
}
