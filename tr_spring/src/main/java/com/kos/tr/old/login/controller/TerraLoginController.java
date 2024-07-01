package com.kos.tr.old.login.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kos.tr.old.login.service.TerraLoginService;
import com.kos.tr.old.member.service.TerraMemberService;
import com.kos.tr.old.member.vo.TerraMemberVO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kos.tr.old.common.T_Session;

@Controller
public class TerraLoginController {
	Logger logger = LogManager.getLogger(TerraLoginController.class);
	
	@Autowired(required=false)
	private TerraLoginService terraLoginService;
	
	@Autowired(required=false)
	private TerraMemberService terraMemberService;
	
	@GetMapping("loginForm")
	public String terraLoginForm() {
		
		return "terraLogin/terraLoginForm";
	}
	
	
	@PostMapping("loginCheck")
	public String terraLoginCheck(HttpServletRequest req, TerraMemberVO tmvo, Model model) {
		logger.info("terraLoginCheck 함수 진입 >>> : ");	
		
		
		
		List<TerraMemberVO> listNumCheck = terraMemberService.tMemSelectId(tmvo);
		if (listNumCheck.size() > 0 && listNumCheck != null) {
			
			tmvo.setMnum(listNumCheck.get(0).getMnum());
			
			// 서비스 호출
			List<TerraMemberVO> listLogin = terraLoginService.terraLoginCheck(tmvo);
			
			if (listLogin.size() == 1) { 
				logger.info("terraLoginCheck loginCheck listLogin.size() >>> : " + listLogin.size());
				
				T_Session ks = T_Session.getInstance();			
				String tnum = ks.getSession(req);
				
				if (tnum !=null && tnum.equals(listLogin.get(0).getMnum())){				
					logger.info("terraLoginCheck login >>> : 로그인 중 >>> : 메인 페이지로 이동 하기 >>> : " + tnum);
					model.addAttribute("listLogin", listLogin);
					return "terraMain/mainRedirect";
				}else {
					ks.setSession(req, tmvo.getMnum());
					logger.info("terraLoginCheck login >>> : 세션부여 하기  >>> : " + tmvo.getMnum());
					
					model.addAttribute("listLogin", listLogin);
					return "terraMain/mainRedirect";
				}
			}
		}
		return "terraLogin/loginplz";
	}
	
	@GetMapping("terraLogout")
	public String terraLogout(HttpServletRequest req) {	
		logger.info("kosLogout() 함수 진입 >>> : ");		
								
		T_Session ts = T_Session.getInstance();			
		ts.killSession(req);
		return "terraCompany/companyMain";
	}
}
