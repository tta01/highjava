package kr.or.ddit.member.service;

import java.util.Date;
import java.util.List;
import kr.or.ddit.member.vo.BoardVO;

public interface IBoardService {
	
	public int savePost(BoardVO bv);
	
	public int modifyText(BoardVO bv);
	
	public int deleteText(long boardNo);
	
	public List<BoardVO> searchText(BoardVO bv);
	
	public boolean checkInfo(long boardNo);
	
	public List<BoardVO> selectAll();

}
