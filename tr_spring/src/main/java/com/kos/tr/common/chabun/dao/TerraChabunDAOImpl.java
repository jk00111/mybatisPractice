package com.kos.tr.common.chabun.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kos.tr.app.vo.AppDraftVO;
import com.kos.tr.board.vo.TerraBoardVO;
import com.kos.tr.member.vo.TerraMemberVO;
import com.kos.tr.rboard.vo.TerraRboardVO;
import com.kos.tr.work.vo.TerraWorkVO;
	
	

@Repository
public class TerraChabunDAOImpl implements TerraChabunDAO {
	Logger logger = LogManager.getLogger(TerraChabunDAOImpl.class);
	
	@Autowired(required = false)
	private SqlSessionTemplate sqlSession;
	
	@Override
	public TerraMemberVO getTerraMemberChabun() {
		// TODO Auto-generated method stub
		logger.info("getTerraMemberChabun >>> : ");
		return sqlSession.selectOne("getTerraMemberChabun");
	}

	@Override
	public AppDraftVO getTerraAppChabun() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getTerraMemberChabun");
	}
	
	@Override
	public TerraBoardVO getTerraBoardChabun() {
		// TODO Auto-generated method stub
		logger.info("getTerraBoardChabun >>> : ");
		
		return sqlSession.selectOne("getTerraBoardChabun");
	}

	@Override
	public TerraRboardVO getTerraRboardChabun() {
		// TODO Auto-generated method stub
		logger.info("getTerraRboardChabun >>> : ");
		
		return sqlSession.selectOne("getTerraBoardChabun");
	}
	
	@Override
	public TerraWorkVO getTerraWorkChabun() {
		// TODO Auto-generated method stub
		logger.info("getTerraWorkChabun >>> : ");
		return sqlSession.selectOne("getTerraWorkChabun");
	} // end of getTerraWrokdChabun function
}
