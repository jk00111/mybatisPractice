package com.kos.tr.old.login.dao;

import java.util.List;

import com.kos.tr.old.member.vo.TerraMemberVO;

public interface TerraLoginDAO {

	public List<TerraMemberVO> terraLoginCheck(TerraMemberVO tmvo);
}
