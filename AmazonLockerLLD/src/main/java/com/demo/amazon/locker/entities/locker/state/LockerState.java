package com.demo.amazon.locker.entities.locker.state;

import com.demo.amazon.locker.LockerStatus;
import com.demo.amazon.locker.entities.OrderPackage;

public interface LockerState {

	public OrderPackage getPackage(Locker locker, String otp);
	public void placePackage(Locker locker, OrderPackage orderPackage, String otp);
	public LockerStatus getLockerStatus();
}
