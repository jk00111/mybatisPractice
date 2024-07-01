package com.kos.tr.old.rboard.dao;

import java.util.List;

import com.kos.tr.old.rboard.vo.TerraRboardVO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TerraRboardDAOImpl implements TerraRboardDAO {
	Logger logger = LogManager.getLogger(TerraRboardDAOImpl.class);
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;

	@Override
	public int trRboardInsert(TerraRboardVO rbvo) {
		// TODO Auto-generated method stub
		logger.info("trRboardInsert 함수 진입 >>> : ");
		
		return sqlSession.insert("trRboardInsert", rbvo);
	}

	@Override
	public List<TerraRboardVO> trRboardSelectAll(TerraRboardVO rbvo) {
		// TODO Auto-generated method stub
		logger.info("trRboardSelectAll 함수 진입 >>> : ");
		
		return sqlSession.selectList("trRboardSelectAll", rbvo);
	}

	@Override
	public int trRboardDelete(TerraRboardVO rbvo) {
		// TODO Auto-generated method stub
		logger.info("trRboardDelete 함수 진입 >>> : ");
		
		return sqlSession.update("trRboardDelete", rbvo);
	}

}
