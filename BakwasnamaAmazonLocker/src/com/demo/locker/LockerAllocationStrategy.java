package com.demo.locker;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface LockerAllocationStrategy {

	public Locker getSuitableLocker(Map<Size, List<Locker>> lockers, Package packege);
}

class BestAvailableLocker implements LockerAllocationStrategy {

	@Override
	public Locker getSuitableLocker(Map<Size, List<Locker>> lockers, Package packege) {
		
		Stream<Locker> lockerStream = lockers.keySet().stream().map(k -> lockers.get(k)).flatMap(l -> l.stream());
		List<Locker> matchingSizeLockers = lockerStream.filter(l -> l.getSize() == packege.getSize()).toList();

		Locker availableLocker = null;
		for (Locker locker : matchingSizeLockers) {
			if (locker.isAvailable()) {
				availableLocker = locker;
				break;
			}
		}
		
		return availableLocker;
	}
	
}