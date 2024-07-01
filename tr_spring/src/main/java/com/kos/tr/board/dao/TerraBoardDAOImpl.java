package com.kos.tr.board.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kos.tr.board.vo.TerraBoardVO;



@Repository
public class TerraBoardDAOImpl implements TerraBoardDAO {
	Logger logger = LogManager.getLogger(TerraBoardDAOImpl.class);
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;

	@Override
	public int tBoardInsert(TerraBoardVO tbvo) {
		// TODO Auto-generated method stub
		logger.info("terraBoardInsert 함수 진입 >>> : ");
		
		return sqlSession.insert("tBoardInsert", tbvo);
	}

	@Override
	public List<TerraBoardVO> tBoardSelectAll(TerraBoardVO tbvo) {
		// TODO Auto-generated method stub
		logger.info("terraBoardSelectAll 함수 진입 >>> : ");
		
		return sqlSession.selectList("tBoardSelectAll", tbvo);
	}

	@Override
	public List<TerraBoardVO> tBoardSelect(TerraBoardVO tbvo) {
		// TODO Auto-generated method stub
		logger.info("terraBoardSelect 함수 진입 >>> : ");
		
		return sqlSession.selectList("tBoardSelect", tbvo);
	}

	@Override
	public int tBoardBhit(TerraBoardVO tbvo) {
		// TODO Auto-generated method stub
		logger.info("terraBoardBhit 함수 진입 >>> : ");
		
		return sqlSession.update("tBoardBhit", tbvo);
	}

	@Override
	public int tBoardUpdate(TerraBoardVO tbvo) {
		// TODO Auto-generated method stub
		logger.info("terraBoardUpdate 함수 진입 >>> : ");
		
		return sqlSession.update("tBoardUpdate", tbvo);
	}
	
	@Override
	public int tBoardDelete(TerraBoardVO tbvo) {
		// TODO Auto-generated method stub
		logger.info("terraBoardDelete 함수 진입 >>> : ");
		
		return sqlSession.update("tBoardDelete", tbvo);
	}

}
