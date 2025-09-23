package com.demo.amazon.locker.services;

import com.demo.amazon.locker.entities.OrderPackage;

public class RefundService {

	public void initiateRefund(OrderPackage pkg) {
		System.out.println("Refund initiated for order : " + pkg.getName());
	}
}
