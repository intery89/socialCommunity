package com.wwb.demo.utils;

import java.util.Calendar;

public class CommonUtils {

	public static String getDate(int delta){
		Calendar calender = Calendar.getInstance();
		String year = String.valueOf(calender.get(Calendar.YEAR));
		String month = String.valueOf(calender.get(Calendar.MONTH) + 1);
		String day = String.valueOf(calender.get(Calendar.DATE - delta));
		return year+month+day;
	}
	
}
