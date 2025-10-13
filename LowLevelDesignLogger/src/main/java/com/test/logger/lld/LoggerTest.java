package com.test.logger.lld;

import com.test.logger.lld.logger.LogType;
import com.test.logger.lld.service.LoggingService;

public class LoggerTest {

	public static void main(String[] args) {
		LoggingService logger = new LoggingService();
		
		logger.log(LogType.INFO, "Iro SInene");
		logger.log(LogType.WARNING, "Iro SInene");
		logger.log(LogType.ERROR, "Hamma");
		
		logger.setFileDisplayMode();
		System.out.println("####");
		
		logger.log(LogType.INFO, "Iro SInene");
		logger.log(LogType.WARNING, "Iro SInene");
		logger.log(LogType.ERROR, "Hamma");
	}

}
