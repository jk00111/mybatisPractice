package com.kos.tr.common.chabun.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kos.tr.app.vo.AppDraftVO;
import com.kos.tr.board.vo.TerraBoardVO;
import com.kos.tr.common.chabun.dao.TerraChabunDAO;
import com.kos.tr.member.vo.TerraMemberVO;
import com.kos.tr.rboard.vo.TerraRboardVO;
import com.kos.tr.work.vo.TerraWorkVO;


@Service
@Transactional
public class TerraChabunServiceImpl implements TerraChabunService {
	Logger logger = LogManager.getLogger(TerraChabunServiceImpl.class);
	
	@Autowired(required = false)
	private TerraChabunDAO terraChabunDAO;
	
	@Override
	public TerraMemberVO getTerraMemberChabun() {
		// TODO Auto-generated method stub
		logger.info("getTerraMemberChabun 함수 진입 >> :");
		return terraChabunDAO.getTerraMemberChabun();
	}

	@Override
	public AppDraftVO getTerraAppChabun() {
		// TODO Auto-generated method stub
		return terraChabunDAO.getTerraAppChabun();
	}
	
	@Override
	public TerraBoardVO getTerraBoardChabun() {
		// TODO Auto-generated method stub
		logger.info("getTerraBoardChabun 함수 진입 >>> : ");
		
		return terraChabunDAO.getTerraBoardChabun();
	}

	@Override
	public TerraRboardVO getTerraRboardChabun() {
		// TODO Auto-generated method stub
		logger.info("getTerraRboardChabun 함수 진입 >>> : ");
		
		return terraChabunDAO.getTerraRboardChabun();
	}
	
	@Override
	public TerraWorkVO getTerraWorkChabun() {
		// TODO Auto-generated method stub
		logger.info("getTerraWorkChabun 함수 진입 >> : ");
		return terraChabunDAO.getTerraWorkChabun();
	} // end of getTerraWrokChabun

}
