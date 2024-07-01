package com.kos.tr.login.dao;

import java.util.List;

import com.kos.tr.member.vo.TerraMemberVO;

public interface TerraLoginDAO {

	public List<TerraMemberVO> terraLoginCheck(TerraMemberVO tmvo);
}
