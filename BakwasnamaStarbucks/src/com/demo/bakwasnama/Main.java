package com.demo.bakwasnama;

public class Main {

	public static void main(String[] args) {
		BillingService billingService = new BillingService();
		
		// 10% discount
		PricingStrategy percentageDiscountStrategy = new PercentageDiscountPricingStrategy(10);
		
		// flat 100 Rs Off
		PricingStrategy fixedAmountStrategy = new FixedAmountDiscountPricingStrategy(100);
		
		OrderService orderService = new OrderService();
		
		Order order1 = orderService.createOrder();
		orderService.addItemToOrder(order1, new MenuItem("Cappuccino", 150), 2);
		orderService.addItemToOrder(order1, new MenuItem("Espresso", 100), 1);
		orderService.addItemToOrder(order1, new MenuItem("Pizza Roll", 200), 1);
		
		double total1 = billingService.calculateTotal(order1, percentageDiscountStrategy);
		
		System.out.println("Total for Order 1 with 10% discount: " + total1);
		
	}

}
