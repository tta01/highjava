package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.BoardVO;

public interface IBoardDao {
	
	public int writing(BoardVO bv);
	
	public int updataText(BoardVO bv);
	
	public int deleteText(long boardNo);
	
	public List<BoardVO> searchText(BoardVO bv);
	
	public boolean checkInfo(long boardNo);
	
	public List<BoardVO> selectAll();


	
}
