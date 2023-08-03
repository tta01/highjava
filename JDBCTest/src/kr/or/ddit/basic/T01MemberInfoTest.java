package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.Util.JDBCUtil;

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
Statement
Prepared & Statement
ResultSet

*/
public class T01MemberInfoTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
				
					break;
				case 3 :  // 자료 수정
				
					break;
				case 4 :  // 전체 자료 출력
			
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
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
			
			isExist = checkMember(memId);
			
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
		

		try {
			conn = JDBCUtil.getConnection();
//			// 커넥션(연결)할 객체를 가져옴 주소,cmd에서 생성한 유저아이디, 비밀번호
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tta","java");
			
			String sql = " INSERT INTO mymember ( "
						 + "   mem_id, 	 "
						 + "   mem_name, "
						 + "   mem_tel,  "
						 + "   mem_addr, "
						 + "   reg_dt    "
						 + " ) VALUES ( ?, ?, ?, ?, sysdate) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memId);
			pstmt.setString(2,memName);
			pstmt.setString(3,memTel);
			pstmt.setString(4,memAddr);
			
			// cnt => 갯수를 세어주는게 아니라 회원 정보의 유무를 묻는 것.
			int cnt = pstmt.executeUpdate(); 
			if(cnt > 0 ) {
				System.out.println("회원 등록 성공!");
			}else {
				System.out.println("회원 등록 실패!!!");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		
			
			 
		}
		
		
		
	}
	/**
	 * 회원아이디가 존재하는지 체크하기 위한 메서드
	 * @param memId 회원아이디
	 */
	private boolean checkMember(String memId) {
		
		boolean isExist = false;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select count(*) as cnt from mymember where mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			while(rs.next()) {
				cnt = rs.getInt("CNT");
			}
			
			if(cnt > 0) {
				isExist = true;
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return isExist;
		
	}

	public static void main(String[] args) {
		T01MemberInfoTest memObj = new T01MemberInfoTest();
		memObj.start();
	}

}






