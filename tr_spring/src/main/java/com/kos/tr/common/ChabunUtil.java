package com.kos.tr.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class ChabunUtil {
	static Logger logger = LogManager.getLogger(ChabunUtil.class);
	
	public static final String BIZ_GUBUN_M 	= "M"; // 회원
	public static final String BIZ_GUBUN_B 	= "B"; // 게시판
	public static final String BIZ_GUBUN_RB = "R"; // 게시판 댓글
	public static final String BIZ_GUBUN_A = "A"; // DRAFT
	public static final String BIZ_GUBUN_W = "W"; // 근태
	
	public static String getMemberChabun(String type, String num) {	
		logger.info("getMemberChabun >>> : " + type + " : " + num);
		
		return BIZ_GUBUN_M.concat(DateFormatUtil.ymdFormats(type)).concat(num);
	}
	
	public static String getBoardChabun(String type, String num) {	
		logger.info("getMemberChabun >>> : " + type + " : " + num);
		String s = BIZ_GUBUN_B.concat(DateFormatUtil.ymdFormats(type)).concat(num);
		logger.info(s);
		return s;
	}
	
	public static String getAppChabunD(String type, String num) {	
		logger.info("getMemberChabun >>> : " + type + " : " + num);
		String s = BIZ_GUBUN_A.concat(DateFormatUtil.ymdFormats(type)).concat(num);
		logger.info(s);
		return s;
	}
	
	public static String getRboardChabun(String type, String num) {
		logger.info("getRboardChabun >>> : " + type + " : " + num);
		
		return BIZ_GUBUN_RB.concat(DateFormatUtil.ymdFormats(type)).concat(num);
	}
	
	// 근태 채번하기 
	public static String getWorkChabun(String type, String num) {
		// 파라미터 제대로 넘어왔는지 확인
		logger.info("getWorkChabun >>> : " + type + num);
		String s = BIZ_GUBUN_W.concat(DateFormatUtil.ymdFormats(type)).concat(num);
		logger.info(s);
		return s;
		
	} // end of getWorkChabun function()
	
	
	public static void main(String[] args) {
		
		String s =ChabunUtil.getMemberChabun("D", "0003");
		logger.info(s);
	}
	
}
