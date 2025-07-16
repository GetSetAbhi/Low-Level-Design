package com.test.logger.lld.logger;

import com.test.logger.lld.logger.display.DisplayMode;

public class Warning extends Logger {
	
	public Warning() {
		
	}
	
	public Warning(Logger logger) {
		setLogType(LogType.WARNING);
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
