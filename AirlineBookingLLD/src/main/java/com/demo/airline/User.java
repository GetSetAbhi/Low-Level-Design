package com.demo.airline;

import java.util.ArrayList;
import java.util.List;

public class User {

	public String name;
	public List<Booking> bookings;
	
	public User(String name) {
		super();
		this.name = name;
		this.bookings = new ArrayList<>();
	}
}
