package com.demo.airline;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		FlightService service = new FlightService();
		BookingService bookingService = new BookingService(service);
		
		User abhi = new User("Abhishek");
		User palak = new User("Palak");
		
		service.getFlights("IXJ", "HYD").forEach(flight -> {
			System.out.println(flight);
		});
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter flights");
		String enterFlightId = scan.nextLine();
		System.out.println("FLight Id entered: " + enterFlightId);
		System.out.println("Seat Labels comma separated: ");
		String seatLabels = scan.nextLine();
		System.out.println("Seats Selected: " + enterFlightId);
		
		String[] seats = seatLabels.split(",");
		
		bookingService.createBooking(enterFlightId, Arrays.asList(seats), abhi);
		

		service.getFlights("IXJ", "HYD").forEach(flight -> {
			System.out.println(flight);
		});
		
		scan.close();
	}

}
