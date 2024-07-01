package com.kos.tr.old.work.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kos.tr.old.member.service.TerraMemberService;
import com.kos.tr.old.member.vo.TerraMemberVO;
import com.kos.tr.old.work.service.TerraWorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kos.tr.old.common.ChabunUtil;
import com.kos.tr.old.common.DateFormatUtil;
import com.kos.tr.old.common.T_Session;
import com.kos.tr.old.common.chabun.service.TerraChabunService;
import com.kos.tr.old.work.vo.TerraWorkVO;

@Controller
public class TerraWorkController {
	Logger logger = LoggerFactory.getLogger(TerraWorkController.class);
	
	// 컨트롤러 - 채번 서비스 연결
	@Autowired(required=false)
	private TerraChabunService terraChabunService;
	
	// 컨트롤러 - 근태 서비스 연결
	@Autowired(required=false)
	private TerraWorkService terraWorkService;
	
	// 컨트롤러 - 멤버 서비스 연결
	@Autowired(required=false)
	private TerraMemberService terraMemberService;
	
	@Autowired(required=false)
	private TerraWorkController terraWorkController;
	
	// 메인페이지 연결
	@GetMapping("trWorkForm")
	public String trWorkForm(TerraWorkVO twvo, Model model, HttpServletRequest q) {
		logger.info("trWorkForm | 함수 진입 >>> : ");
		T_Session ks = T_Session.getInstance();
		String mnum = ks.getSession(q);
		logger.info("trWorkForm | mnum 세션받기 | {}", mnum);
		model.addAttribute("mnum", mnum);
		return "terraWork/trWorkForm";
	} // end of trWorkForm function
	
	// 근무상태 입력 : 출근하기 한정으로 작동
	@GetMapping("workInsert")
	public String trWorkInsert(TerraWorkVO twvo, Model model, HttpServletRequest q) {
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraWorkController.userData(q);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		logger.info("trWorkInsert 함수 진입 >>> : ");
		
		// 데이터 이동 확인
//		logger.info("trWorkInsert : twvo.getWstate() : " + twvo.getWstate());
		
		List<TerraWorkVO> listS = terraWorkService.trWorkSelect(twvo); 
		int listsize = listS.size();
		
		// 상태 변경버튼 누르면 넘어온 문자열을 대치하는 문자열 숫자로 변경
		// 01:근무, 02:외근, 03:외출, 04:회의, 05:퇴근
		String wstate = twvo.getWstate();
		if(listsize == 0 ) {
			if(wstate != null) {
				if(wstate.equals("출근하기")) {
					wstate = "01,";
				}			
			}else {
				logger.info("근태 정보 없음");
			}
			
			// 채번 : wnum 자동생성 
			String num = terraChabunService.getTerraWorkChabun().getWnum();
//			logger.info("terraBoardInsert num >>> : " + num);
			String wnum = ChabunUtil.getWorkChabun("D", num);
//			logger.info("terraBoardInsert wnum >>> : " + wnum);
			
			twvo.setWstate(wstate);
			twvo.setWnum(wnum);
//			logger.info("trWorkInsert : twvo.getWstate() 치환 >>> :" + twvo.getWstate());
			
			// 현재시각으로 Startdate 만들기 >>> 24시간 표기:분 >>> kk:mm
			String startdate = twvo.getStartdate();
//			logger.info("trWorkInsert startdate (init)>>> : {}", startdate );
			
			startdate = DateFormatUtil.ksFormat();
//			logger.info("trWorkInsert startdate (after)>>> : {}", startdate );
			twvo.setStartdate(startdate);
			
			// 서비스 호출
			int nCnt = terraWorkService.trWorkInsert(twvo);
			if (nCnt > 0) {
				logger.info("terraWorkInsert nCnt >>> : " + nCnt);
				return "terraWork/trWorkInsert";
			}
			return "terraWork/trWorkForm";
		}
		logger.info("trWorkInsert | 이미 출근한상태");
		return "terraWork/trWorkForm";
	} // end of trWorkInsert 
	
	// SELECT | TODAY | 오늘날짜에 해당되는 근태 정보를 조회하여 근무현황 select all 한다
	@GetMapping("workSelect")
	public String trWorkSelect(TerraWorkVO twvo, Model model, HttpServletRequest q) {
		logger.info(" trWorkSelect 함수 진입 >>> : ");
		
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraWorkController.userData(q);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		twvo.setMnum(uList.get(0).getMnum());
		logger.info("trWorkSelect | Mnum >>> : {} ", twvo.getMnum());
		String worknow = ""; // 최종 리턴 변수선언
		
		// DB에서 SELECT로 데이터 제대로 가져왔는지 확인하는 절차
		List<TerraWorkVO> listS = terraWorkService.trWorkSelect(twvo); 
		int listsize = listS.size();
		logger.info("listsize test | {}", listsize);
		
		if(listsize > 0) {
			TerraWorkVO _tvo = listS.get(0);
			logger.info("trWorkSelect | 데이터 : {}", _tvo);
			logger.info("trWorkSelect | Wnum : {}", _tvo.getWnum());
			logger.info("trWorkSelect | Mnum : {}", _tvo.getMnum());
			logger.info("trWorkSelect | Startdate : {}", _tvo.getStartdate());
			logger.info("trWorkSelect | Enddate : {}", _tvo.getEnddate());
			logger.info("trWorkSelect | Insertdate : {}", _tvo.getInsertdate());
			logger.info("trWorkSelect | Updatedate : {}", _tvo.getUpdatedate());
			logger.info("trWorkSelect | Wstate : {}", _tvo.getWstate());
			logger.info("-------------------------------------------------------------");
			
			if(_tvo.getStartdate() != null) { // 출근 안한상태
		
				// startdate 추출 및 분리 => String[] 형태에 담기
				String std = _tvo.getStartdate();
				int s_length = 5;
				String[] s_std = std.split("(?<=\\G.{" + s_length + "})");
				logger.info("trWorkSelect | std : {}", std);
		
				int s_std_l=s_std.length;
				logger.info("trWorkSelect | s_std_l 길이 | {}", s_std_l);
				
				if (s_std_l >0) {
					for(int i=0;i < s_std_l;i++) {// 자른 startdate 추출 및 확인
						int j = i+1;
					logger.info("trWorkSelect | startdate 자름 | "+ j +"번째 | {}", s_std[i]);
					}
				}
				
				// enddate 추출 및 분리 => String[] 형태에 담기
				String endd = _tvo.getEnddate();
				String[] s_endd = endd.split("(?<=\\G.{" + s_length + "})");
				logger.info("trWorkSelect | std : {}", endd);
		
				int s_endd_l=s_endd.length;
				logger.info("trWorkSelect | s_std_l 길이 | {}", s_endd_l);
				
				if (s_endd_l >0) {
					for(int i=0;i < s_endd_l;i++) {// 자른 startdate 추출 및 확인
						int j = i+1;
					logger.info("trWorkSelect | enddate 자름 | "+ j +"번째 | {}", s_endd[i]);
					}
				}
				
				// wstate 추출 및 분리 => String[] 형태에 담기
				String wstate = _tvo.getWstate();
				String[] s_wstate = wstate.split(",");
				
				logger.info("trWorkSelect | wstate : {}", wstate);
		
				int s_wstate_l=s_wstate.length;
				logger.info("trWorkSelect | s_wstate_l 길이 | {}", s_wstate_l);
				
				if (s_wstate_l >0) {
					for(int i=0;i < s_wstate_l;i++) {// 자른 startdate 추출 및 확인
						int j = i+1;
					logger.info("trWorkSelect | wstate 자름 | "+ j +"번째 | {}", s_wstate[i]);
					}
				}
				
				// 넘어가야할 데이터(String) == hh:mm,state,~hh:mm,state,~hh:mm,state,~
				// 넘어가야할 데이터(String) == std1, wstate,~
				// 넘기기전에 데이터들을 String형태로 붙인다.
				int i = 0;
				for (i = 0 ; i < s_wstate_l; i++) {
					//ssesesee형태로 들어가므로 맨 startdate 첫 데이터랑, enddate 마지막 데이터는 별도로 취급한다
					if(i < s_wstate_l -1 && i > 0){ // 첫번째 세트
						
						if(i%2 != 0) { // i 가 홀수일때 -> startdate
							
							int j = (i/2)+1; // 1 = 1 3 =2 5= 3
							
							logger.info("i 홀수일때  | i | {}", i);
							logger.info("i 홀수일때 | j | {}", j);
							logger.info("i 홀수일때 | s_std[{}] | {}", j, s_std[j]);
							worknow += s_std[j] + ",";
							
						}else { // i가 짝수일 때 
							int k = (i-1)/2; // i=2 k=1 | 
					
							if (!s_endd[0].equals("00,")) {
								worknow += s_endd[k] + ",";
								logger.info("i 짝수일때  | i ||| {}", i);
								logger.info("i 짝수일때  | k ||| {}", k);
								logger.info("i 짝수일때  | s_endd[{}] ||| {}", k, s_endd[k]);
								
							}else {
								worknow	+= s_std[k+1] +",";
								logger.info("i 짝수일때  | end=00,| i | {}", i);
								logger.info("i 짝수일때  | end=00,| k | {}", k);
								logger.info("i 짝수일때  | end=00,| s_std[{}] | {}", k+1, s_endd[k+1]);
							}
						}
						worknow += s_wstate[i] + "~";
						
					}else if(i==0){ // 첫번째 startdate만 뽑아온다.
						worknow += s_std[0] + "," + s_wstate[0]+ "~";
						
					}else if(i == s_wstate_l -1) { 
						if(s_wstate[s_wstate_l -1].equals("01") || s_wstate[s_wstate_l -1].equals("05")  ){ // 상태가 01(업무복귀) 또는 05(퇴근)이면 enddate 뽑아옴
							worknow += s_endd[s_endd_l -1] + "," + s_wstate[s_wstate_l -1]+ "~";
							logger.info("마지막 enddate | {} ", s_wstate[s_wstate_l -1]);
							
						}else { // 01(업무복귀)가 아니고  05(퇴근)이아니면  마지막startdate만 뽑아온다
							worknow	+= s_std[s_std.length -1] + "," + s_wstate[s_wstate_l -1]+ "~";
							logger.info("마지막 startdate | {} ", s_wstate[s_wstate_l -1]);
							
						}
					}
				}
				logger.info("trWorkSelect | 데이터 넘기기 전 | {}", worknow);

				_tvo.setDatashift(worknow);
				model.addAttribute("datashift", worknow);
				return "terraWork/trWorkForm";
			} // startdate : null check end
			return "terraWork/trWorkForm";
		} // listsize null check end
		// 데이터 넘기기
		return "terraWork/trWorkForm";
	} // end of trWorkSelect
	
	
	// SELECT | VACATION | 휴가현황 조회
	@GetMapping("workSelectVa")
	@ResponseBody
	public String trWorkSelectVacation(TerraMemberVO tmvo, Model model) {
		logger.info("trWorkSelectVacation 함수 진입 >>> : ");
		
//		logger.info("trWorkSelectVacation | tmvo.getMnum >>> : {} ", tmvo.getMnum());
//		logger.info("trWorkSelectVacation | tmvo.getMholiday >>> : {} ", tmvo.getMholiday());
		
		String restvacation = "";
		List<TerraMemberVO> listV = terraWorkService.trWorkSelectVacation(tmvo);
		int listsize = listV.size();
//		logger.info("trWorkSelectVacation | listsize >>> : {} ", listsize);
		TerraMemberVO _tmvo = null;
		if (listsize > 0 ) {
			 _tmvo = listV.get(0);
		
//		logger.info("trWorkSelectVacation | _tmvo.getMnum() >>> : {} ", _tmvo.getMnum());
//		logger.info("trWorkSelectVacation | _tmvo.getMholiday() >>> : {}", _tmvo.getMholiday());
		
		restvacation = _tmvo.getMholiday();
		return restvacation;
		}
		return restvacation;
	} // end of trWorkSelectVacation function
	
	// SELECT | WORK_HOUR | 올해 근무일수(리스트사이즈), 근무시간 조회
	@GetMapping("workHour")
	@ResponseBody
	public String trWorkSelectHour(TerraWorkVO twvo, Model model) {
		logger.info("trWorkSelectHour 함수 진입 >>> : ");
		
//		logger.info("trWorkSelectHour | twvo.getMnum() >>> : {} ", twvo.getMnum());
		
		String dayNhour = "0"; //  근무일+근무시간
		
		// 데이터 가져오기 
		List<TerraWorkVO> listH = terraWorkService.trWorkSelectHour(twvo);
		int listsize = listH.size();
//		logger.info("trWorkSelectHour | listsize >>> : {} ", listsize);
		
		TerraWorkVO _twvo = null;
		String sd = null;
		String ed = null;
		int sumtime_h = 0;
		int sumtime_m = 0;
		
		if (listsize > 0 ) {
			for(int i = 0; i < listsize; i++) {
				_twvo =listH.get(i);
//				int j = i+1;
//				logger.info("출석데이터 추출 " + j + "번째 startdate| {}", _twvo.getStartdate());
//				logger.info("출석데이터 추출 " + j + "번째 enddate| {}", _twvo.getEnddate());
				
				// 근무시간 구하는 과정
				sd = _twvo.getStartdate();
				if (sd == null) { // 출근전이면 현재시간으로 대체해서 근무시간 구할 것
					// 출근 : sysdate, 퇴근 : sysdate => startdate-enddate=sysdate-sysdate=0
					String now_time1 = DateFormatUtil.ksFormat();
//					logger.info("trWorkSelectHour | sd << 현재시각 | : {}", now_time1 );
					_twvo.setStartdate(now_time1);
				}
				sd = _twvo.getStartdate(); // 세팅한 sd값을 다시 가져옴
				
				String sdsub_h = sd.substring(0, 2);
				String sdsub_m = sd.substring(3, 5);
//				logger.info("startdate 맨처음 입력시간 | 시간 : {} | 분 : {} |", sdsub_h, sdsub_m);
				
				ed = _twvo.getEnddate();
//				logger.info("trWorkSelectHour | ed1 | {}", ed);
				
				if (ed == null) { // 퇴근 안찍었으면 현재시간으로 대체해서 근무시간 구할 것
					String now_time2 = DateFormatUtil.ksFormat();
//					logger.info("trWorkSelectHour | ed << 현재시각 | : {}", now_time2 );
					_twvo.setEnddate(now_time2);
				}
				
				ed = _twvo.getEnddate(); // 세팅한 ed를 다시 불러옴
				
//				logger.info("trWorkSelectHour | ed2 | {}", ed);
				String edsub_h = ed.substring(ed.length()-5, ed.length()-3);
				String edsub_m = ed.substring(ed.length()-2, ed.length());
//				logger.info("enddate 마지막  입력시간 | 시간 : {} | 분 : {} |", edsub_h, edsub_m);
				
				int isd_h = Integer.parseInt(sdsub_h);
				int isd_m = Integer.parseInt(sdsub_m);
				int ied_h = Integer.parseInt(edsub_h);
				int ied_m = Integer.parseInt(edsub_m);
				
				int subtime = ied_h*60 + ied_m - isd_h*60 - isd_m;
				int divtime_h = subtime/60;
				int divtime_m = subtime%60;
//				logger.info(i+1+"번째 | 1일근무시간 | {} 시 {} 분", divtime_h, divtime_m);
				
				sumtime_h += divtime_h;
				sumtime_m += divtime_m;
				
			}
		// 시, 분 환산 최종계산
		sumtime_h += sumtime_m/60;
		sumtime_m = sumtime_m%60;
			
//		logger.info("trWorkSelectHour | 누적 근무시간 | {} 시 {} 분", sumtime_h, sumtime_m);	
//		logger.info("---------------------------------------------------------------");	
		
		String string_h = Integer.toString(sumtime_h);
		String string_m = Integer.toString(sumtime_m);
		dayNhour = listsize + "," + string_h + ","+ string_m;
		logger.info("trWorkSelectHour | 일, 시, 분 1 |{}", dayNhour);
		return dayNhour;
		}
		logger.info("trWorkSelectHour | 일, 시, 분 2 |{}", dayNhour);
		return dayNhour;
	} // end of trWorkSelectVacation function
	
	@GetMapping("workUpdate")
	public String trWorkUpdate(TerraWorkVO twvo, Model model, HttpServletRequest q) {
		
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraWorkController.userData(q);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		logger.info(" trWorkUpdate 함수 진입 >>> : ");
		
		// 데이터 
//		logger.info("trWorkUpdate | twvo.getMnum >>> : {} ", twvo.getMnum());
//		logger.info("trWorkUpdate | twvo.getWstate >>> : {} ", twvo.getWstate());
	
		String wstate = twvo.getWstate();
//		logger.info("trWorkUpdate | wstate >>> : {}", wstate);
		
		// 근무상태 입력정보를 코드로 변환
		if(wstate != null) {
			if(wstate.equals("업무")) {
				wstate = "01";
			}else if(wstate.equals("외근")) {
				wstate = "02";
			}else if(wstate.equals("외출")) {
				wstate = "03";
			}else if(wstate.equals("회의")) {
				wstate = "04";
			}else if(wstate.equals("퇴근하기") || wstate.equals("조퇴")) {
				wstate = "05";
			}		
		}else {
			logger.info("근태 안넘어왔어");
		}
//		logger.info("trWorkUpdate | wstate | 변경 >>> : {}", wstate);
		
		// DB에서데이터 제대로 가져오기
		List<TerraWorkVO> listS = terraWorkService.trWorkSelect(twvo); 
		int listsize = listS.size();
		logger.info("listsize test | {}", listsize);
		
		TerraWorkVO _tvo = null;
		String wc_wstate = null;
		String[] s_wstate = null;
		String lto_chk = null;
		int w_len = 0;
		if(listsize > 0) {		
			_tvo= listS.get(0);
			// 데이터 가공 (1) : 근무상태------------------------------
			wc_wstate = _tvo.getWstate();
			s_wstate = wc_wstate.split(",");
//			logger.info("trWorkUpdate | 근태 꺼냄 : {}", wc_wstate);
			w_len = s_wstate.length;
			lto_chk = s_wstate[w_len-1];
			logger.info("trWorkUpdate | 근태 마지막 데이터 lto_chk | {}", lto_chk);
			
		}

		// 현재시각으로 추출하기 >>> 24시간 표기:분 >>> kk:mm
		String now_time = DateFormatUtil.ksFormat();
//		logger.info("trWorkUpdate | 현재시각 추출하기 | : {}", now_time );
				
//		logger.info("trWorkUpdate | 근태 배열길이 : {}", s_wstate.length);

		// 퇴근이 두번눌리지 않게 체크
		if(listsize>0) {
			if(!wstate.equals(lto_chk)) {
				// 데이터 분리(2) : 시작시간------------------------------------
				String wc_startdate = _tvo.getStartdate();
				
				// 데이터 분리(3) : 종료시간------------------------------------
				String wc_enddate = _tvo.getEnddate();
				logger.info("trWorkUpdate | 종료시간 꺼냄 : {}", wc_enddate);
		
				if (wc_enddate != null ) {
					boolean bool = wc_enddate.equals("00,");
	//				logger.info("enddate 판별 | {}", bool);
					
					if (bool) { // enddate = 00,
						logger.info("분기1 | enddate | 00, ");
						wc_enddate = wc_enddate.replace("00,", "AA");  //<<<<<<<<<<<<여기서 제대로 안바뀜
						logger.info("분기1 | wc_enddate | {}", wc_enddate);
						
					}
				}
	//			logger.info("wc_enddate re | {}", wc_enddate);
				// 꺼내오기 끝=======================================================================
				
				// 조건 판별해서 wstate, startdate, enddate 붙이기===========================>>
				if (s_wstate.length % 2 == 0 && wstate.equals("01")) { // 근태 배열의 길이가짝수고 업무버튼눌렀을 때 ==> enddate추가
					
					// Wstate 붙이기
					String ss = null;
					String st = null;
					String ssstchk= null;
					
					// enddate에 데이터 유무에 따른 분기
					if(!wc_enddate.equals("AA")) {// enddate값이 있을때
						if(lto_chk.equals("05")) { // enddate 마지막값이 퇴근일 때 05,
							ss = wc_wstate;
							st = wc_enddate;
						}else {
							ss = wc_wstate + wstate + ",";
							st = wc_enddate + now_time;
							ssstchk = "enddate있음";
							logger.info("trWorkUpdate | enddate 유무분기1 | "+ ss+"|"+st+"|"+ ssstchk);
						}
					}else {
						//s_startdate
						// enddate값이 없을 때 
						ss = wc_wstate + wstate + ",";
						st = wc_enddate.replace("AA", now_time);
						ssstchk = "enddate없음";
						logger.info("trWorkUpdate | enddate 유무분기2 | "+ ss+"|"+st+"|"+ ssstchk);
					}
					logger.info("trWorkUpdate | enddate 유무분기 최종3 | "+ ss+"|"+st+"|"+ ssstchk);
					_tvo.setWstate(ss);
					_tvo.setEnddate(st);
					
					twvo.setWnum(_tvo.getWnum());
					twvo.setWstate(_tvo.getWstate());
					twvo.setEnddate(_tvo.getEnddate());
					
					// 업데이트(ENDDATE)쿼리 서비스 연결
					int nCnt = terraWorkService.trWorkUpdateE(twvo);
					if (nCnt > 0) {
						logger.info("trWorkUpdate | nCnt | {}", nCnt);
						return "terraWork/trWorkForm";
					}
					return "terraWork/trWorkForm";
						
				}else if (s_wstate.length % 2 != 0 && !wstate.equals("01") ) { // 근태 배열의 길이가 홀수이고 업무버튼이 아닐 때 
					if (!wstate.equals("05")) { // 근태 배열의 길이가 홀수이고 업무&퇴근 버튼이 아닐 때 => startdate 추가
						
						// Wstate 붙이기
						String ss = wc_wstate + wstate + ",";
						String st = wc_startdate + now_time;
						_tvo.setWstate(ss);
						_tvo.setStartdate(st);
						// 쿼리에 보낼 vo 세팅
						twvo.setWnum(_tvo.getWnum());
						twvo.setWstate(_tvo.getWstate());
						twvo.setStartdate(_tvo.getStartdate());
					
						// 업데이트(STARTDATE)쿼리 서비스 연결
						int nCnt = terraWorkService.trWorkUpdateS(twvo);
						if (nCnt > 0) {
							logger.info("trWorkUpdate | nCnt | {}", nCnt);
							return "terraWork/trWorkForm";
						} // startdate 붙이기
						
					}else { // 근태 배열의 길이가 홀수이고 퇴근버튼을 눌렀을 때 => enddate 추가
						// Wstate 붙이기
						
						String ss = wc_wstate + wstate + ",";
						String st = null;
						if(wc_enddate.equals("AA")){
							st = now_time;
						}else{
							st = wc_enddate + now_time;
						}
						
						_tvo.setWstate(ss);
						_tvo.setEnddate(st);
						
						twvo.setWnum(_tvo.getWnum());
						twvo.setWstate(_tvo.getWstate());
						twvo.setEnddate(_tvo.getEnddate());
						
						// 업데이트(ENDDATE)쿼리 서비스 연결
						int nCnt = terraWorkService.trWorkUpdateE(twvo);
						if (nCnt > 0) {
							logger.info("trWorkUpdate | nCnt | {}", nCnt);
							return "terraWork/trWorkForm";
						}
						return "terraWork/trWorkForm";
					} // end | add startdate 
					return "terraWork/trWorkForm";
				} 
				return "terraWork/trWorkForm";
				// 조건 판별해서 wstate, startdate, enddate 붙이기<<=================================
			}
			logger.info("<<<<<<이미 퇴근한 상태>>>>>>");
		}
		logger.info("<<<<<출근 데이터 없음 >>>>>");
		return "terraWork/trWorkForm";
	} // end of trWorkUpdate
	
	// 근태현황 함수
	@GetMapping("workEtc")
	@ResponseBody
	public String trWorkEtc(TerraWorkVO twvo, TerraMemberVO tmvo, Model model) {
		logger.info("trWorkEtc | 함수진입");
		
		// 결근 startdate=null 
		logger.info("trWorkEtc | Mnum >>> : {} ", twvo.getMnum());
		String workEtc = ""; // 최종 리턴 변수선언
		
		// DB에서 SELECT로 데이터 제대로 가져왔는지 확인하는 절차
		List<TerraWorkVO> listS = terraWorkService.trWorkEtc(twvo); 
		int listsize = listS.size();
		logger.info("trWorkEtc | listsize test | {}", listsize);
		
		String wc_wstate = null;
		String wc_startdate = null;
		String wc_enddate = null;
		TerraWorkVO _tvo = null;
		
		int s_length = 5;
		String[] s_wstate = null;
		String[] s_startdate = null;
		String[] s_enddate = null;
		int slen = 0;
		int elen = 0;
		int wlen = 0;
		
		if(listsize > 0) {
			_tvo = listS.get(0);
			logger.info("trWorkEtc | 데이터 : {}", _tvo);
			logger.info("trWorkEtc | Wnum : {}", _tvo.getWnum());
			logger.info("trWorkEtc | Mnum : {}", _tvo.getMnum());
			logger.info("trWorkEtc | Startdate : {}", _tvo.getStartdate());
			logger.info("trWorkEtc | Enddate : {}", _tvo.getEnddate());
			logger.info("trWorkEtc | Wstate : {}", _tvo.getWstate());
			logger.info("-------------------------------------------------------------");
			
			wc_wstate =_tvo.getWstate();
			wc_startdate =_tvo.getStartdate();
			wc_enddate = _tvo.getEnddate();
			
			s_wstate = wc_wstate.split(",");
			s_startdate = wc_startdate.split("(?<=\\G.{" + s_length + "})");
			if(wc_enddate != null) {
			s_enddate = wc_enddate.split("(?<=\\G.{" + s_length + "})");
			elen = s_enddate.length;
			}
			slen = s_startdate.length;
			wlen = s_wstate.length;
			logger.info("trWorkEtc | s_startdate.length| {}", slen);
			logger.info("trWorkEtc | s_enddate.length| {}", elen);
			logger.info("trWorkEtc | s_wstate.length| {}", wlen);
		}
		// 데이터 확인 종료 -----------------------------------------------------
		
		// (1) 지각
		// 출근시 지각 판별용logic
		TerraWorkVO latevo = null;
		String[] s_latestart = null;
		int late_i = 0;
		
		for (int i=0; i < listsize ; i++) { // 지각 for
			int chulgeun = 900;
			latevo=listS.get(i);
			String latestart=latevo.getStartdate(); // listS의 i번째 Startdate
			s_latestart = latestart.split("(?<=\\G.{" + s_length + "})"); // listS의 i번째 Startdate를 5자리로 자름 ( 01:02형태)
			
			String chul_str = s_latestart[0].substring(0, 2)+s_latestart[0].substring(3, 5); // 01:02형태를 0102형태로 변형
			int chul_int = Integer.parseInt(chul_str); // String 0102를 int 0102로 바꿈
			logger.info("출근 : 출근하기 | {} | {} |", chulgeun, chul_int);
			boolean late_b = chulgeun < chul_int;
			logger.info("지각 BOOLEAN | {} |", late_b);
			
			if (late_b) {
				late_i = late_i+1;
			}
			logger.info("지각 카운트 | {} |", late_i);
		} // 지각 for
		String late_s = Integer.toString(late_i);	
		// (1) 지각 끝 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		logger.info("=================================");
		
		// (2) 조퇴
		// 퇴근시 조퇴 판별용logic
		TerraWorkVO earlyvo = null;
		String[] s_earlyend = null;
		int early_i = 0;
		
		for (int i=0; i < listsize ; i++) { // 조퇴 for
			int toegun = 1800;
			earlyvo=listS.get(i);
			String earlyend=earlyvo.getEnddate(); // listS의 i번째 enddate
			
			if(earlyend!=null) {
				s_earlyend = earlyend.split("(?<=\\G.{" + s_length + "})"); // listS의 i번째 enddate를 5자리로 자름 ( 01:02형태)
				int s_earlycnt = s_earlyend.length - 1;
				String chul_str = s_earlyend[s_earlycnt].substring(0, 2)+s_earlyend[s_earlycnt].substring(3, 5); // 01:02형태를 0102형태로 변형
				int chul_int = Integer.parseInt(chul_str); // String 0102를 int 0102로 바꿈
				logger.info("조퇴 : 퇴근하기 | {} | {} |", toegun, chul_int);
				boolean early_b = toegun > chul_int;
				logger.info("조퇴 BOOLEAN | {} |", early_b);
				
				if (early_b) {
					early_i = early_i+1;
				}
			
			}else {
				logger.info("조퇴 | 퇴근미체크");
				early_i = early_i+0;	
			}
			logger.info("조퇴 카운트 | {} |", early_i);	
		} // 지각 for
		String early_s = Integer.toString(early_i);	
		
		// (3) 퇴근미체크
		// row 하나당 근태wstate 마지막데이터 추출, 오늘날짜 이전데이터만 추출해야함.
		// 출근이후 상태변동이 있는 날 | 지나간 날의 마지막 wstate != 05 ==> 퇴근미체크 
		// 출근이후 상태 변동이 없는 날 | enddate = null => 퇴근미체크
		int notchk_i = 0;
		
		TerraWorkVO ncvo = null;
		for(int i = 1;i < listsize; i++) { // 오늘은 제외이므로 i=1부터 시작(i=0 --> 오늘)
			ncvo=listS.get(i);
			String[] swstate = ncvo.getWstate().split(","); // wstate 00 형태로 자르기
			String lastwstate = swstate[swstate.length -1]; // i번째 데이터의 마지막 wstate
			
			if (ncvo.getEnddate() != null) { // 출근이후 상태변동이 있는 날
				if(!lastwstate.equals("05")) { // 마지막 상태변동이 "퇴근"이 아닐경우 미체크+1
					notchk_i = notchk_i+1;
				}else{  // 마지막 상태변동이 "퇴근"일 아닐경우 미체크 유지
					notchk_i = notchk_i+0;
				}
			}else { // 출근이후 상태 변동이 없는 날, enddate가 전부 null
				notchk_i = notchk_i+1;
			}
			
		}
		logger.info("퇴근미체크 | 퇴근미체크 | {}", notchk_i);
		String notchk = Integer.toString(notchk_i);
		
		// (4) 결근
		
		// == (4)-1 | 오늘까지 근무일
		// hiredate 가져오기
		List<TerraMemberVO> tmvo1 = terraMemberService.tMemSelect(tmvo);
		int listsizeH = tmvo1.size();
		logger.info("listsizeH ||||| {} ", listsizeH);
		int abs = 0;
		
		if(listsizeH > 0) {
			Calendar cal = Calendar.getInstance(); 
			String hiredate = tmvo1.get(0).getHiredate();
			logger.info("trWorkEtc | 결근체크 | hiredate추출 | {} ", hiredate); // 입사일(근무 1일차)
			String[] s_hiredate = hiredate.split("-");
			String y_hiredate = s_hiredate[0];
			String m_hiredate = s_hiredate[1];
			String d_hiredate = s_hiredate[2];	

			logger.info("trWorkEtc | 결근체크 | hiredate YYYY추출 | {} ", y_hiredate);
			logger.info("trWorkEtc | 결근체크 | hiredate mm추출 | {} ", m_hiredate);
			logger.info("trWorkEtc | 결근체크 | hiredate dd추출 | {} ", d_hiredate);
			String thisyear = DateFormatUtil.ymdFormats("Y"); // 오늘에 해당하는 연도, YYYY를 추출한다.
			logger.info("trWorkEtc | 결근체크 | 올해  YYYY추출 | {} ", thisyear);
			
			if(y_hiredate.equals(thisyear)) { // 분기1. 입사년도가 올해면  입사일 이후부터 카운트
				
				String today = DateFormatUtil.ymdFormats("D");
				logger.info("trWorkEtc | 결근체크 | 오늘날짜1| {}", today);
				
				int today_i = Integer.parseInt(today);
				int today_y = Integer.parseInt(today.substring(0,4));
				
				
				logger.info("trWorkEtc | 결근체크 | 오늘날짜1 연도추출| {}", today_y);
				
				int startMonth = Integer.parseInt(m_hiredate) -1;
				int startDate = Integer.parseInt(d_hiredate);
				cal.set(today_y, startMonth, startDate); // hiredate 연, 월, 일 세팅
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				logger.info("trWorkEtc | 결근체크 | 첫출근1 cal.getTime()| {}", cal.getTime()); // 20230713
				logger.info("trWorkEtc | 결근체크 | 첫출근1 sdf.format(cal.getTime())| {}", sdf.format(cal.getTime())); // 20230713
				int std = Integer.parseInt(sdf.format(cal.getTime()));
				
				logger.info("trWorkEtc | 결근체크 | 첫출근1 시작 날짜 출력| 와일문 전 | {}", std);// 날짜 출력(체크)
				int i = 0;
				int dayOfWeekNumber = 0;
				
				while(std <= today_i) { // 첫출근일이 오늘날짜보다 크면 종료 // 
					logger.info("trWorkEtc | 결근체크 | 오늘날짜1 시작 날짜 출력 | while {} | {}", i, std);// 날짜 출력(체크)
					dayOfWeekNumber = cal.get(Calendar.DAY_OF_WEEK); // 요일 추출
					logger.info("trWorkEtc | 결근체크 | 오늘날짜1 시작 날짜 요일 출력 | while {} | {}", i, dayOfWeekNumber);
					if ( dayOfWeekNumber != 1 && dayOfWeekNumber != 7 ) { // 주말이아니면  근무일 1증가
						abs = abs +1;
					}
					cal.add(Calendar.DATE, 1); // 날짜 1일 증가
					i = i+1;
					std= Integer.parseInt(sdf.format(cal.getTime()));
				}//end of while
				logger.info("trWorkEtc | 결근체크 | 오늘날짜1 최종 | abs {}", abs);
				
			}else { // 분기2. 입사년도가 올해가 아니면 => 올해 1월 1일부터 카운트
								
				String today = DateFormatUtil.ymdFormats("D");
				logger.info("trWorkEtc | 결근체크 | 오늘날짜2| {}", today);
				
				int today_i = Integer.parseInt(today);
				int today_y = Integer.parseInt(today.substring(0,4));
				logger.info("trWorkEtc | 결근체크 | 오늘날짜2 연도추출| {}", today_y);
				
				int startMonth = Integer.parseInt("00"); //month -1
				int startDate = Integer.parseInt("01");
				cal.set(today_y, startMonth, startDate); // 올해 1월 1일 세팅
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				int std= Integer.parseInt(sdf.format(cal.getTime()));
				logger.info("trWorkEtc | 결근체크 | 오늘날짜2 시작 날짜 출력| 와일문 전 | {}", std);// 날짜 출력(체크)
			
				int i = 0;
				int dayOfWeekNumber = 0;
				
				while(std <= today_i) { // 첫출근일이 오늘날짜보다 크면 종료 // 
					logger.info("trWorkEtc | 결근체크 | 오늘날짜2 시작 날짜 출력 | while {} | {}", i, std);// 날짜 출력(체크)
					dayOfWeekNumber = cal.get(Calendar.DAY_OF_WEEK); // 요일 추출
					logger.info("trWorkEtc | 결근체크 | 오늘날짜2 시작 날짜 요일 출력 | while {} | {}", i, dayOfWeekNumber);
					if ( dayOfWeekNumber != 1 && dayOfWeekNumber != 7 ) { // 주말이아니면  근무일 1증가
						abs = abs +1;
					}
					cal.add(Calendar.DATE, 1); // 날짜 1일 증가
					i = i+1;
					std= Integer.parseInt(sdf.format(cal.getTime()));
				}//end of while
				logger.info("trWorkEtc | 결근체크 | 오늘날짜1 최종 | abs {}", abs);
				
			} // end of if-else	
		} // listsizeH
		
		int TodayCount = abs;
		logger.info("trWorkEtc | 결근체크 | 오늘까지 근무일수 | {}", TodayCount);
		
		// == (4)-2 | 오늘까지 출근건수 카운트 
		int WorkDayCount = 0;
		if(listsize > 0) {
			WorkDayCount = listsize;
		}
		logger.info("trWorkEtc | 결근체크 | 오늘까지 출근한날 | {}", WorkDayCount);
		
		// == (4)-3 | 오늘까지 휴가사용일 카운트 | 기준휴가일(초기상태, 15일) - 남은휴가일 = 휴가 사용일
		int UsedVacation = 0;
		List<TerraMemberVO> listV = terraWorkService.trWorkSelectVacation(tmvo);
		TerraMemberVO _tmvo = null; 
		
		
		if (listsize > 0 ) {
			 _tmvo = listV.get(0);		
			int restvacation = Integer.parseInt(_tmvo.getMholiday()); // 남은 휴가일
			UsedVacation = 15 - restvacation; 
			logger.info("trWorkEtc | 결근체크 | 오늘까지 휴가사용일수 | {}", UsedVacation);
		}
		int sum = 0;
		if (listsize > 0) {
			sum = TodayCount - WorkDayCount + UsedVacation;
		}	
		String absence =Integer.toString(sum); // 최종적으로String나와야 합칠수있음.
		logger.info("trWorkEtc | 결근체크 | 결근일수 | {}", absence);
		
		// 근태현황 | 최종 합치기
		workEtc = late_s + "," + early_s + "," + notchk + "," + absence + ",";	
		logger.info("trWorkEtc | 데이터 넘기기 전 | {} ", workEtc);
		
		return workEtc;
	} // end of trWorkEtc
	
	public List<TerraMemberVO> userData(HttpServletRequest req) {
		
		List<TerraMemberVO> uList = new ArrayList<TerraMemberVO>();
		T_Session ks = T_Session.getInstance();			
		String tnum = ks.getSession(req);
		
		if (tnum == null) {
			logger.info(tnum);
			return  uList;
		}else {
		TerraMemberVO tmvo = new TerraMemberVO();
		tmvo.setMnum(tnum);
		uList = terraMemberService.tMemSelect(tmvo);
		
		return uList;
		
		}
	}
	
} // end of TerraWorkController