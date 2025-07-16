package com.test.logger.lld.logger.display;

public class FileLogger implements DisplayMode {

	@Override
	public void log(String message) {
		System.out.println("[File] " + message);
	}

}
