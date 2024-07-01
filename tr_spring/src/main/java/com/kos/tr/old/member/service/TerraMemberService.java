package com.kos.tr.old.member.service;

import java.util.List;

import com.kos.tr.old.member.vo.TerraMemberVO;

public interface TerraMemberService {
	int tMemInsert(TerraMemberVO tmvo);
	List<TerraMemberVO> tMemSelectAll(TerraMemberVO tmvo);
	List<TerraMemberVO> tMemSelect(TerraMemberVO tmvo);
	List<TerraMemberVO> tMemIdcheck(TerraMemberVO tmvo);
	List<TerraMemberVO> tMemSelectId(TerraMemberVO tmvo);
	List<TerraMemberVO> tMemSelectProxy(TerraMemberVO tmvo);
	List<TerraMemberVO> tMemSelectApp(TerraMemberVO tmvo);
	
	
	int tMemUpdate(TerraMemberVO tmvo);
	int tMemDelete(TerraMemberVO tmvo);
}
