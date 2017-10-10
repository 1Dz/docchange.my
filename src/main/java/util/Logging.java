package util;

import org.jboss.logging.Logger;
import org.apache.commons.logging.impl.Log4JLogger;

public class Logging {
	
	private static final Logger LOGGER = Logger.getLogger(Log4JLogger.class);

	public static void logError(Throwable e){
		LOGGER.error(e.getMessage(), e);
	}
	
	public static void logEvent(String message){
		LOGGER.info(message);
	}
}
