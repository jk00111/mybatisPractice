package com.kos.tr.rboard.controller;

import java.lang.management.MemoryType;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kos.tr.common.ChabunUtil;
import com.kos.tr.common.chabun.service.TerraChabunService;
import com.kos.tr.rboard.service.TerraRboardService;
import com.kos.tr.rboard.vo.TerraRboardVO;


@Controller
public class TerraRboardController {
	Logger logger = LogManager.getLogger(TerraRboardController.class);
	
	@Autowired(required=false)
	private TerraChabunService terraChabunService;
	
	@Autowired(required=false)
	private TerraRboardService terraRboardService;
	
	@GetMapping("rboardForm")
	public String trRboardForm() {
		logger.info("trRboardForm 함수 진입 >>> : ");
		
		return "terraRboard/trRboardInsertForm";
	}
	
	@PostMapping("rBoardInsert")
	@ResponseBody
	public String trRboardInsert(TerraRboardVO rbvo) {
		logger.info("trRboardInsert >>> : ");
		logger.info("trRboardInsert rbvo.getBnum() >>> : " + rbvo.getBnum());
		
		String rnum = ChabunUtil.getRboardChabun("D", terraChabunService.getTerraRboardChabun().getRnum());
		logger.info("trRboardInsert rnum >>> : " + rnum);
		
		rbvo.setRnum(rnum);
		logger.info("rbvo.getRnum() >>> : " 	 + rbvo.getRnum());
		logger.info("rbvo.getBnum() >>> : " 	 + rbvo.getBnum());
		logger.info("rbvo.getMnum() >>> : " 	 + rbvo.getMnum());
		logger.info("rbvo.getMname() >>> : " 	 + rbvo.getMname());
		logger.info("rbvo.getMdeptno() >>> : " 	 + rbvo.getMdeptno());
		logger.info("rbvo.getRmemo() >>> : " 	 + rbvo.getRmemo());
		
		int nCnt = terraRboardService.trRboardInsert(rbvo);
		logger.info("trRboardInsert nCnt >>> : " + nCnt);
		
		if (1 == nCnt) { return "GOOD"; }
		else { return "BAD"; }
	}
	
	@ResponseBody
	@GetMapping(value ="trRboardSelectAll")
	public String trRboardSelectAll(TerraRboardVO rbvo) {
		logger.info("trRboardSelectAll >>> : ");
		logger.info("trRboardSelectAll rbvo.getBnum() >>> : " + rbvo.getBnum());
		
		List<TerraRboardVO> list = terraRboardService.trRboardSelectAll(rbvo);
		logger.info("trRboardSelectAll list >>> : " + list);
		
//		String mnum = ChabunUtil.getMemberChabun("Y", terraChabunService.getTerraMemberChabun().getMnum());
//		rbvo.setMnum(mnum);
		
		String ss = "";
		String listStr = "";
		for (int i=0; i < list.size(); i++) {
			TerraRboardVO _rbvo = list.get(i);
			String s0 = _rbvo.getRnum();
			String s1 = _rbvo.getMname();
			String s2 = _rbvo.getRmemo();
			String s3 = _rbvo.getInsertdate();
			ss = s0 + "," + s1 + "," + s2 + "," + s3;
			listStr += ss + "&";
		}
		
		return listStr;
	}
	
	@PostMapping("rBoardDelete")
	@ResponseBody
	public String trRboardDelete(TerraRboardVO rbvo) {
		logger.info("trRboardDelete >>> : ");
		logger.info("trRboardDelete rbvo.getRnum() >>> : " + rbvo.getRnum());
		
		int nCnt = terraRboardService.trRboardDelete(rbvo);
		logger.info("trRboardDelete nCnt >>> : " + nCnt);
		
		if (1 == nCnt) { return "GOOD"; }
		else { return "BAD"; }
	}

}
