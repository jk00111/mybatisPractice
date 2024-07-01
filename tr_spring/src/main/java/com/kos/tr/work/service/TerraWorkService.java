package com.kos.tr.work.service;

import java.util.List;

import com.kos.tr.member.vo.TerraMemberVO;
import com.kos.tr.work.vo.TerraWorkVO;

public interface TerraWorkService {

	public int trWorkInsert(TerraWorkVO twvo);
	public List<TerraWorkVO> trWorkSelect(TerraWorkVO twvo);
	public List<TerraMemberVO> trWorkSelectVacation(TerraMemberVO tmvo);
	public int trWorkUpdateS(TerraWorkVO twvo);
	public int trWorkUpdateE(TerraWorkVO twvo);
	public List<TerraWorkVO> trWorkEtc(TerraWorkVO twvo);
	
	//근무일수 조회
	public List<TerraWorkVO> trWorkSelectHour(TerraWorkVO twvo);
} // end of TerraWorkService interface
