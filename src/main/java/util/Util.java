package util;

import java.time.LocalDateTime;

public class Util {
	
	public static LocalDateTime date;
	
	public static LocalDateTime getNow() {
		date = LocalDateTime.now();
		return date;
	}
	
	public static String getDateStringNow() {
		date = LocalDateTime.now();
		return String.format("%d.%d.%d - %d:%d", date.getDayOfMonth(), date.getMonthValue(), date.getYear(), date.getHour(), date.getMinute());
	}
	
	public static String getDateString() {
		date = LocalDateTime.now();
		return String.format("%d.%d.%d", date.getDayOfMonth(), date.getMonthValue(), date.getYear());
	}
}
