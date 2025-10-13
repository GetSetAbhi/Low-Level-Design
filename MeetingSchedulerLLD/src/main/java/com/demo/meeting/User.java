package com.demo.meeting;

import java.util.ArrayList;
import java.util.List;

public class User {
	public String name;
	public List<Meeting> meetings;
	
	public User(String name) {
		super();
		this.name = name;
		this.meetings = new ArrayList<>();
	}

	public void receiveMessage(String message) {
		System.out.println("["+this.name + " reminder] : " + message);
	}
}
