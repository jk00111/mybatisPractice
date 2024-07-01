package com.kos.tr.old.work.service;

import java.util.List;

import com.kos.tr.old.member.vo.TerraMemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kos.tr.old.work.dao.TerraWorkDAO;
import com.kos.tr.old.work.vo.TerraWorkVO;

@Service
@Transactional
public class TerraWorkServiceImpl implements TerraWorkService {
	Logger logger = LoggerFactory.getLogger(TerraWorkServiceImpl.class);
	
	// 서비스에서 DAO 연결
	// DI 의존성주입
	@Autowired(required=false)
	private TerraWorkDAO terraWorkDAO;
	
	@Override
	public int trWorkInsert(TerraWorkVO twvo) {
		// TODO Auto-generated method stub
		logger.info("trWorkInsert 함수 진입 >>> : ");
		return terraWorkDAO.trWorkInsert(twvo);
	} // end of trWorkInsert function 

	@Override
	public List<TerraWorkVO> trWorkSelect(TerraWorkVO twvo) {
		// TODO Auto-generated method stub
		logger.info("trWorkSelect 함수 진입 >>> : ");
		return terraWorkDAO.trWorkSelect(twvo);
	} // end of trWorkSelectAll function

	@Override
	public List<TerraMemberVO> trWorkSelectVacation(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		logger.info("trWorkSelectVacation 함수 진입 >>> : ");
		return terraWorkDAO.trWorkSelectVacation(tmvo);
	}
	
	@Override
	public int trWorkUpdateS(TerraWorkVO twvo) {
		// TODO Auto-generated method stub
		logger.info("trWorkUpdate_S 함수 진입 >>> : ");
		return terraWorkDAO.trWorkUpdateS(twvo);
	} // end oftrWorkUpdate

	@Override
	public int trWorkUpdateE(TerraWorkVO twvo) {
		// TODO Auto-generated method stub
		logger.info("Service | trWorkUpdateE 함수 진입 >>> : ");
		return terraWorkDAO.trWorkUpdateE(twvo);
	}

	@Override
	public List<TerraWorkVO> trWorkSelectHour(TerraWorkVO twvo) {
		// TODO Auto-generated method stub
		logger.info("trWorkSelectHour 함수 진입 >>> : ");
		return terraWorkDAO.trWorkSelectHour(twvo);
	} // end of trWorkSelectAll function

	@Override
	public List<TerraWorkVO> trWorkEtc(TerraWorkVO twvo) {
		// TODO Auto-generated method stub
		logger.info("trWorkSelectHour 함수 진입 >>> : ");
		return terraWorkDAO.trWorkEtc(twvo);
	}
	
	
	
	
} // end of TerraWorkServiceImpl class
