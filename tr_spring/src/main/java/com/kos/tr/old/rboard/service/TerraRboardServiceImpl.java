package com.kos.tr.old.rboard.service;

import java.util.List;

import com.kos.tr.old.rboard.dao.TerraRboardDAO;
import com.kos.tr.old.rboard.vo.TerraRboardVO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TerraRboardServiceImpl implements TerraRboardService {
	Logger logger = LogManager.getLogger(TerraRboardServiceImpl.class);
	
	@Autowired(required=false)
	private TerraRboardDAO terraRboardDAO;

	@Override
	public int trRboardInsert(TerraRboardVO rbvo) {
		// TODO Auto-generated method stub
		logger.info("trRboardInsert 함수 진입 >>> : ");
		
		return terraRboardDAO.trRboardInsert(rbvo);
	}

	@Override
	public List<TerraRboardVO> trRboardSelectAll(TerraRboardVO rbvo) {
		// TODO Auto-generated method stub
		logger.info("trRboardSelectAll 함수 진입 >>> : ");
		
		return terraRboardDAO.trRboardSelectAll(rbvo);
	}

	@Override
	public int trRboardDelete(TerraRboardVO rbvo) {
		// TODO Auto-generated method stub
		logger.info("trRboardDelete 함수 진입 >>> : ");
		
		return terraRboardDAO.trRboardDelete(rbvo);
	}

}
