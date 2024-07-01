package com.kos.tr.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.kos.tr.app.controller.TerraAppController;
import com.kos.tr.common.T_Session;
import com.kos.tr.member.service.TerraMemberService;
import com.kos.tr.member.vo.TerraMemberVO;

@Controller
public class terraMainController {
	
	Logger logger = LogManager.getLogger(terraMainController.class);
	
	// 컨트롤러 - 채번 서비스 연결
	@Autowired(required=false)
	private TerraAppController terraAppController;
	
	@Autowired(required=false)
	private TerraMemberService terraMemberService;
	
	
	@GetMapping("mainPage")
	public String terraMainPage( TerraMemberVO tmvo, Model model, HttpServletRequest req) {
		
		logger.info("terraMainPage 함수 진입 >> :");

		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraAppController.userData(req);
		if (uList.size() != 1) {
			logger.info("로그인 정보 >>> :" + uList.size());
			logger.info("req >>>> :" + req);
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		return "terraMain/mainPage";
	}
	
	
	@GetMapping("companyMain")
	public String companyMainPage() {
		logger.info("companyMainPage 함수 진입 >> :");
		return "terraCompany/companyMain";
	}
	
	@GetMapping("mainNotice")
	public String mainNotice() {
		logger.info("companyMainPage 함수 진입 >> :");
		return "terraMain/noticePopup";
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
