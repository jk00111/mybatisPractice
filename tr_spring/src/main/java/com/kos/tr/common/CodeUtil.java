package com.kos.tr.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public abstract class CodeUtil {

	public static final String[] DOC_TYPE = {"휴가신청서", "기안서", "지출결의서", "사직서"};
	public static final String[] DOC_TYPE_R = {"V", "D", "E", "F"};
	public static final String[] DAPP_VAL = {"관리부", "영업부", "기획부", "생산부", "안전관리부"};
	public static final String[] AAUTH_VAL = {"C", "B", "A", "S"};
	public static final String[] MPOSITION_VAL = {"사원", "대리", "차장", "부장", "이사", "대표이사"};
	
	public static final String[] STEP_VAL = {"대기", "진행", "예정", "완료", "반려"};

	public static final String[] EXPAND_ETYPE = {"식비", "소모품","복리후생", "교통비", "운반비", "숙박비", "기타"};
	public static final String[] EXPAND_ECARD = {"법인카드", "개인카드"};
	
	public static String expandEtype(String type) {
		switch(type) {
		case "01":
			return EXPAND_ETYPE[0];
		case "02":
			return EXPAND_ETYPE[1];
		case "03":
			return EXPAND_ETYPE[2];
		case "04":
			return EXPAND_ETYPE[3];
		case "05":
			return EXPAND_ETYPE[4];
		case "06":
			return EXPAND_ETYPE[5];
		case "07":
			return EXPAND_ETYPE[6];
		default:
			return "-";
			
		}
	}
	
	public static String expandEcard(String type) {
		switch(type) {
		case "C":
			return EXPAND_ECARD[0];
		case "P":
			return EXPAND_ECARD[1];
		default:
			return "-";
		}
	}
	
	public static String nowStep(String level, String app, String tnum) {
		
		ArrayList<String> bList = new ArrayList<String>(Arrays.asList(app.split(",")));
		int f = bList.indexOf(tnum);
		
		System.out.println((f+1) + ":" + level + ":" + tnum);
		
		if (f + 1 == Integer.parseInt(level)) {
			return STEP_VAL[0];
		}else if(0 == Integer.parseInt(level)) {
			return STEP_VAL[4];
		}else if (9 == Integer.parseInt(level)) {
			return STEP_VAL[3];
		}else if (f + 1 < Integer.parseInt(level)) {
			return STEP_VAL[1];
		}else if (f + 1 > Integer.parseInt(level)) {
			return STEP_VAL[2];
		}
		
		return "err";
	}
	
	public static String docType(String type) {
		
		switch (type) {
		case "V":
			return DOC_TYPE[0];
		case "D":
			return DOC_TYPE[1];
		case "E":
			return DOC_TYPE[2];
		case "F":
			return DOC_TYPE[3];
		default:
			return "err";
		}
	}
	
	public static String docTypeR(String type) {
		
		switch (type) {
		case "휴가신청서":
			return DOC_TYPE_R[0];
		case "기안서":
			return DOC_TYPE_R[1];
		case "지출결의서":
			return DOC_TYPE_R[2];
		case "사직서":
			return DOC_TYPE_R[3];
		default:
			return "err";
		}
	}
	
	public static String[] lineSplit(String app) {
		
		String[] array = app.split(",");
		
		for(int i = 0; i < array.length; i++){
			
			if(array[i].equals("undefined") ){
				
				array[i].replace("undefined", " ");
			}
		}
		
		return array;
	}
	
	public static String dappVal(String type) {
		
		switch (type) {
		case "1000":
			return DAPP_VAL[0];
		case "2000":
			return DAPP_VAL[1];
		case "3000":
			return DAPP_VAL[2];
		case "4000":
			return DAPP_VAL[3];
		case "5000":
			return DAPP_VAL[4];
		default:
			return "err";
		}
	}
	
	public static String aauthVal(String type) {
		
		switch (type) {
		case "01":
			return AAUTH_VAL[0];
		case "03":
			return AAUTH_VAL[1];
		case "04":
			return AAUTH_VAL[2];
		case "09":
			return AAUTH_VAL[3];
		default:
			return "err";
		}
	}
	
	public static String mpositionVal(String type) {
		
		switch (type) {
		case "01":
			return MPOSITION_VAL[0];
		case "02":
			return MPOSITION_VAL[1];
		case "03":
			return MPOSITION_VAL[2];
		case "04":
			return MPOSITION_VAL[3];
		case "05":
			return MPOSITION_VAL[4];
		case "06":
			return MPOSITION_VAL[5];
		default:
			return "err";
		}
	}
	
	public static String numPadUtil(String num) {
		
		for (int i = num.length(); i < 2; i++) {
			
			num = "0" + num;
		}
		
		return num;
	}
	
	
	public static String nullChk(String app, String mnum) {
	
		ArrayList<String> bList = new ArrayList<String>(Arrays.asList(app.split(",")));
		int c = bList.indexOf("-");
		int m = bList.indexOf(mnum);
		
		if ((c-1) == m || m == 4) {
			return "end"; 
		}
		return "keep";
	}
	
	public static String hpval(String hp) {
		
		String hpval = hp.substring(0, 3) + "-" + hp.substring(3, 7) + "-" + hp.substring(7);
		
		return hpval ;
	}

	    public static String formatDateTime(String dateTimeString) {
	        try {
	            // 문자열로부터 Date 객체를 생성합니다.
	            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            Date inputDateTime = dateTimeFormat.parse(dateTimeString);

	            // Calendar 인스턴스를 생성하고 현재 날짜와 시간을 가져옵니다.
	            Calendar now = Calendar.getInstance();
	            Calendar inputCalendar = Calendar.getInstance();
	            inputCalendar.setTime(inputDateTime);

	            // 현재 날짜와 비교하여 같은 날이면 날짜 부분만 표시하고, 그렇지 않으면 시간 부분만 표시합니다.
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	            if (now.get(Calendar.YEAR) == inputCalendar.get(Calendar.YEAR)
	                    && now.get(Calendar.MONTH) == inputCalendar.get(Calendar.MONTH)
	                    && now.get(Calendar.DAY_OF_MONTH) == inputCalendar.get(Calendar.DAY_OF_MONTH)) {
	                // 같은 날짜이면 날짜 부분만 표시합니다.
	                return timeFormat.format(inputDateTime);
	            } else {
	                // 다른 날짜이면 시간 부분만 표시합니다.
	                return dateFormat.format(inputDateTime);
	            }
	        } catch (Exception e) {
	            // 날짜 파싱 오류가 발생하면 오류 메시지를 반환합니다.
	            return "Invalid date format";
	        }
	    }
	
	public static void main(String[] args) {
		
		 String formattedDateTime = formatDateTime("2023-07-28 05:07:17");

	     System.out.println(formattedDateTime);
	}
}
