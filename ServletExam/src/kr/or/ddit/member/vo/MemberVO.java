package kr.or.ddit.member.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DB 테이블에 있는 데이터를 객체화 하기 위한 클래스
 *
 * <p>
 * DB테이블의 컬럼명과 클래스의 멤버변수를 매핑하는 역할을 수행한다.
 * </p>
 *
 */

public class MemberVO {
	
	private String memId;		// 회원 아이디
	private String memName;		// 회원이름
	private String memTel;		// 회원전화번호
	private String memAddr;		// 회원주소
	
	private long atchFileId = -1;	// 첨부파일 ID
	
	private Date regDt;			// 등록일시

	public MemberVO(String memId, String memName, String memTel, String memAddr) {
		super();
		this.memId = memId;
		this.memName = memName;
		this.memTel = memTel;
		this.memAddr = memAddr;
		
	}

	public MemberVO() {
		
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemTel() {
		return memTel;
	}

	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}

	public String getMemAddr() {
		return memAddr;
	}

	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}

	public Date getRegDt() { // 날짜
		return regDt;
	}
	
	
	public long getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(long atchFileId) {
		this.atchFileId = atchFileId;
	}

	public String getRegDtDisplay() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// MM => 달(월)  /  mm => 분(시간) 으로 출력됨
		
		return sdf.format(this.regDt);
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "MemberVo [memId=" + memId + ", memName=" + memName + ", memTel=" + memTel + ", memAddr=" + memAddr
				+ ", regDt=" + regDt + "]";
	}
	
	
}
