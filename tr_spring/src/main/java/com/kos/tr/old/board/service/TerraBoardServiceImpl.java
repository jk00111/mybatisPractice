package com.kos.tr.old.board.service;

import java.util.List;

import com.kos.tr.old.board.dao.TerraBoardDAO;
import com.kos.tr.old.board.vo.TerraBoardVO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TerraBoardServiceImpl implements TerraBoardService {
	Logger logger = LogManager.getLogger(TerraBoardServiceImpl.class);
	
	@Autowired(required=false)
	private TerraBoardDAO terraBoardDAO;

	@Override
	public int tBoardInsert(TerraBoardVO tbvo) {
		// TODO Auto-generated method stub
		logger.info("tBoardInsert 함수 진입 >>> : ");
		
		return terraBoardDAO.tBoardInsert(tbvo);
	}

	@Override
	public List<TerraBoardVO> tBoardSelectAll(TerraBoardVO tbvo) {
		// TODO Auto-generated method stub
		logger.info("tBoardSelectAll 함수 진입 >>> : ");
		
		return terraBoardDAO.tBoardSelectAll(tbvo);
	}

	@Override
	public List<TerraBoardVO> tBoardSelect(TerraBoardVO tbvo) {
		// TODO Auto-generated method stub
		logger.info("tBoardSelect 함수 진입 >>> : ");
		
		return terraBoardDAO.tBoardSelect(tbvo);
	}

	@Override
	public int tBoardBhit(TerraBoardVO tbvo) {
		// TODO Auto-generated method stub
		logger.info("tBoardBhit 함수 진입 >>> : ");
		
		return terraBoardDAO.tBoardBhit(tbvo);
	}
	
	@Override
	public int tBoardUpdate(TerraBoardVO tbvo) {
		// TODO Auto-generated method stub
		logger.info("tBoardUpdate 함수 진입 >>> : ");
		
		return terraBoardDAO.tBoardUpdate(tbvo);
	}

	@Override
	public int tBoardDelete(TerraBoardVO tbvo) {
		// TODO Auto-generated method stub
		logger.info("tBoardDelete 함수 진입 >>> : ");
		
		return terraBoardDAO.tBoardDelete(tbvo);
	}

}
