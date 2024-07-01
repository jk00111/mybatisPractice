package com.kos.tr.board.dao;

import java.util.List;

import com.kos.tr.board.vo.TerraBoardVO;



public interface TerraBoardDAO {
	
	public int tBoardInsert(TerraBoardVO tbvo);
	public List<TerraBoardVO> tBoardSelectAll(TerraBoardVO tbvo);
	public List<TerraBoardVO> tBoardSelect(TerraBoardVO tbvo);	
	public int tBoardBhit(TerraBoardVO tbvo);
	public int tBoardDelete(TerraBoardVO tbvo);
	public int tBoardUpdate(TerraBoardVO tbvo);

}
