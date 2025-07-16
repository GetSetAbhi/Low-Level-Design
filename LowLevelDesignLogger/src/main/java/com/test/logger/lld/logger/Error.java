package com.test.logger.lld.logger;

import com.test.logger.lld.logger.display.DisplayMode;

public class Error extends Logger {

	public Error() {
		setLogType(LogType.ERROR);
	}
	
	public Error(Logger logger) {
		setLogType(LogType.ERROR);
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
