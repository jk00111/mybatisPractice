package com.kos.tr.old.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class T_Session {

	private static final String T_SESSION_MNUM = "TNUM";
	
//	private static K_Session kSession = null;
//	public static K_Session getInstance() {
//		if(kSession == null) {
//			kSession = new K_Session();
//		}
//		return kSession;
//	}
	
	// 싱글톤 패턴 : Singleton pattern
	private static class LazyHolder{
		public static final T_Session SESSIONLISTENER_INSTANCE = new T_Session();
	}	
	
	public static T_Session getInstance(){
		return LazyHolder.SESSIONLISTENER_INSTANCE;
	}

	public T_Session(){
		super();
	}
	
	public void killSession(final HttpServletRequest hReq){
			
		HttpSession hSession = hReq.getSession(false);			
		if (hSession !=null) {
			hSession.removeAttribute(T_SESSION_MNUM);
			hSession.invalidate();
		}
	}
	
	public boolean setSession(final HttpServletRequest hReq, final String mnum) {
		
		HttpSession hSession = hReq.getSession();
		String t_session_val = (String)hSession.getAttribute(T_SESSION_MNUM);
		int nCnt = 0;
		
		if (t_session_val !=null) {
			boolean b1 = t_session_val.equals(mnum);
			
			if (b1) { 
				nCnt++; 
			}else {
				System.out.println("세션 없음 >>> : ");
			}
		}else {
			System.out.println("세션 없음 >>> : ");
		}
		
		if (nCnt == 0) {
//			hSession.invalidate();
			hSession.setAttribute(T_SESSION_MNUM, mnum);
			hSession.setMaxInactiveInterval(60*60*9); // 9시간 
			
			return false;
		}		
		return true;
	}
	
	public String getSession(final HttpServletRequest hReq){
		
		String strSession = "";		
		HttpSession hSession = hReq.getSession(false);		
		if (hSession !=null) {
			strSession = (String)hSession.getAttribute(T_SESSION_MNUM);
		}							
		return strSession;
	}	
	
}
