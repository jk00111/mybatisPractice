package com.kos.tr.old.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class DateFormatUtil {
	static Logger logger = LogManager.getLogger(DateFormatUtil.class);
	
	public static String ymdFormat(){
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	
	public static String ymFormat(){
		return new SimpleDateFormat("yyyyMM").format(new Date());
	}
	
	public static String yFormat(){
		return new SimpleDateFormat("yyyy").format(new Date());
	}
	
	// 24h:00m
	public static String ksFormat() {
		return new SimpleDateFormat("kk:mm").format(new Date());
	}
	
	public static String ymdFormats(String ymdFlag) {
		logger.info("ymdFormats() >>>>>>>>>>>>");
		
		String y = "";
		
		if ("D".equals(ymdFlag.toUpperCase())) {
			y = DateFormatUtil.ymdFormat();
		}
		
		if ("M".equals(ymdFlag.toUpperCase())) {
			y = DateFormatUtil.ymFormat();
		}
		
		if ("Y".equals(ymdFlag.toUpperCase())) {
			y = DateFormatUtil.yFormat();
		}
		
		if ("N".equals(ymdFlag.toUpperCase())) {
			y = "";
		}
		
		logger.info("y>>>>>>>>>>>>" + y);
		return y;
	}
}
