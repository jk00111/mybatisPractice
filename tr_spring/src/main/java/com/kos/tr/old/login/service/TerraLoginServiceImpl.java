package com.kos.tr.old.login.service;

import java.util.List;

import com.kos.tr.old.login.dao.TerraLoginDAO;
import com.kos.tr.old.member.vo.TerraMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
