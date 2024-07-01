package com.kos.tr.login.service;

import java.util.List;

import com.kos.tr.member.vo.TerraMemberVO;

public interface TerraLoginService {

	public List<TerraMemberVO> terraLoginCheck(TerraMemberVO tmvo);
}
