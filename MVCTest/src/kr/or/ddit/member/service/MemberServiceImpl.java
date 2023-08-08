package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	private IMemberDao memDao;
	
	private static IMemberService memService;

	
	private MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}
	
	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImpl();
		}
		
		return memService;
	}

	@Override
	public int registMember(MemberVO mv) {
		
		int cnt = memDao.insertMember(mv);
		
/*		 
 		트랜잭션 처리
 
 		 보내는 사람 계좌에서 100만 인출
		 다오에 업데이트
		
		 받는 사람 계좌에 100만 입금
		 다오에 업데이트				*/
		
		return cnt;
	}

	@Override
	public int modifyMember(MemberVO mv) {
		
		int cnt = memDao.updateMember(mv);
		
		return cnt;
	}

	@Override
	public int removeMember(String memId) {
		
		int cnt = memDao.deleteMember(memId);	
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean isExist = memDao.checkMember(memId);
		
		return isExist;
	}

	@Override
	public List<MemberVO> selectAll() {
		
		List<MemberVO> memList = memDao.selectAll();
		
		return memList;
	}

}
