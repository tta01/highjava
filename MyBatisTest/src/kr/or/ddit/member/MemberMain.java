package kr.or.ddit.member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.Util.JDBCUtil3;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    reg_dt DATE DEFAULT sysdate, -- 등록일
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

4개의 인터페이스 => 알맞게 호출할 줄 알아야 함
Connection
Statement & Prepared => connection이 있어야 리턴 받아 생성할 수 있음
ResultSet -> executeQurey (select문)
crud - executeUpddate (나머지) 

끝나면 반드시 close(); !! try&catch문 finally에 넣어줌

*/
public class MemberMain {
	
	private Scanner scan;
	
	private IMemberService memService;
	
	
	public MemberMain() {
		scan = new Scanner(System.in); 
		memService = new MemberServiceImpl();
	}

	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){ // 여기서는 뷰!! 
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 자료 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------"); 
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){ // controller 기능
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updataMember();
					break;
				case 4 :  // 전체 자료 출력
					selectAll();
					break;
				case 5 :  // 자료 검색
					searchMember();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	/**
	 * 회원정보를 검색하기 위한 메서드
	 */
	
	private void searchMember() {
	/*
	 	검색할 회원 ID, 회원이름, 전화번호, 주소 등을 입력하면
	 	입력한 정보만 사용하여 검색하는 기능을 구현하시오.
	 	주소는 입력한 값이 포함만 되어도 검색 되도록 한다.
	 	입력하지 않을 데이터는 엔터키로 다음으로 넘긴다.
	 */
		
		scan.nextLine(); //입력 버퍼 비우기
		
		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");
		System.out.println("회원 ID>>");
		String memId = scan.nextLine().trim();	//공백제거
		
		System.out.println("회원 이름 >> ");
		String memName = scan.nextLine().trim();	//공백제거
		
		System.out.println("회원전화번호 >> ");
		String memTel = scan.nextLine().trim();		//공백제거
		
		System.out.println("회원주소 >> ");
		String memAddr = scan.nextLine().trim();	//공백제거
		
		MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
		
		List<MemberVO> memList = memService.searchMemberVO(mv);
		
		System.out.println("-------------------------------------");
		System.out.println("ID\t생성일\t이  름\t전화번호\t\t주소");
		System.out.println("-------------------------------------");
		
		
		if(memList.size() == 0 ) {
			System.out.println("회원정보가 존재하지 않습니다.");
		} else {
			
			for (MemberVO mv2 : memList) {
				
				System.out.println(mv2.getMemId()+ "\t" + mv2.getRegDtDisplay() + "\t"
							+ mv2.getMemName() + "\t" + mv2.getMemTel() + "\t"
							+ mv2.getMemAddr());
			}
			
			System.out.println("--------------------------------------");
			System.out.println("출력 작업 끝");
		
		}
	}

	/**
	 * 전체 회원자료를 출력하기 위한 메서드
	 */
	private void selectAll() {
		
		System.out.println("-------------------------------------");
		System.out.println("ID\t생성일\t이  름\t전화번호\t\t주소");
		System.out.println("-------------------------------------");
		
		List<MemberVO> memList = memService.selectAll();
		
		if(memList.size() == 0 ) { // 
			System.out.println("회원정보가 존재하지 않습니다.");
		} else {
			for (MemberVO mv : memList) {
				
				System.out.println(mv.getMemId()+ "\t" + mv.getRegDtDisplay() + "\t"
							+ mv.getMemName() + "\t" + mv.getMemTel() + "\t"
							+ mv.getMemAddr());
			}
			
			System.out.println("--------------------------------------");
			System.out.println("출력 작업 끝");
		
		}
	}
	
	private	void deleteMember() { // View 부분
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력해 주세요.");
		System.out.println("회원 ID >>");
		
		String memId = scan.next();
		
		int cnt = memService.removeMember(memId);
		
		if(cnt > 0) {
			System.out.println(memId + "회원의 정보를 삭제 했습니다.");
		} else {
			System.out.println(memId + "회원의 정보를 삭제 실패!!");
		}
	}
	
	/**
	 * 회원정보를 수정하기 위한 메서드
	 */
	private void updataMember() {
		boolean isExist = false;
		
		String memId="";
		
		do {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력해 주세요.");
			System.out.println("회원 ID >>");
			
			memId = scan.next();
			
			isExist = memService.checkMember(memId);
			
			if(!isExist) {
				System.out.println("회원 ID가 " + memId + "인 회원은 " + " 존재하지 않습니다.\n다시 입력해 주세요.");
			}
			
		} while(!isExist);
		
		/*-------------------------------------------------------------------------------------------*/
		
		System.out.println("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.println("회원전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); // 입력버퍼 비우기
		
		System.out.println("회원주소 >> ");
		String memAddr = scan.nextLine();
		
		MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);

		int cnt = memService.modifyMember(mv);
		
		if(cnt > 0) {
			System.out.println(memId + "회원의 정보를 수정 했습니다.");
		} else {
			System.out.println(memId + "회원의 정보를 수정 실패!!");
		}
	}
	
	/**
	 * 회원정보를 등록하기 위한 메서드
	 */
	private void insertMember() {
		
		boolean isExist = false;
		
		String memId="";
		
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력해 주세요.");
			System.out.println("회원 ID >>");
			
			memId = scan.next();
			
			isExist = memService.checkMember(memId);
			
			if(isExist) {
				System.out.println("회원 ID가 " + memId + "인 회원은" + " 이미 존재합니다.\n다시 입력해 주세요.");
			}
			
		} while(isExist);
		
/*-------------------------------------------------------------------------------------------*/
		
		System.out.println("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.println("회원전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); // 입력버퍼 비우기
		
		System.out.println("회원주소 >> ");
		String memAddr = scan.nextLine();
		
		System.out.println(memId);
		System.out.println(memName);
		System.out.println(memTel);
		System.out.println(memAddr);
		
/*---------------------------------------------------------------------------------------*/
	
	MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
		
	int cnt = memService.registMember(mv);
		
	if(cnt > 0 ) {
		System.out.println("회원 등록 성공!");
	}else {
		System.out.println("회원 등록 실패!!!");
	}
	
	}
	
	public static void main(String[] args) {
		MemberMain memObj = new MemberMain();
		memObj.start();
	}
}
