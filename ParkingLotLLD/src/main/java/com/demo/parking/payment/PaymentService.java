package com.demo.parking.payment;

import com.demo.parking.ParkingTicket;
import com.demo.parking.PaymentStatus;

public class PaymentService {

	private PaymentStrategy card;
	private PaymentStrategy upi;
	private PaymentStrategy cash;
	
	public PaymentService() {
		this.card = new CardPaymentStrategy();
		this.cash = new CashPaymentStrategy();
		this.upi = new UpiPaymentStrategy();
	}
	
	public void pay(double amount, PaymentMode paymentMode, ParkingTicket ticket) {
		PaymentStrategy paymentStrategy = this.getPaymentStrategy(paymentMode);
		paymentStrategy.pay(amount);
		ticket.setPaymentStatus(PaymentStatus.PAID);
	}
	
	private PaymentStrategy getPaymentStrategy(PaymentMode paymentMode) {
		if (paymentMode.equals(PaymentMode.UPI)) {
			return this.upi;
		} else if (paymentMode.equals(PaymentMode.CREDIT_CARD)) {
			return this.card;
		}
		return this.cash;
	}
}
