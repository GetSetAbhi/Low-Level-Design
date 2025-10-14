package com.demo.parking.payment;

public class CashPaymentStrategy implements PaymentStrategy {

	@Override
	public void pay(double amount) {
		System.out.println("Amount " + amount + " paid via cash");
	}

}
