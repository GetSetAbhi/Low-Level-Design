package com.demo.parking.payment;

public class UpiPaymentStrategy implements PaymentStrategy {

	@Override
	public void pay(double amount) {
		System.out.println("Amount " + amount + " paid via UPI");
	}

}
