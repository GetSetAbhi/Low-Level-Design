package com.demo.locker;

public class Main {

	public static void main(String[] args) {

		LockerAllocationStrategy strategy = new BestAvailableLocker();
		
		LockerService service = new LockerService(strategy);
		
		for (int i = 0; i < 1; i++) {
			service.createLocker(i + 1, Size.SMALL);
		}
		
		for (int i = 0; i < 1; i++) {
			service.createLocker(i + 3, Size.MEDIUM);
		}
		
		for (int i = 0; i < 1; i++) {
			service.createLocker(i + 5, Size.MEDIUM);
		}
		
		Package package1 = new Package(Size.SMALL);
		
		LockerAssignment assignment = service.assignLocker(package1);
		
		service.openLocker(assignment);
	}

}
