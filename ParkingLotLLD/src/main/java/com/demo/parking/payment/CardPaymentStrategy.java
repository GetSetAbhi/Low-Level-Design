package com.demo.parking.payment;

public class CardPaymentStrategy implements PaymentStrategy {

	@Override
	public void pay(double amount) {
		System.out.println("Amount " + amount + " paid via card");
	}

}
