package com.kos.tr.login.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kos.tr.member.vo.TerraMemberVO;

@Repository
public class TerraLoginDAOImpl implements TerraLoginDAO {

	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<TerraMemberVO> terraLoginCheck(TerraMemberVO tmvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("terraLoginCheck", tmvo);
	}

}
