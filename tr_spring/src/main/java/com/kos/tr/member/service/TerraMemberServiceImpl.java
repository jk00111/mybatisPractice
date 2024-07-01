package com.kos.tr.member.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kos.tr.member.dao.TerraMemberDAO;
import com.kos.tr.member.vo.TerraMemberVO;


@Service
@Transactional
public class TerraMemberServiceImpl implements TerraMemberService {
	private Logger logger = LogManager.getLogger(TerraMemberService.class);
	
	@Autowired(required = false)
	private TerraMemberDAO terraMemberDAO;
	
	@Override
	public int tMemInsert(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		logger.info("tMemInsert() 함수 진입 >> :");
		logger.info("tMemInsert()  >> :"+tmvo);
		
		return terraMemberDAO.tMemInsert(tmvo);
	}

	@Override
	public List<TerraMemberVO> tMemSelectAll(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		logger.info("tMemSelectAll() 함수 진입 >>> : ");
		
		return terraMemberDAO.tMemSelectAll(tmvo);
	}

	@Override
	public List<TerraMemberVO> tMemSelect(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		logger.info("tMemSelect() 함수 진입 >>> : ");
		
		return terraMemberDAO.tMemSelect(tmvo);
	}

	@Override
	public List<TerraMemberVO> tMemIdcheck(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		logger.info("tMemIdcheck() 함수 진입 >>> : ");
		
		return terraMemberDAO.tMemIdcheck(tmvo);
	}

	@Override
	public List<TerraMemberVO> tMemSelectId(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		return terraMemberDAO.tMemSelectId(tmvo);
	}

	@Override
	public List<TerraMemberVO> tMemSelectProxy(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		return terraMemberDAO.tMemSelectProxy(tmvo);
	}

	@Override
	public int tMemUpdate(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		logger.info("tMemUpdate() 함수 진입 >>> : ");
		
		return terraMemberDAO.tMemUpdate(tmvo);
	}

	@Override
	public int tMemDelete(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		logger.info("tMemDelete() 함수 진입 >>> : ");
		
		return terraMemberDAO.tMemDelete(tmvo);
	}

	@Override
	public List<TerraMemberVO> tMemSelectApp(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		return terraMemberDAO.tMemSelectApp(tmvo);
	}

}
