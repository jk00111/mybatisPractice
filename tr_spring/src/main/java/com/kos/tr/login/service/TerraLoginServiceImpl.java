package com.kos.tr.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kos.tr.login.dao.TerraLoginDAO;
import com.kos.tr.member.vo.TerraMemberVO;

@Service
@Transactional
public class TerraLoginServiceImpl implements TerraLoginService {

	@Autowired(required=false)
	private TerraLoginDAO terraLoginDAO;
	
	@Override
	public List<TerraMemberVO> terraLoginCheck(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		return terraLoginDAO.terraLoginCheck(tmvo);
	}

}
