package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

// DAO : Data Access Object : DB의 data에 접근하기 위한 객체로 실제로 DB에 접근하는 객체

/**
 * 실제 DB와 연결해서 SQL문을 수행한 후 결과를 작성하여 서비스에 전달하는 DAO의 Interface
 */
public interface IMemberDao {

	/**
	 * MemberVO에 담겨진 데이터를 DB에 insert하기 위한 메서드
	 * @param mv DB에 등록할 데이터가 담겨진 MemberVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환됨.
	 */
	public int insertMember(MemberVO mv);
	
	/**
	 * MemberVO에 담겨진 데이터를 DB에 update하기 위한 메서드
	 * @param mv DB에 수정할 데이터가 담겨진 MemberVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환됨.
	 */
	public int updateMember(MemberVO mv);
	
	/**
	 * 해당 ID에 해당하는 회원정보를 삭제하기 위한 메서드
	 * @param memId 삭제할 회원 ID
	 * @return 삭제 성공하면 1, 실패하면 0 반환됨.
	 */
	public int deleteMember(String memId);
	
	/**
	 * 해당 ID에 해당하는 회원정보가 존재하는지 확인하기 위한 메서드
	 * @param memId 체크할 회원ID
	 * @return 해당 회원이 존재하면 true, 존재하지 않으면 false 리턴함
	 */
	public boolean checkMember(String memId);
	
	/**
	 * 전체 회원정보를 가져오기 위한 메서드
	 * @return 전체 회원정보를 담은 리스트
	 */
	public List<MemberVO> selectAll();
	
}
