package com.demo.amazon.locker;

public enum Size {
	L("Large"), S("Small"), M("Medium"), XL("Extra Large");
	
	private String name;
	
	Size(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
