package com.test.logger.lld.service;

import com.test.logger.lld.logger.Error;
import com.test.logger.lld.logger.Info;
import com.test.logger.lld.logger.LogType;
import com.test.logger.lld.logger.Logger;
import com.test.logger.lld.logger.Warning;
import com.test.logger.lld.logger.display.DisplayFactory;
import com.test.logger.lld.logger.display.DisplayMode;

public class LoggingService {

	private DisplayMode currentDisplayMode;
	private DisplayMode fileDisplayMode;
	private DisplayMode consoleDisplayMode;
	private Logger logger;

	public LoggingService() {
		this.fileDisplayMode = DisplayFactory.getDisplayMode("file");
		this.consoleDisplayMode = DisplayFactory.getDisplayMode("console");
		this.currentDisplayMode = this.consoleDisplayMode;

		this.logger = new Info();
		Logger warning = new Warning();
		Logger error = new Error();
		logger.setNext(warning);
		warning.setNext(error);
	}

	public void log(LogType type, String message) {
		this.logger.display(type, message, this.currentDisplayMode);
	}

	public void setFileDisplayMode() {
		this.currentDisplayMode = this.fileDisplayMode;
	}

	public void setConsoleDisplayMode() {
		this.currentDisplayMode = this.consoleDisplayMode;
	}
}
