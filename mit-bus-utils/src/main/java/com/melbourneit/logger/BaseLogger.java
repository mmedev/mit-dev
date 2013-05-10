package com.melbourneit.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseLogger {

	private static Logger logger = LoggerFactory.getLogger(BaseLogger.class);
	
	private static BaseLogger baseLogger;
	
	public static BaseLogger getInstance(){
		if(baseLogger == null)
			return baseLogger = new BaseLogger();
		else
			return baseLogger;
	}
	
	public void logException(String message){
		logger.error(message);
	}
	
	public void logException(Throwable e){
		logger.error(e.getCause().toString(),e.getCause());
	}
	
	public void logException(String message, Throwable e){
		logger.error(message,e.getCause());
	}
}
