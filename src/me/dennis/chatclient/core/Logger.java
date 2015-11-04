package me.dennis.chatclient.core;

import java.util.Calendar;
import static java.util.Calendar.*;

public class Logger {

	private static Calendar cal = Calendar.getInstance();
	
	private static String getDate() {
		return "[" + (cal.get(HOUR)) + ":" + (cal.get(MINUTE) + ":" + (cal.get(SECOND) + "]"));
	}
	
	public static void info(String s) {
		System.out.println(getDate() + "[INFO] " + s);
	}
	
	public static void err(String s) {
		System.err.println(getDate() + "[SEVERE] " + s);
	}
	
}
