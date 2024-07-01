package com.kos.tr.old.board.service;

import java.util.List;

import com.kos.tr.old.board.vo.TerraBoardVO;



public interface TerraBoardService {
	
	public int tBoardInsert(TerraBoardVO tbvo);
	public List<TerraBoardVO> tBoardSelectAll(TerraBoardVO tbvo);
	public List<TerraBoardVO> tBoardSelect(TerraBoardVO tbvo);	
	public int tBoardBhit(TerraBoardVO tbvo);
	public int tBoardDelete(TerraBoardVO tbvo);
	public int tBoardUpdate(TerraBoardVO tbvo);

}
