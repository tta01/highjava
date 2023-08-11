package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.BoardVO;

public interface IBoardDao {
	
	public int writing(BoardVO bv);
	
	public int updataText(BoardVO bv);
	
	public int deleteText(Long boardNo);
	
	public boolean searchText(Long boardNo);
	
	public List<BoardVO> selectAll();
	
}
