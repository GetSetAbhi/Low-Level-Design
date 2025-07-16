package com.test.logger.lld.logger;

import com.test.logger.lld.logger.display.DisplayMode;

public class Info extends Logger {

	public Info() {
		
	}
	
	public Info(Logger logger) {
		setLogType(LogType.INFO);
		setLogger(logger);
	}

	@Override
	public void display(LogType type, String message, DisplayMode mode) {
		if (type.equals(getLogType())) {
			String output = type.toString() + ": " + message;
			mode.log(output);
		} else if (getLogger() != null) {
			getLogger().display(type, message, mode);
		}
	}

}
