package com.wwb.demo.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class CommonUtils {

	public static Map<String, String> cache = new HashMap<String, String>();
	public static Map<String, Long> codeCache = new HashMap<String, Long>();
    public static final String link = "http://localhost:8080/com-wwb-demo-controller/loginsuccess";
	
	public static String getDate(int delta){
		Calendar calender = Calendar.getInstance();
		String year = String.valueOf(calender.get(Calendar.YEAR));
		String month = String.valueOf(calender.get(Calendar.MONTH) + 1);
		String day = String.valueOf(calender.get(Calendar.DATE - delta));
		return year+month+day;
	}
	
	public static String getRequestParams(Map<String, String> params){
		StringBuilder sb = new StringBuilder();
		int len = params.size();
		for(Map.Entry<String, String> entity: params.entrySet()){
			sb.append(entity.getKey());
			sb.append("=");
			sb.append(entity.getValue());
			if(len-1 > 0){
				sb.append("&");
				len--;
			}
		}
		return sb.toString();
	}
	
	public static String generateEmailLink(String link, String params){
		return link + "?" + params;
	}
	
	public static String generateEmailContent(String linkWithParams){
		return "Please kindly click this link to activate your account in zhaoxiwang.\n<a href='"+ linkWithParams +"'>激活</a>";
	}
	
	// return true if remove success or not find responding record
	// return false if remove failure
	public static boolean removeExpiredCache(String username){
		String code = cache.get(username);
		Long timestamp = getExpiredTime(username);
		Long now = System.currentTimeMillis();
		if(now - timestamp > 86400){
			try{
				cache.remove(username);
				codeCache.remove(username+code);
				return true;					
			}catch(Exception e){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isExpired(String username){
		Long timestamp = getExpiredTime(username);
		Long now = System.currentTimeMillis();
		if(now - timestamp > 86400){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isValid(String username, String code){
		if(StringUtils.isBlank(username) || StringUtils.isBlank(code) || isExpired(username)){
			return false;
		}else if(code.equalsIgnoreCase(cache.get(username))){
			return true;
		}else{
			return false;
		}
	}
	
	private static Long getExpiredTime(String username){
		String code = cache.get(username);
		if(code!=null){
			return codeCache.get(username+code);
		}
		return Long.valueOf(0);
	}
	
}
