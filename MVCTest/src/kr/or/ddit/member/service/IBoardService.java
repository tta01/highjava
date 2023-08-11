package kr.or.ddit.member.service;

import java.util.List;
import kr.or.ddit.member.vo.BoardVO;

public interface IBoardService {
	
	public int savePost(BoardVO bv);
	
	public int modifyText(BoardVO bv);
	
	public int deleteText(Long boardNo);
	
	public boolean searchText(Long boardNo);
	
	public List<BoardVO> selectAll();
	

}
