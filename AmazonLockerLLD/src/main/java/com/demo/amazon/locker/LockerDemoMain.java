package com.demo.amazon.locker;

import java.util.Scanner;

import com.demo.amazon.locker.entities.OrderPackage;
import com.demo.amazon.locker.entities.User;
import com.demo.amazon.locker.entities.locker.state.Locker;
import com.demo.amazon.locker.services.LockerService;
import com.demo.amazon.locker.services.RefundService;

public class LockerDemoMain {

	public static void main(String[] args) {
		User user = new User("Abhishek");
		
		OrderPackage opack = new OrderPackage("Labubu", Size.M);
		opack.setUser(user);
		
		LockerService service = new LockerService(new RefundService());
		
		service.assignLockerToOrder(opack);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Locker ID : ");
		String lockerId = scan.nextLine();
		
		Locker locker = service.getLockerById(lockerId);
		
		System.out.println("Enter otp : ");
		String otp = scan.nextLine();
		locker.getOrderPackage(otp);
		
		System.out.println("#########");
		System.out.println("Enter otp again to check: ");
		otp = scan.nextLine();
		locker.getOrderPackage(otp);
		
		scan.close();
	}

}
