package com.kos.tr.old.rboard.service;

import java.util.List;

import com.kos.tr.old.rboard.vo.TerraRboardVO;

public interface TerraRboardService {
	
	public int trRboardInsert (TerraRboardVO rbvo);
	public List<TerraRboardVO> trRboardSelectAll(TerraRboardVO rbvo);
	public int trRboardDelete(TerraRboardVO rbvo);
}
