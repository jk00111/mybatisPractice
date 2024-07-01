package com.kos.tr.member.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kos.tr.member.vo.TerraMemberVO;

@Repository
public class TerraMemberDAOImpl implements TerraMemberDAO {

	Logger logger = LogManager.getLogger(TerraMemberDAO.class);
	
	@Autowired(required = false)
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int tMemInsert(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		logger.info("tMemInsert 함수 진입");
		
		return sqlSession.insert("tMemInsert", tmvo);
	}

	@Override
	public List<TerraMemberVO> tMemSelectAll(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		logger.info("tMemSelectAll 함수 진입 >>> : ");	
		
		return sqlSession.selectList("tMemSelectAll", tmvo);
	}
	
	@Override
	public List<TerraMemberVO> tMemSelect(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		logger.info("tMemSelect 함수 진입 >>> : ");	
		
		return sqlSession.selectList("tMemSelect", tmvo);
	}

	@Override
	public List<TerraMemberVO> tMemIdcheck(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		logger.info("tMemIdcheck 함수 진입 >>> : ");		
		
		return sqlSession.selectList("tMemIdcheck", tmvo);
	}

	@Override
	public List<TerraMemberVO> tMemSelectId(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("tMemSelectId", tmvo);
	}

	@Override
	public List<TerraMemberVO> tMemSelectProxy(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("", tmvo);
	}

	@Override
	public int tMemUpdate(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		logger.info("tMemUpdate 함수 진입 >>> : ");	
		
		return sqlSession.update("tMemUpdate", tmvo);
	}

	@Override
	public int tMemDelete(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		logger.info("tMemDelete 함수 진입 >>> : ");		
		
		return sqlSession.update("tMemDelete", tmvo);
	}

	@Override
	public List<TerraMemberVO> tMemSelectApp(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("tMemSelectApp", tmvo);
	}

}
