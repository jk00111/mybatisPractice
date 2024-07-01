package com.kos.tr.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kos.tr.board.service.TerraBoardService;
import com.kos.tr.board.vo.TerraBoardVO;
import com.kos.tr.common.ChabunUtil;
import com.kos.tr.common.CommonUtils;
import com.kos.tr.common.FileUploadUtil;
import com.kos.tr.common.T_Session;
import com.kos.tr.common.chabun.service.TerraChabunService;
import com.kos.tr.member.service.TerraMemberService;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import com.kos.tr.member.vo.TerraMemberVO;



@Controller
public class TerraBoardController {
	Logger logger = LogManager.getLogger(TerraBoardController.class);
	
	@Autowired(required=false)
	private TerraChabunService terraChabunService;
	
	@Autowired(required=false)
	private TerraBoardService terraBoardService;
	
	@Autowired(required=false)
	private TerraMemberService terraMemberService;
	
	@Autowired(required=false)
	private TerraBoardController terraBoardController;
	
	
	@GetMapping("tBoardInsertForm")
	public String tBoardInsertForm() {
		logger.info("tBoardInsert function Start >>> : ");
		
		return "terraBoard/tBoardInsertForm";
	}

	@PostMapping("tBoardInsert")
//	@RequestMapping(value = "/test/tBoardInsert", method= {RequestMethod.POST})
	public String tBoardInsert(HttpServletRequest req, Model model) {
		
		List<TerraMemberVO> uList = terraBoardController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		logger.info("tBoardInsert function Start >>> : ");
		
		String num = terraChabunService.getTerraBoardChabun().getBnum();
		logger.info("tBoardInsert num >>> : " + num);
		String bnum = ChabunUtil.getBoardChabun("D", num);
		
		FileUploadUtil fu = new FileUploadUtil(	CommonUtils.TBOARD_IMG_UPLOAD_PATH,
												CommonUtils.TBOARD_IMG_FILE_SIZE,
												CommonUtils.TBOARD_EN_CODE);
		
		boolean bool = fu.imgfileUpload(req);
		logger.info("tBoardInsert bool >>> : " + bool);
		
		TerraBoardVO _tbvo = null;
		_tbvo = new TerraBoardVO();
		
		_tbvo.setBnum(bnum);
		_tbvo.setMnum(uList.get(0).getMnum());
		_tbvo.setBsubject(fu.getParameter("bsubject"));
		_tbvo.setBmemo(fu.getParameter("bmemo"));
		_tbvo.setBfile(fu.getFileName("bfile"));
		logger.info("파일이름 >>> : " + fu.getFileName("bfile"));
		
		int nCnt = terraBoardService.tBoardInsert(_tbvo);
		if (nCnt > 0) {
			logger.info("terraBoardInsert nCnt >>> : " + nCnt);
			
			return "terraBoard/tBoardInsert";
		}
		
		return "terraBoard/tBoardInsertForm";
	}
	
	@GetMapping("tBoardSelectAll")
	public String tBoardSelectAll(TerraBoardVO tbvo, Model model, HttpServletRequest req) {
		logger.info("tBoardSelectAll 함수 진입  >>> : ");
		
		// 로그인 후 작성자 정보 찾아옴
		List<TerraMemberVO> uList = terraBoardController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		int pageSize 	= CommonUtils.BOARD_PAGE_SIZE;
		int groupSize 	= CommonUtils.BOARD_GROUP_SIZE;
		int curPage 	= CommonUtils.BOARD_CUR_PAGE;
		int totalCount 	= CommonUtils.BOARD_TOTAL_COUNT;
		
		if (tbvo.getCurPage() != null) {
			curPage = Integer.parseInt(tbvo.getCurPage());
		}
		
		tbvo.setPageSize(String.valueOf(pageSize));
		tbvo.setGroupSize(String.valueOf(groupSize));
		tbvo.setCurPage(String.valueOf(curPage));
		tbvo.setTotalCount(String.valueOf(totalCount));
		
		logger.info("tBoardSelectAll kbvo.getPageSize() >>> : " 	+ tbvo.getPageSize());
		logger.info("tBoardSelectAll kbvo.getGroupSize() >>> : " 	+ tbvo.getGroupSize());
		logger.info("tBoardSelectAll kbvo.getCurPage() >>> : " 		+ tbvo.getCurPage());
		logger.info("tBoardSelectAll kbvo.getTotalCount() >>> : " 	+ tbvo.getTotalCount());
		
		List<TerraBoardVO> listAll = terraBoardService.tBoardSelectAll(tbvo);
			logger.info("tBoardSelectAll listAll.size() >>> : " + listAll.size());

			model.addAttribute("pagingTBVO", tbvo);
			model.addAttribute("listAll", listAll);
			
			return "terraBoard/tBoardSelectAll";
	}
	
	@GetMapping("tBoardSelect")
	public String tBoardSelect(TerraBoardVO tbvo, Model model) {
		logger.info("tBoardSelect 함수 진입 >>> : ");
		logger.info("tBoardSelect 함수 진입 tbvo.getBnum() >>> : " + tbvo.getBnum());
		
		List<TerraBoardVO> listS = terraBoardService.tBoardSelect(tbvo);
		if (listS.size() == 1) {
			logger.info("tBoardSelect listS.size() >>> : " + listS.size());
			
			int bhitCnt = terraBoardService.tBoardBhit(tbvo);
			logger.info("tBoardSelect bhitCnt >>> : " + bhitCnt);
			
			model.addAttribute("listS", listS);
			
			return "terraBoard/tBoardSelect";
		}
		
		return "terraBoard/tBoardSelectAll";
	}
	
	@GetMapping("tBoardSelectContents")
	public String tBoardSelectContents(TerraBoardVO tbvo, Model model, HttpServletRequest req) {
		List<TerraMemberVO> uList = terraBoardController.userData(req);
		if (uList.size() != 1) {
			return "terraLogin/loginplz";
		}
		model.addAttribute("uList", uList);
		
		logger.info("tBoardSelectContents 함수 진입 >>> : ");
		logger.info("tBoardSelectContents 함수 진입 tbvo.getBnum() >>> : " + tbvo.getBnum());
		
		
		List<TerraBoardVO> listS = terraBoardService.tBoardSelect(tbvo);
		logger.info("listS >> " + listS);
		
		for (int i=0; i < listS.size(); i++) {
			TerraBoardVO _tvo = listS.get(i);
			logger.info("파일  >>> : " + _tvo.getBfile());
		}
		
		if (listS.size() == 1) {
			logger.info("tBoardSelect listS.size() >>> : " + listS.size());
			
			int bhitCnt = terraBoardService.tBoardBhit(tbvo);
			logger.info("tBoardSelect bhitCnt >>> : " + bhitCnt);
			
			
			model.addAttribute("listS", listS);
			
			return "terraBoard/tBoardSelectContents";
		}
		
		return "terraBoard/tBoardSelectAll";
	}
	
	@GetMapping("tBoardUpdateForm")
	public String tBoardUpdate(TerraBoardVO tbvo, HttpServletRequest req) {
		logger.info("tBoardUpdate 함수 진입 >>> : ");
		logger.info("tBoardUpdate 함수 진입 tbvo.getBnum() >>> : " + tbvo.getBnum());
		
		FileUploadUtil fu = new FileUploadUtil(	CommonUtils.TBOARD_IMG_UPLOAD_PATH,
												CommonUtils.TBOARD_IMG_FILE_SIZE,
												CommonUtils.TBOARD_EN_CODE);
		boolean bool = fu.imgfileUpload(req);
		logger.info("tBoardInsert bool >>> : " + bool);
		
		int nCnt = terraBoardService.tBoardUpdate(tbvo);
		
		if (nCnt > 0) {
			logger.info("tBoardUpdate nCnt >>> : " + nCnt);
			
			return "terraBoard/tBoardUpdateForm";
		}
		
		return "terraBoard/tBoardUpdateForm";
	}
	
	@GetMapping("tBoardDelete")
	public String tBoardDelete(TerraBoardVO tbvo) {
		logger.info("tBoardDelete 함수 진입 >>> : ");
		logger.info("tBoardDelete 함수 진입 tbvo.getBnum() >>> : " + tbvo.getBnum());
		
		int nCnt = terraBoardService.tBoardDelete(tbvo);
		
		if (nCnt > 0) {
			logger.info("tBoardDelete nCnt >>> : " + nCnt);
			
			return "terraBoard/tBoardUpdateForm";
		}
		
		return "terraBoard/tBoardUpdateForm";
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