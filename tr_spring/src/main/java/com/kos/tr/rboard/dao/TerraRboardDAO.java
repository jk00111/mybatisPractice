package com.kos.tr.rboard.dao;

import java.util.List;

import com.kos.tr.rboard.vo.TerraRboardVO;



public interface TerraRboardDAO {
	
	public int trRboardInsert(TerraRboardVO rbvo);
	public List<TerraRboardVO> trRboardSelectAll(TerraRboardVO rbvo);
	public int trRboardDelete(TerraRboardVO rbvo);

}
