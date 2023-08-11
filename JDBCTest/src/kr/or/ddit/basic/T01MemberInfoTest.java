package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.or.ddit.Util.JDBCUtil3;

/*
	회원정보를 관리하는 프로그램을 작성하는데 


	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자 수료 삭제			---> delete
		3. 자료정			---> update
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
	conn = JDBCUtil3.getConnection();
	시작할 때 연결해주고 시작!! 내가 생성한 유틸을 겟 커넥션으로 가져와서 연결

Statement & Prepared => connection이 있어야 리턴 받아 생성할 수 있음
	pstmt = conn.prepareStatement(sql);
	stmt = conn.Statement(sql);
	
	근데 얘네가 오면 내가 그 메소드안에서 불러와야 하는 쿼리문도
	pstmt.setString(1, writer);
	stmt.setString(1, writer);
	이런 형식으로 가져와야해
	
	앞 번호는 순서 , 뒤는 쿼리문에서 불러올 자바식 이름
	String sql ="Select * from jdbc_board where board_writer = ?";
	여기에 들어가는 순서랑 맞기만 하면 되고, ? 갯수에 맞춰서 가져와야 해 이거 안 맞으면 sql오류남
	
	rs = pstmt.executeQuery();
	rs = stmt.executeQuery();
	불러온 쿼리문을 저장할 곳인데
	쿼리문을 select로 불러왔으면 무조건 query
	쿼리문을 select 이외의 것으로 불렀으면 update
	
	rs에 저장 후 출력하면되는데 보통 자료가 얼마나 입력되었는지 모르니까 while문으로 출력
	while() 조건에 rs에서 가져오고, 다음이 있으면 가져와야 하니까 (rs.next()) 이렇게 넣어주기
	
	String content = rs.getString("board_content");
	타입 가져올 이름 = rs.get타입("가져올 쿼리 테이블 명");
	
	다 가져왔으면 출력! 하고 예외처리 해주면 끝!!
	
ResultSet -> executeQurey (select문)
crud - executeUpddate (나머지) 

끝나면 반드시 close(); !! try&catch문 finally에 넣어줌

*/
public class T01MemberInfoTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	private static final Logger SQL_LOGGER = LogManager.getLogger("log4jexam.sql.Query");
	private static final Logger PARAM_LOGGER = LogManager.getLogger("log4jexam.sql.Parameter");
	private static final Logger RESULT_LOGGER = LogManager.getLogger(T01MemberInfoTest.class);
	// 클래스 명으로도 가능 / result debug가 출력 돼..?
	
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
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updataMember();
					break;
				case 4 :  // 전체 자료 출력
					selectAll();
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
	 * 전체 회원자료를 출력하기 위한 메서드
	 */
	private void selectAll() {
		try {
			
			conn = JDBCUtil3.getConnection();
			
			stmt = conn.createStatement();
			
			String sql = "select * from mymember";
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("-------------------------------------");
			System.out.println("ID\t생성일\t이  름\t전화번호\t\t주소");
			System.out.println("-------------------------------------");
			
			
			while(rs.next()) {
				String memId = rs.getString("mem_Id");
				String memName = rs.getString("mem_Name");
				String memTel = rs.getString("mem_Tel");
				String memAddr = rs.getString("mem_Addr");
				Date regDt= rs.getTimestamp("reg_Dt");
				
				System.out.println(memId + "\t" + regDt + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
			
			System.out.println("--------------------------------------");
			System.out.println("출력 작업 끝");
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}
	private	void deleteMember() {
		
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력해 주세요.");
		System.out.println("회원 ID >>");
		
		String memId = scan.next();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원의 정보를 삭제 했습니다.");
			} else {
				System.out.println(memId + "회원의 정보를 삭제 실패!!");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
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
			
			isExist = checkMember(memId);
			
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
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " update mymember "
				   +" set mem_name =?, "
				   +" mem_tel =?, "
				   +" mem_addr=? "
				+ "where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원의 정보를 수정 했습니다.");
			} else {
				System.out.println(memId + "회원의 정보를 수정 실패!!");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
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
			conn = JDBCUtil3.getConnection();
			
			String sql = " INSERT INTO mymember ( "
						 + "   mem_id, 	 "
						 + "   mem_name, "
						 + "   mem_tel,  "
						 + "   mem_addr, "
						 + "   reg_dt    "
						 + " ) VALUES ( ?, ?, ?, ?, sysdate) ";
			
			SQL_LOGGER.debug("SQL : " + sql);
			// 위의 sql 쿼리 문 ? 갯수대로 작성해주면 됨!
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memId);
			pstmt.setString(2,memName);
			pstmt.setString(3,memTel);
			pstmt.setString(4,memAddr);
			
			PARAM_LOGGER.warn("Parameter : " + "memId" + memId
						+ "memName" + memName + "memTel" + memTel + "memAddr" + memAddr );	
				
			// cnt => 갯수를 세어주는게 아니라 회원 정보의 유무를 묻는 것.
			int cnt = pstmt.executeUpdate(); // select만 query
			
			RESULT_LOGGER.debug("cnt : " + cnt); 
			
			if(cnt > 0 ) {
				System.out.println("회원 등록 성공!");
			}else {
				System.out.println("회원 등록 실패!!!");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}
	
	/**
	 * 회원아이디가 존재하는지 체크하기 위한 메서드
	 * @param memId 회원아이디
	 */
	private boolean checkMember(String memId) {
		
		boolean isExist = false;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select count(*) as cnt from mymember where mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery(); // select문으로 쓸 때만 '쿼리'!!!!
			
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
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return isExist;	
	}

	public static void main(String[] args) {
		T01MemberInfoTest memObj = new T01MemberInfoTest();
		memObj.start();
	}
}
