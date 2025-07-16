package com.test.logger.lld.logger.display;

public class ConsoleLogger implements DisplayMode {

	@Override
	public void log(String message) {
		System.out.println("[Console] " + message);
	}

}
