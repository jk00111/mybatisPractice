package com.kos.tr.old.common.chabun.service;


import com.kos.tr.old.app.vo.AppDraftVO;
import com.kos.tr.old.board.vo.TerraBoardVO;
import com.kos.tr.old.member.vo.TerraMemberVO;
import com.kos.tr.old.rboard.vo.TerraRboardVO;
import com.kos.tr.old.work.vo.TerraWorkVO;

public interface TerraChabunService {
	public TerraMemberVO getTerraMemberChabun(); // 멤버
	public AppDraftVO getTerraAppChabun(); // 결재
	public TerraBoardVO getTerraBoardChabun(); // 보드
	public TerraRboardVO getTerraRboardChabun(); // 댓글
	public TerraWorkVO getTerraWorkChabun(); // 근태
	
}
