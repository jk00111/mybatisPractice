package com.kos.tr.old.work.dao;

import java.util.List;

import com.kos.tr.old.member.vo.TerraMemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kos.tr.old.work.vo.TerraWorkVO;

@Repository
public class TerraWorkDAOImpl implements TerraWorkDAO {
	
	Logger logger = LoggerFactory.getLogger(TerraWorkDAOImpl.class);
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;
	
	
	// (1) 근태 INSERT 
	@Override
	public int trWorkInsert(TerraWorkVO twvo) {
		// TODO Auto-generated method stub
		logger.info("trWorkInsert 함수 진입 >>> : ");
		return sqlSession.insert("trWorkInsert", twvo);
	} // end of trWorkInsert function()

	// (2) 근태 Select
	@Override
	public List<TerraWorkVO> trWorkSelect(TerraWorkVO twvo) {
		// TODO Auto-generated method stub
		logger.info("trWorkSelect 함수 진입 >>> : ");
		return sqlSession.selectList("trWorkSelect", twvo);
	} // end of trWorkInsert function()

	// (3) 휴가현황 Select
	@Override
	public List<TerraMemberVO> trWorkSelectVacation(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		logger.info("trWorkSelectVacation 함수 진입 >>> : ");
		return sqlSession.selectList("trWorkSelectVacation", tmvo);
	} // end of trWorkSelectVacation function()

	// (4) 근태 변경, start Update_S
	@Override
	public int trWorkUpdateS(TerraWorkVO twvo) {
		// TODO Auto-generated method stub
		logger.info("trWorkUpdateS 함수 진입 >>> : ");
		return sqlSession.update("trWorkUpdateS", twvo);
	} // end of trWorkUpdate function()

	// (5) 근태 변경, End Update_E
	@Override
	public int trWorkUpdateE(TerraWorkVO twvo) {
		// TODO Auto-generated method stub
		logger.info("trWorkUpdateE 함수 진입 >>> : ");
		return sqlSession.update("trWorkUpdateE", twvo);
	}
	
	// (6) 근무일수 조회 
	@Override
	public List<TerraWorkVO> trWorkSelectHour(TerraWorkVO twvo) {
		// TODO Auto-generated method stub
		logger.info("trWorkSelectHour 함수 진입 >>> : ");
		return sqlSession.selectList("trWorkSelectHour", twvo);
	} // end of trWorkInsert function()
	
	// (7) 근태현황 조회
	@Override
	public List<TerraWorkVO> trWorkEtc(TerraWorkVO twvo) {
		// TODO Auto-generated method stub
		logger.info("trWorkEtc 함수 진입 >>> : ");
		return sqlSession.selectList("trWorkEtc", twvo);
	} // end of trWorkEtc
	

	


	
	
	
} // end of TerraWorkDAOImpl class
