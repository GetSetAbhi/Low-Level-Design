package com.test.logger.lld;

import com.test.logger.lld.logger.display.DisplayMode;

import com.test.logger.lld.logger.Error;
import com.test.logger.lld.logger.Info;
import com.test.logger.lld.logger.LogType;
import com.test.logger.lld.logger.Logger;
import com.test.logger.lld.logger.Warning;
import com.test.logger.lld.logger.display.DisplayFactory;

public class LoggerTest {

	public static void main(String[] args) {
		Logger logger = new Info(new Warning(new Error()));
		
		DisplayMode mode = DisplayFactory.getDisplayMode("console");
		
		logger.display(LogType.INFO, "Iro SInene", mode);
		logger.display(LogType.WARNING, "Iro SInene", mode);
		logger.display(LogType.ERROR, "Hamma", mode);
	}

}
