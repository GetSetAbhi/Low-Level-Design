package com.test.logger.lld.logger;

import com.test.logger.lld.logger.display.DisplayMode;

public abstract class Logger {
	
	private Logger logger;
	private LogType logType;
	
	public Logger() {
		
	}
	
	public Logger(LogType logType, Logger logger) {
		this.setLogger(logger);
		this.setLogType(logType);
	}
	
	public abstract void display(LogType type, String message, DisplayMode mode);

	public LogType getLogType() {
		return logType;
	}

	public void setLogType(LogType logType) {
		this.logType = logType;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

}
