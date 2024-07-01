package com.kos.tr.old.work.dao;

import java.util.List;

import com.kos.tr.old.member.vo.TerraMemberVO;
import com.kos.tr.old.work.vo.TerraWorkVO;

public interface TerraWorkDAO {

	public int trWorkInsert(TerraWorkVO twvo);
	public List<TerraWorkVO> trWorkSelect(TerraWorkVO twvo);
	public List<TerraMemberVO> trWorkSelectVacation(TerraMemberVO tmvo);
	public int trWorkUpdateS(TerraWorkVO twvo);
	public int trWorkUpdateE(TerraWorkVO twvo);
	public List<TerraWorkVO> trWorkEtc(TerraWorkVO twvo);
	
	//근무일수 조회
	public List<TerraWorkVO> trWorkSelectHour(TerraWorkVO twvo);
} // end of TerraWorkDAO interface(class)
