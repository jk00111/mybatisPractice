package com.kos.tr.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kos.tr.app.service.TerraAppService;
import com.kos.tr.app.vo.AppDocVO;
import com.kos.tr.app.vo.AppDraftVO;
import com.kos.tr.app.vo.AppExpandVO;
import com.kos.tr.app.vo.AppVacationVO;
import com.kos.tr.common.ChabunUtil;
import com.kos.tr.common.CodeUtil;
import com.kos.tr.common.CommonUtils;
import com.kos.tr.common.FileUploadUtil;
import com.kos.tr.common.T_Session;
import com.kos.tr.common.chabun.service.TerraChabunService;
import com.kos.tr.member.service.TerraMemberService;
import com.kos.tr.member.vo.TerraMemberVO;

@Controller
public class TerraAppController {

	Logger logger = LogManager.getLogger(TerraAppController.class);
	
	@Autowired(required=false)
	private TerraAppController terraAppController;
	
	@Autowired(required=false)
	private TerraMemberService terraMemberService;
	
	@Autowired(required=false)
	private TerraAppService terraAppService;
	
	@Autowired(required=false)
	private TerraChabunService terraChabunService;
	
	@GetMapping("appInsertDoc")
	public String appInsertDoc(Model model, HttpServletRequest req) {
		
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraAppController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
				
		return"terraApproval/docSelect";
	}
	
	@GetMapping("appInsertForm/{type}")
	public String appInsertForm(@PathVariable String type, TerraMemberVO tmvo, Model model, HttpServletRequest req) {
		
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraAppController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);

		// 결재선 정보
		List<TerraMemberVO> aList = terraMemberService.tMemSelectApp(tmvo);		
		model.addAttribute("aList", aList);
		
		if (type.equals("D")) {
			return "terraApproval/trDraftForm";
		}else if(type.equals("V")) {
			return "terraApproval/trVacationForm";
		}else if(type.equals("E")) {
			return "terraApproval/trExpandForm";
		}
		return "";
	}
	
	@GetMapping("/appSelect/{type}")
	public String appSelectType(@PathVariable String type, HttpServletRequest req, AppDocVO advo, Model model) {
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraAppController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		
		// 나를 대결자로 설정한 직원 정보
		logger.info("aaaa >>> ");
		List<TerraMemberVO> pList = terraMemberService.tMemSelectProxy(uList.get(0));
		if (pList.size() > 0 && pList != null) {		
			if (pList.get(0).getMcuryn().equals("N")) {
				logger.info(pList.get(0).getMcuryn());
				
				// 대결권한을 준 사람의 결재정보 조회
				advo.setMnum(pList.get(0).getMnum());
				pList = terraMemberService.tMemSelectProxy(uList.get(0));
				List<AppDocVO> prList = terraAppService.appSelectAll(advo);
				model.addAttribute("prList", prList);
				model.addAttribute("pList", pList);
			}
		}
		advo.setMnum(uList.get(0).getMnum());
		List<AppDocVO> aList = terraAppService.appSelectAll(advo);
		List<AppDocVO> cList = new ArrayList<AppDocVO>();
		
		for (int i = 0; i < aList.size(); i++) {
			
			AppDocVO advo_s = new AppDocVO();
			advo_s = aList.get(i);
			
			int level = Integer.parseInt(advo_s.getAlevel());
			ArrayList<String> bList = new ArrayList<String>(Arrays.asList(advo_s.getAapp().split(",")));
			int f = bList.indexOf(uList.get(0).getMnum());
						
			if (level < 6 && f != -1) {	
				if (type.equals("A")) {
					logger.info(advo_s.getAnum() + " >>> 전체"); 
					cList.add(advo_s);
				}else {
					if (f + 1 == level && type.equals("W")) {
						logger.info(advo_s.getAnum() + ">>>> 대기");
						cList.add(advo_s);
					}else if (f + 1 < level && type.equals("K")) {
						logger.info(advo_s.getAnum() + ">>>> 진행");
						cList.add(advo_s);
					}else if (f + 1 > level && type.equals("P")) {
						logger.info(advo_s.getAnum() + ">>>> 예정");
						cList.add(advo_s);
					}
				}
			}
		}
		model.addAttribute("cList", cList);
		
		switch (type) {
			// 현재 진행중  > 전체
			case "A":
				return "terraApproval/appSelect_All";
			// 현재 진행중 > 대기
			case "W":
				return "terraApproval/appSelect_W";
			// 현재 진행중 > 진행				
			case "K":
				return "terraApproval/appSelect_K";
			// 현재 진행중 > 예정	
			case "P":
				return "terraApproval/appSelect_P";
			default:
				return "/";	
		}
	}
	
	// 문서함 >> 기안
	@GetMapping("/appSelect/D")
	public String appSelectDraft(HttpServletRequest req, AppDocVO dvo, Model model) {
		
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraAppController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		int pageSize = CommonUtils.BOARD_PAGE_SIZE;
		int groupSize = CommonUtils.BOARD_GROUP_SIZE;
		int curPage = CommonUtils.BOARD_CUR_PAGE;
		int totalCount = CommonUtils.BOARD_TOTAL_COUNT;
		
		if (dvo.getCurPage() != null){
			curPage = Integer.parseInt(dvo.getCurPage());
		}
		
		dvo.setPageSize(String.valueOf(pageSize));
		dvo.setGroupSize(String.valueOf(groupSize));
		dvo.setCurPage(String.valueOf(curPage));
		dvo.setTotalCount(String.valueOf(totalCount));
		
		model.addAttribute("pagingdvo", dvo);	
		
		dvo.setMnum(uList.get(0).getMnum());
		logger.info(dvo.getMnum());
		
		List<AppDocVO> cList = terraAppService.appSelectDraft(dvo);
		model.addAttribute("cList", cList);
		
		return "terraApproval/appSelect_D";
	}
	
	@GetMapping("/appSelect/AA")
	public String appSelectAA(HttpServletRequest req, AppDocVO dvo, Model model) {
		// 결재단계 무관한 서비스-쿼리 만들어야
		
		// 로그인 후 사용자 정보 찾아옴
		List<TerraMemberVO> uList = terraAppController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		dvo.setMnum(uList.get(0).getMnum()); 
		logger.info(dvo.getMnum());
		
		List<AppDocVO> cList = terraAppService.appSelectAll(dvo);
		model.addAttribute("cList", cList);
		
		return "terraApproval/appSelect_AA";
	}
	
	// 문서함 >> 참조
	@GetMapping("/appSelect/R")
	public String appSelectRef(HttpServletRequest req, AppDocVO dvo, Model model) {
		
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraAppController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		int pageSize = CommonUtils.BOARD_PAGE_SIZE;
		int groupSize = CommonUtils.BOARD_GROUP_SIZE;
		int curPage = CommonUtils.BOARD_CUR_PAGE;
		int totalCount = CommonUtils.BOARD_TOTAL_COUNT;
		
		if (dvo.getCurPage() != null){
			curPage = Integer.parseInt(dvo.getCurPage());
		}
		
		dvo.setPageSize(String.valueOf(pageSize));
		dvo.setGroupSize(String.valueOf(groupSize));
		dvo.setCurPage(String.valueOf(curPage));
		dvo.setTotalCount(String.valueOf(totalCount));
		
		model.addAttribute("pagingdvo", dvo);	
		
		dvo.setAref(uList.get(0).getMnum());
		
		List<AppDocVO> cList = terraAppService.appSelectRef(dvo);
		model.addAttribute("cList", cList);
		
		return "terraApproval/appSelect_R";
	}
	
	// 문서함 >> 전체 
	@GetMapping("/appSelect/AllDoc")
	public String appSelectAllDoc(HttpServletRequest req, AppDocVO dvo, Model model) {
		
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraAppController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		int pageSize = CommonUtils.BOARD_PAGE_SIZE;
		int groupSize = CommonUtils.BOARD_GROUP_SIZE;
		int curPage = CommonUtils.BOARD_CUR_PAGE;
		int totalCount = CommonUtils.BOARD_TOTAL_COUNT;
		
		if (dvo.getCurPage() != null){
			curPage = Integer.parseInt(dvo.getCurPage());
		}
		
		dvo.setPageSize(String.valueOf(pageSize));
		dvo.setGroupSize(String.valueOf(groupSize));
		dvo.setCurPage(String.valueOf(curPage));
		dvo.setTotalCount(String.valueOf(totalCount));
		
		dvo.setMnum(uList.get(0).getMnum());
		
		List<AppDocVO> cList = terraAppService.appSelectAllDoc(dvo);
		model.addAttribute("cList", cList);
		model.addAttribute("req", req);
		model.addAttribute("pagingdvo", dvo);	
		
		return "terraApproval/appSelect_AllDoc";
	}
	
	// 문서함 >> 반려
	@GetMapping("/appSelect/RJ")
	public String appSelectRJ(HttpServletRequest req, AppDocVO dvo, Model model) {
		
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraAppController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		dvo.setMnum(uList.get(0).getMnum());
		
		List<AppDocVO> cList = terraAppService.appSelectReject(dvo);
		model.addAttribute("cList", cList);
		
		return "terraApproval/appSelect_RJ";
	}
	
	@PostMapping("/appSelectContent/{type}")
	public String appSelectContent(@PathVariable String type, AppDocVO advo,  Model model, HttpServletRequest req) {
		logger.info(type);
		
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraAppController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		// 나를 대결자로 설정한 직원 정보
		List<TerraMemberVO> pList = terraMemberService.tMemSelectProxy(uList.get(0));
		if (pList.size() > 0 && pList != null) {		
			if (pList.get(0).getMcuryn().equals("N")) {
				logger.info(pList.get(0).getMcuryn());
				
				// 대결권한을 준 사람의 결재정보 조회
				advo.setAapp(pList.get(0).getMnum());
				pList = terraMemberService.tMemSelectProxy(uList.get(0));
				List<AppDocVO> prList = terraAppService.appSelectAll(advo);
				model.addAttribute("prList", prList);
				model.addAttribute("pList", pList);
			}
		}
			TerraMemberVO tmvo_s = new TerraMemberVO();
			tmvo_s.setMnum(uList.get(0).getMnum());
			advo.setMnum(uList.get(0).getMname());
			String passYN = terraMemberService.tMemSelect(tmvo_s).get(0).getMpassyn();
			List<AppDocVO> pcList = terraAppService.appProxySelect(advo);
			model.addAttribute("pcList", pcList);
			
			List<TerraMemberVO> aList = new ArrayList<TerraMemberVO>();
			List<TerraMemberVO> bList = new ArrayList<TerraMemberVO>();
			
			
			
		
		switch (type) {
		
		case "D":
			
			List<AppDraftVO> cList = terraAppService.appSelectContentD(advo);

			String app[] = CodeUtil.lineSplit(cList.get(0).getAapp());
			String ref[] = CodeUtil.lineSplit(cList.get(0).getAref());
			
			for (int i = 0; i < app.length ; i++) {
				TerraMemberVO tmvo = new TerraMemberVO();

				if (app[i].equals("-") ) {	
				}else {
					
				tmvo.setMnum(app[i]);
				aList.add(terraMemberService.tMemSelect(tmvo).get(0));
				}
			}
			for (int i = 0; i < ref.length; i++) {
				TerraMemberVO tmvo = new TerraMemberVO();
				
				if (ref[i].equals("-") ) {	
				}else {
					tmvo.setMnum(ref[i]);
					bList.add(terraMemberService.tMemSelect(tmvo).get(0));
				}
			}
			model.addAttribute("aList", aList);
			model.addAttribute("bList", bList);
			model.addAttribute("cList", cList);
			model.addAttribute("req", req);
			model.addAttribute("passYN", passYN);
			
			return "terraApproval/appSelectContent_D";
		case "V":
			
			List<AppVacationVO> vList = terraAppService.appSelectContentV(advo);
			
			app = CodeUtil.lineSplit(vList.get(0).getAapp());
			ref = CodeUtil.lineSplit(vList.get(0).getAref());
			
			
			for (int i = 0; i < app.length ; i++) {
				TerraMemberVO tmvo = new TerraMemberVO();

				if (app[i].equals("-") ) {	
				}else {
					
				tmvo.setMnum(app[i]);
				aList.add(terraMemberService.tMemSelect(tmvo).get(0));
				}
			}
			for (int i = 0; i < ref.length; i++) {
				TerraMemberVO tmvo = new TerraMemberVO();
				
				if (ref[i].equals("-") ) {	
				}else {
					tmvo.setMnum(ref[i]);
					bList.add(terraMemberService.tMemSelect(tmvo).get(0));
				}
			}
			model.addAttribute("aList", aList);
			model.addAttribute("bList", bList);
		
			model.addAttribute("vList", vList);
			model.addAttribute("req", req);
			model.addAttribute("passYN", passYN);
			
			return "terraApproval/appSelectContent_V";
			
			
		case "E":
			
			List<AppExpandVO> eList = terraAppService.appSelectContentE(advo);
			
			app = CodeUtil.lineSplit(eList.get(0).getAapp());
			ref = CodeUtil.lineSplit(eList.get(0).getAref()); 
			String subject[] = CodeUtil.lineSplit(eList.get(0).getEsubject());
			String memo[] = CodeUtil.lineSplit(eList.get(0).getEmemo());
			String etype[] = CodeUtil.lineSplit(eList.get(0).getEtype());
			String deal[] = CodeUtil.lineSplit(eList.get(0).getEdeal());
			String when[] = CodeUtil.lineSplit(eList.get(0).getEwhen());
			String amount[] = CodeUtil.lineSplit(eList.get(0).getEamount());
			String price[] = CodeUtil.lineSplit(eList.get(0).getEprice());
			String card[] = CodeUtil.lineSplit(eList.get(0).getEcard());
			
			for (int i = 0; i < app.length ; i++) {
				TerraMemberVO tmvo = new TerraMemberVO();

				if (app[i].equals("-") ) {	
				}else {
					
				tmvo.setMnum(app[i]);
				aList.add(terraMemberService.tMemSelect(tmvo).get(0));
				}
			}
			for (int i = 0; i < ref.length; i++) {
				TerraMemberVO tmvo = new TerraMemberVO();
				
				if (ref[i].equals("-") ) {	
				}else {
					tmvo.setMnum(ref[i]);
					bList.add(terraMemberService.tMemSelect(tmvo).get(0));
				}
			}
			model.addAttribute("aList", aList);
			model.addAttribute("bList", bList);
		
			model.addAttribute("eList", eList);
			model.addAttribute("req", req);
			model.addAttribute("passYN", passYN);
			
			return "terraApproval/appSelectContent_E";
		}
		return "terraApproval/appSelectContent_D";
	}
	
	@PostMapping("/appInsert/{type}")
	public String appInsert(@PathVariable String type, HttpServletRequest req) {
		logger.info("trAppVacationInsert 함수 진입 >>> : ");
		String anum = ChabunUtil.getAppChabunD("D", terraChabunService.getTerraAppChabun().getAnum());
		logger.info("anum >>> " + anum);
		int nCnt = 0;
		
		FileUploadUtil fu = new FileUploadUtil( CommonUtils.TMEM_IMG_UPLOAD_PATH,
				CommonUtils.TMEM_IMG_FILE_SIZE,
				CommonUtils.TMEM_EN_CODE);
		boolean bool = fu.imgfileUpload(req);
		logger.info("bool >>>>>>>>>> " + bool);
		
		if (type.equals("D")) {
			logger.info("Insert/D 진입");
			
			AppDraftVO dvo = null;
			dvo = new AppDraftVO();
			dvo.setAconfirm("-,-,-,-,-,");
			// 채번
			dvo.setAnum(anum);
			logger.info("anum >>> " + anum);
			// 사원번호
			T_Session ks = T_Session.getInstance();			
			String tnum = ks.getSession(req);
			logger.info("tnum >>> " + tnum);
			dvo.setMnum(tnum);
			
			// 결재자
			dvo.setAapp(fu.getParameter("aapp"));
			logger.info("aapp >>>>>>>>>> " + fu.getParameter("aapp"));
			// 참조자
			dvo.setAref(fu.getParameter("aref"));
			logger.info("aref >>>>>>>>>> " + fu.getParameter("aref"));
			
			
			// 문서상태
			dvo.setAlevel(fu.getParameter("alevel"));
			logger.info("alevel >>>>>>>>>> " + fu.getParameter("alevel"));
			
			
			// 문서권한
			dvo.setAauth(fu.getParameter("aauth"));
			logger.info("aauth >>>>>>>>>> " + fu.getParameter("aauth"));
			
			// 문서종류
			dvo.setAtype(fu.getParameter("atype"));
			logger.info("atype >>>>>>>>>> " + fu.getParameter("atype"));
			
			// 삭제일
			dvo.setDeletedate(fu.getParameter("deletedate"));
			logger.info("deletedate >>> " + fu.getParameter("deletedate"));
			
			// 휴가 문서 제목
			dvo.setDsubject(fu.getParameter("dsubject"));
			logger.info("vsubject >>>>>>>>>> " + fu.getParameter("dsubject"));
			
			
			// 휴가 문서 내용
			dvo.setDmemo(fu.getParameter("dmemo"));
			logger.info("dmemo >>>>>>>>>> " + fu.getParameter("dmemo"));
			// 휴가 문서 파일
			dvo.setDfile(fu.getFileName("dfile"));
			logger.info("dfile >>>>>>>>>> " + fu.getFileName("dfile"));
			
			nCnt = terraAppService.appDraftInsert(dvo);
			
			if (nCnt > 0) {
				logger.info("DraftInsert 함수 nCnt >>> : " + nCnt);
				return "/terraApproval/appInsert";
			}
		}else if(type.equals("V")) {
			
			AppVacationVO avvo = null;
			avvo = new AppVacationVO();
			avvo.setAconfirm("-,-,-,-,-,");
			// 채번
			avvo.setAnum(anum);
			logger.info("anum >>> " + anum);
			
			// 사원번호
			T_Session ks = T_Session.getInstance();			
			String tnum = ks.getSession(req);
			logger.info("tnum / mnum >>> " + tnum);
			avvo.setMnum(tnum);
			
			// 결재자
			avvo.setAapp(fu.getParameter("aapp"));
			logger.info("aapp >>>>>>>>>> " + fu.getParameter("aapp"));
			// 참조자
			avvo.setAref(fu.getParameter("aref"));
			logger.info("aref >>>>>>>>>> " + fu.getParameter("aref"));
			
			
			// 문서상태
			avvo.setAlevel(fu.getParameter("alevel"));
			logger.info("alevel >>>>>>>>>> " + fu.getParameter("alevel"));
			
			
			// 문서권한
			avvo.setAauth(fu.getParameter("aauth"));
			logger.info("aauth >>>>>>>>>> " + fu.getParameter("aauth"));
			
			// 문서종류
			avvo.setAtype(fu.getParameter("atype"));
			logger.info("atype >>>>>>>>>> " + fu.getParameter("atype"));
			
			// 삭제일
			avvo.setDeletedate(fu.getParameter("deletedate"));
			logger.info("deletedate >>> " + fu.getParameter("deletedate"));
			
			// 휴가 문서 제목
			avvo.setVsubject(fu.getParameter("vsubject"));
			logger.info("vsubject >>>>>>>>>> " + fu.getParameter("vsubject"));
			
			
			// 휴가 문서 내용
			avvo.setVmemo(fu.getParameter("vmemo"));
			logger.info("vmemo >>>>>>>>>> " + fu.getParameter("vmemo"));
			// 휴가 문서 파일
			avvo.setVfile(fu.getFileName("vfile"));
			logger.info("vfile >>>>>>>>>> " + fu.getFileName("vfile"));
			// 휴가 시작일
			avvo.setStartdate(fu.getParameter("startdate"));
			logger.info("startdate >>>>>>>>>> " + fu.getParameter("startdate"));
			// 휴가 종료일
			avvo.setEnddate(fu.getParameter("enddate"));
			logger.info("enddate >>>>>>>>>> " + fu.getParameter("enddate"));
			
			nCnt = terraAppService.appVacationInsert(avvo);	
			
			
			if (nCnt > 0) {
				logger.info("VacationInsert 함수 nCnt >>> : " + nCnt);
				return "/terraApproval/appInsert";
			}
		}else if(type.equals("E")) {
	
			
			AppExpandVO aevo = null;
			aevo = new AppExpandVO();
			aevo.setAconfirm("-,-,-,-,-,");
			// anum 채번 돌려서 가져옴
			aevo.setAnum(anum);
			logger.info("anum >>> " + anum);
			
			// 사원번호 세션 가져와서 mnum에 넣기 
			T_Session ks = T_Session.getInstance();			
			String tnum = ks.getSession(req);
			logger.info("tnum / mnum >>> " + tnum);
			aevo.setMnum(tnum);
			
			// 결재자
			aevo.setAapp(fu.getParameter("aapp"));
			logger.info("aapp >>>>>>>>>> " + fu.getParameter("aapp"));
			// 참조자
			aevo.setAref(fu.getParameter("aref"));
			logger.info("aref >>>>>>>>>> " + fu.getParameter("aref"));
			
			
			// 문서상태
			aevo.setAlevel(fu.getParameter("alevel"));
			logger.info("alevel >>>>>>>>>> " + fu.getParameter("alevel"));
			
			
			// 문서권한
			aevo.setAauth(fu.getParameter("aauth"));
			logger.info("aauth >>>>>>>>>> " + fu.getParameter("aauth"));
			
			// 문서종류
			aevo.setAtype(fu.getParameter("atype"));
			logger.info("atype >>>>>>>>>> " + fu.getParameter("atype"));
			
			// 삭제일
			aevo.setDeletedate(fu.getParameter("deletedate"));
			logger.info("deletedate >>> " + fu.getParameter("deletedate"));
			
			aevo.setEsubject(fu.getParameter("esubject"));
			logger.info("esubject >>> : " + fu.getParameter("esubject"));
			
			// 지출구분
			String etype = "";
			String types[] = fu.getParameterValues("etype");
			for (int i=0; i<types.length; i++) {
				etype += types[i] + ",";
			}
			aevo.setEtype(etype);
			logger.info("etype >>> : " + fu.getParameter("etype"));
			
			// 거래처 
			String edeal = "";
			String deal[] = fu.getParameterValues("edeal");
			for (int i=0; i<deal.length; i++) {
				edeal += deal[i] + ",";
			}
			aevo.setEdeal(edeal);
			logger.info("edeal >>>" + edeal);
			
			//회계기준월
			String ewhen = "";
			String when[] = fu.getParameterValues("ewhen");
			for (int i=0; i<when.length; i++) {
				ewhen += when[i] + ",";
			}
			aevo.setEwhen(ewhen);
			logger.info("ewhen >>> : " + ewhen);
			
			// 내용
			String ememo = "";
			String memo[] = fu.getParameterValues("ememo");
			for (int i=0; i<memo.length; i++) {
				ememo += memo[i] + ",";
			}
			aevo.setEmemo(ememo);
			logger.info("ememo >>> : " + ememo);
			
			// 수량
			String eamount = "";
			String amount[] = fu.getParameterValues("eamount");
			for (int i=0; i<amount.length; i++) {
				eamount += amount[i] + ",";
			}
			aevo.setEamount(eamount);
			logger.info("eamount >>> : " + eamount);
			
			// 단가
			String eprice = "";
			String price[] = fu.getParameterValues("eprice");
			for (int i=0; i<price.length; i++) {
				eprice += price[i] + ",";
			}
			aevo.setEprice(eprice);
			logger.info("eprice >>> : " + eprice);
			
			// 카드구분
			String ecard = "";
			String card[] = fu.getParameterValues("ecard");
			for (int i=0; i<card.length; i++) {
				ecard += card[i] + ",";
			}
			aevo.setEcard(ecard);
			logger.info("ecard >>> : " + ecard);
			logger.info("ecard >>> " + aevo.getEcard());
			
			aevo.setEfile(fu.getFileName("efile"));
			
			
			
			nCnt = terraAppService.appExpandInsert(aevo);
			
			
			
			return "/terraApproval/appInsert";
			
		}
		
		return "/terraApproval/appInsert";
	}

	@PostMapping("/appSangsinUpdate")
	public String appSangsinUpdate(AppDocVO advo, HttpServletRequest req, Model model) {
		
		T_Session ks = T_Session.getInstance();			
		String tnum = ks.getSession(req);
		int nCnt = 0;
		advo.setAlevel(CodeUtil.numPadUtil(advo.getAlevel()));
		
		String chk = CodeUtil.nullChk(advo.getAapp(), tnum);
		logger.info(chk);
		
		if (chk.equals("keep")) {
			nCnt = terraAppService.appUpdate(advo);
		}else if(!chk.equals("keep") || advo.getAlevel().equals("09")){
			logger.info(advo.getAlevel());
			advo.setAlevel("09");
			nCnt = terraAppService.appUpdate(advo);
		}
		
		logger.info(nCnt);
		
		
		if (nCnt == 1) {
			model.addAttribute("anum", advo.getAnum());
			model.addAttribute("atype", advo.getAtype());
			
			return "terraApproval/appUpdateok";
		}
		return "terraApproval/appUpdateok";
	}
	
	@PostMapping("/appRejectUpdate") // 반려
	public String appRejectUpdate(AppDocVO advo, HttpServletRequest req, Model model) {
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraAppController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		model.addAttribute("anum", advo.getAnum());
		model.addAttribute("atype", advo.getAtype());
		
		int nCnt = terraAppService.appRejectUpdate(advo);
		
		return "terraApproval/appUpdateok";
	}
	
	@PostMapping("/appSangsinUpdateProxy")
	public String appSangsinUpdateProxy(AppDocVO advo, HttpServletRequest req) {
		logger.info("anum >>>>" + advo.getAnum());
		logger.info("alevel >>> " + advo.getAlevel());
		logger.info("aconfirm >>>>" + advo.getAconfirm());
		logger.info("aapp >>>>" + advo.getAapp());
		
		
		AppDocVO advo_p = new AppDocVO();
		
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraAppController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		
		// 나를 대결자로 설정한 직원 정보
		List<TerraMemberVO> pList = terraMemberService.tMemSelectProxy(uList.get(0));
		if (pList.size() > 0 && pList != null) {		
			if (pList.get(0).getMcuryn().equals("N")) {
				logger.info(pList.get(0).getMcuryn());
				
				// 대결권한을 준 사람의 정보 조회
				advo_p.setAapp(pList.get(0).getMnum());
				pList = terraMemberService.tMemSelectProxy(uList.get(0));
			}
		}
		int nCnt = 0;
		int pCnt = 0;
		String chk = CodeUtil.nullChk(advo.getAapp(), pList.get(0).getMnum());
		String appR = advo.getAapp().replace(pList.get(0).getMnum(), uList.get(0).getMnum());
		advo_p.setAapp(appR);
		advo_p.setAnum(advo.getAnum());
		advo.setAlevel(CodeUtil.numPadUtil(advo.getAlevel()));
		
		logger.info(chk);
		List<AppDocVO> pcList = terraAppService.appProxySelect(advo_p);
		//테이블 존재시 업데이트, 없을시 인서트 구현필요
		if (pcList.size() == 0) {
			// PROXY 인서트
			if (chk.equals("keep")) {
				nCnt = terraAppService.appUpdate(advo);
				pCnt = terraAppService.appProxyInsert(advo_p);
			}else if(!chk.equals("keep") || advo.getAlevel().equals("09")){
				logger.info(advo.getAlevel());
				advo.setAlevel("09");
				nCnt = terraAppService.appUpdate(advo);
				pCnt = terraAppService.appProxyInsert(advo_p);
			}
		}else {
			if (chk.equals("keep")) {
				nCnt = terraAppService.appUpdate(advo);
				pCnt = terraAppService.appProxyUpdate(advo_p);
			}else if(!chk.equals("keep") || advo.getAlevel().equals("09")){
				logger.info(advo.getAlevel());
				advo.setAlevel("09");
				nCnt = terraAppService.appUpdate(advo);
				pCnt = terraAppService.appProxyUpdate(advo_p);
			}
		}
		
		logger.info(nCnt);
		if (nCnt == 1 && pCnt == 1) {
			return "terraApproval/appUpdateok";
		}
		return "terraApproval/appUpdateok"; 
	}
	
	@PostMapping("/appPassUpdate")
	public String appPassUpdate(AppDocVO advo, HttpServletRequest req, Model model) {
		
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraAppController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		advo.setAlevel("09"); // 결재 종료
		
		String passCf = advo.getAconfirm().replaceAll("@", "~").replaceAll("-", "~");
		
		logger.info(passCf);
		advo.setAconfirm(passCf); // 결재자 pass
	
		
		int nCnt = terraAppService.appPassUpdate(advo);
		model.addAttribute("anum", advo.getAnum());
		model.addAttribute("atype", advo.getAtype());
		
		
		if (nCnt == 1) {
			return "terraApproval/appUpdateok";
		}
		return "terraApproval/appUpdateok";
	}
	
	@ResponseBody
	@GetMapping("/appAlert")
	public String appAlert(HttpServletRequest req, AppDocVO advo, Model model) {
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraAppController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		advo.setMnum(uList.get(0).getMnum());
		List<AppDocVO> aList = terraAppService.appSelectAll(advo);
		List<AppDocVO> cList = new ArrayList<AppDocVO>();
		
		for (int i = 0; i < aList.size(); i++) {
			
			AppDocVO advo_s = new AppDocVO();
			advo_s = aList.get(i);
			
			int level = Integer.parseInt(advo_s.getAlevel());
			ArrayList<String> bList = new ArrayList<String>(Arrays.asList(advo_s.getAapp().split(",")));
			int f = bList.indexOf(uList.get(0).getMnum());
						
			if (level < 6 && f != -1) {	
					if (f + 1 == level) {
						logger.info(advo_s.getAnum() + ">>>> 대기");
						cList.add(advo_s);
					}
				}
			}
		
		if (cList.size() > 0 && cList != null) {
			
			return "ALERT_YES";
		}
		return "ALERT_NO";
	}
	
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
}
