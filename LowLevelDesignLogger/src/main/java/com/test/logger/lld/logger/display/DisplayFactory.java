package com.test.logger.lld.logger.display;

public class DisplayFactory {
	
	public static DisplayMode getDisplayMode(String type) {
		if (type.equalsIgnoreCase("console")) {
			return new ConsoleLogger();
		}
		return new FileLogger();
	}

}
