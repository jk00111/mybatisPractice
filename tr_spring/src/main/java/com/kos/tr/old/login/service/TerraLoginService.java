package com.kos.tr.old.login.service;

import java.util.List;

import com.kos.tr.old.member.vo.TerraMemberVO;

public interface TerraLoginService {

	public List<TerraMemberVO> terraLoginCheck(TerraMemberVO tmvo);
}
