package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.BoardDaoImpl;
import kr.or.ddit.member.dao.IBoardDao;
import kr.or.ddit.member.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao brdDao;
	
	private static IBoardService brdService;
	
	private BoardServiceImpl() {
		brdDao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(brdService == null) {
			brdService = new BoardServiceImpl();
		}
		return brdService;
	}
			

	@Override
	public int savePost(BoardVO bv) {
		
		int cnt = brdDao.writing(bv);
		
		return cnt;
	}

	@Override
	public int modifyText(BoardVO bv) {
		int cnt = brdDao.updataText(bv);
		return cnt;
	}

	@Override
	public int deleteText(long boardNo) {
		
		int cnt = brdDao.deleteText(boardNo);
		
		return cnt;
	}
 
	@Override
	public List<BoardVO> searchText(BoardVO bv) {
		
		List<BoardVO> brdList = brdDao.searchText(bv);
		
		return brdList;
	}

	@Override
	public List<BoardVO> selectAll() {
		
		List<BoardVO> brdList = brdDao.selectAll();
		
		return brdList;
	}

	@Override
	public boolean checkInfo(long boardNo) {
		
		boolean isExit = brdDao.checkInfo(boardNo);
		
		return isExit;
	}
}
