package com.test.logger.lld.logger;

import com.test.logger.lld.logger.display.DisplayMode;

public abstract class Logger {

	private Logger next;

	public Logger() {

	}

	public void display(LogType type, String message, DisplayMode mode) {
		if (this.getLogType() == type) {
			mode.log(message);
		} else if (this.next != null) {
			this.next.display(type, message, mode);
		}
	}

	public abstract LogType getLogType();

	public Logger getLogger() {
		return next;
	}

	public void setNext(Logger next) {
		this.next = next;
	}

}
