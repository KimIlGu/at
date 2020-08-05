package com.sbs.kig.at.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

public class Util {

	public static String getNowDateStr() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = date.format(cal.getTime());
		return dateStr;
	}
	
	public static boolean empty(Object obj) {
		if (obj == null) {
			return true;
		}

		if (obj instanceof String) {
			return ((String) obj).trim().length() == 0;
		}
		return true;
	}
}
