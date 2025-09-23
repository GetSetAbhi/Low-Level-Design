package com.demo.amazon.locker.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.demo.amazon.locker.LockerStatus;
import com.demo.amazon.locker.Size;
import com.demo.amazon.locker.entities.OrderPackage;
import com.demo.amazon.locker.entities.locker.state.Locker;

public class LockerService {

	private Map<String, Locker> lockers;
	private Random random;
	private ScheduledExecutorService executorService;
	private RefundService refundService;

	public LockerService(RefundService refundService) {
		this.refundService = refundService;
		this.lockers = new HashMap<>();
		for (int i = 0; i < 5; i++) {
			Locker locker = new Locker(Size.L);
			this.lockers.put(locker.getLockerId(), locker);
		}

		for (int i = 0; i < 5; i++) {
			Locker locker = new Locker(Size.M);
			this.lockers.put(locker.getLockerId(), locker);
		}

		for (int i = 0; i < 5; i++) {
			Locker locker = new Locker(Size.S);
			this.lockers.put(locker.getLockerId(), locker);
		}

		for (int i = 0; i < 5; i++) {
			Locker locker = new Locker(Size.XL);
			this.lockers.put(locker.getLockerId(), locker);
		}
		this.random = new Random();
		this.executorService = Executors.newSingleThreadScheduledExecutor();
		this.startMonitoring();
	}

	public void assignLockerToOrder(OrderPackage orderPackage) {
		Optional<Locker> lockerOpt = this.lockers.values().stream()
				.filter(locker -> locker.getLockerStatus().equals(LockerStatus.FREE)
						&& locker.getSize().equals(orderPackage.getSize()))
				.findFirst();
		if (lockerOpt.isPresent()) {
			Locker locker = lockerOpt.get();
			int otp = this.random.nextInt(100000, 1000000);
			locker.placePackage(orderPackage, String.valueOf(otp));
			System.out.println("Order placed in locker, please note otp " + otp);
		} else {
			throw new RuntimeException("No Locker Available");
		}
	}

	public Locker getLockerById(String id) {
		if (this.lockers.containsKey(id)) {
			return this.lockers.get(id);
		}
		throw new RuntimeException("No Locker with id " + id + " present.");
	}

	public void startMonitoring() {
		this.executorService.scheduleAtFixedRate(this::markExpiredLockers, 0, 5, TimeUnit.MINUTES);
		//this.executorService.execute(() -> markExpiredLockers());
	}

	private void markExpiredLockers() {
		Date now = new Date(System.currentTimeMillis());
		List<Locker> expiredLockers = this.lockers.values().stream()
				.filter(locker -> locker.getLockerStatus().equals(LockerStatus.OCCUPIED)
						&& expiryTimeEqualsOrBeforeCurrentTime(locker.getExpireBy(), now))
				.toList();
		if (!expiredLockers.isEmpty()) {
			for (Locker locker : expiredLockers) {
				OrderPackage order = locker.getOrderPackage();
				this.refundService.initiateRefund(order);
				locker.setLockerExpiredState();
			}
		}
	}

	private boolean expiryTimeEqualsOrBeforeCurrentTime(Date expiryTime, Date currentTime) {
		return expiryTime.equals(currentTime) || expiryTime.before(currentTime);
	}
}
