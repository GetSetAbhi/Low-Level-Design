package com.demo.amazon.locker.entities.locker.state;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.demo.amazon.locker.LockerStatus;
import com.demo.amazon.locker.Size;
import com.demo.amazon.locker.entities.OrderPackage;

public class Locker {

	private String lockerId;
	private Size size;
	private OrderPackage orderPackage;
	private Date expireBy;
	private String otp;
	private Lock lock;

	private LockerState currentState;
	private LockerState lockerFreeState;
	private LockerState lockerOccupiedState;
	private LockerState lockerExpiredState;

	public Locker(Size size) {
		this.lockerId = UUID.randomUUID().toString();
		this.size = size;
		this.lockerFreeState = new LockerFreeState();
		this.currentState = this.lockerFreeState;
		this.lockerOccupiedState = new LockerOccupiedState();
		this.lockerExpiredState = new LockerExpiredState();
		this.setLock(new ReentrantLock());
	}

	public void placePackage(OrderPackage orderPackage, String otp) {
		lock.lock();
		try {
			this.currentState.placePackage(this, orderPackage, otp);
		} finally {
			this.lock.unlock();
		}
	}

	public OrderPackage getOrderPackage(String otp) {
		lock.lock();
		try {
			return this.currentState.getPackage(this, otp);
		} finally {
			lock.unlock();
		}
	}

	public boolean isLockerExpired() {
		Date now = new Date(System.currentTimeMillis());
		return this.expireBy != null && (this.expireBy.equals(now) || this.expireBy.before(now));
	}

	void setPackage(OrderPackage orderPackage) {
		this.orderPackage = orderPackage;
	}

	public String getLockerId() {
		return lockerId;
	}

	public LockerStatus getLockerStatus() {
		LockerStatus status = this.currentState.getLockerStatus();
		return status;
	}

	public Size getSize() {
		return size;
	}

	public OrderPackage getOrderPackage() {
		return orderPackage;
	}

	public Date getExpireBy() {
		return expireBy;
	}

	String getOtp() {
		return otp;
	}

	void setOtp(String otp) {
		this.otp = otp;
	}

	void setLockerFreeState() {
		this.expireBy = null;
		this.currentState = this.lockerFreeState;
	}

	void setLockerOccupiedState() {
		this.currentState = this.lockerOccupiedState;
	}

	public void setLockerExpiredState() {
		if (this.orderPackage != null) {
			this.orderPackage = null;
			this.currentState = this.lockerExpiredState;
		}
	}

	void setExpireBy(Date expireBy) {
		this.expireBy = expireBy;
	}

	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}
}
