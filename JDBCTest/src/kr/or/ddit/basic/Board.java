package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import kr.or.ddit.Util.JDBCUtil3;

/*
 위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
 
------------------------------------------------------------

 시퀀스의 다음 값 구하기
  시퀀스이름.nextVal
 */

public class Board {
	public static void main(String[] args) {
		new Board().start();
	}
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner sc = new Scanner(System.in);
	
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 메  뉴  선 택 ===");
		System.out.println("  1. 새글 작성");
		System.out.println("  2. 글 수정");
		System.out.println("  3. 글 삭제");
		System.out.println("  4. 전체목록 출력");
		System.out.println("  5. 검색");
		System.out.println("  6. 게시판 종료");
		System.out.println("----------------------");
		System.out.print("원하는 메뉴 선택 >> ");
	}
	
	public void start() {
		int menu = 0;
		
		do {
			displayMenu();
			menu = sc.nextInt();
			switch(menu){
				case 1 : // 새글작성
					writing();
					break;
				case 2 :  // 글 수정
					updataText();
					break;
				case 3 :  // 글 삭제
					deleteText();
					break;
				case 4 :  // 전체 목록 출력
					selectAll();
					break;
				case 5 :  // 검색
					searchText();
					break;
				case 6 :  // 종료
					System.out.println("게시판을 종료합니당당");
//					System.exit(0);
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		
		}while(menu != 6);
	}

	private void searchText() {
		
		String title = "";
		String writer = "";
		
		sc.nextLine();
		
		System.out.println();
		System.out.println("검색은 작성자로만 검색 할 수 있습니다.");
		System.out.println("검색할 정보를 입력하세요.");
		System.out.println("작성자  : ");
		writer = sc.nextLine().trim();	//공백제거
		
		System.out.println();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql ="Select * from jdbc_board where board_writer = ?";
			//물음표의 갯수!!만큼 쓰세ㅠ여
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
		
			rs = pstmt.executeQuery();
			
			System.out.println("-------------------------------------");
			System.out.println("작성 번호\t제목\t작성자\t작성 날짜\t작성 내용");
			System.out.println("-------------------------------------");
			
			while(rs.next()) {
				int No = rs.getInt("board_no");
				title = rs.getString("board_title");
				writer = rs.getString("board_writer");
				Date date = rs.getTimestamp("board_date");
				String content = rs.getString("board_content");
				
				String regDt = getRegDtDisplay(date);

				
				System.out.println(No + "\t" + title + "\t" + writer + "\t" + regDt + "\t" + content);
			}
			
			System.out.println("--------------------------------------");
			System.out.println("출력 작업 완료");

		}catch (SQLException ex) {
				ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}


	private void selectAll() {
		
		try {
		
			conn = JDBCUtil3.getConnection();
			
			stmt = conn.createStatement();
			
			String sql = "select * from jdbc_board";
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("-------------------------------------");
			System.out.println("작성 번호\t제목\t작성자\t작성 날짜\t작성 내용");
			System.out.println("-------------------------------------");
			
			while(rs.next()) {
				int No = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				Date date = rs.getTimestamp("board_date");
				String content = rs.getString("board_content");
				
				String regdt = getRegDtDisplay(date);
				
				System.out.println(No + "\t" + title + "\t" + writer + "\t" + regdt + "\t" + content);
			
			}
			
			System.out.println("--------------------------------------");
			System.out.println("출력 작업 완료");
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}	
}

	private void deleteText() {
		
		System.out.println();
		System.out.println("삭제할 게시글의 작성자를 입력해 주세요.");
		System.out.println("작성자  : ");
		
		String writer = sc.next();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from jdbc_board where board_writer = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(writer + "님의 글을 삭제했습니다.");
			} else {
				System.out.println(writer + "님의 글 삭제 실패ㅜㅜ");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}
	
	
	private void updataText() {
		
		boolean isExist = false;
		String writer = "";
		do {
			
			System.out.println("수정할 글의 작성자를 입력해주세요 : ");
			System.out.println("작성자  : ");
			writer = sc.next();
			
			isExist = checkInfo(writer);
			
			if(!isExist) {
				System.out.println("작성자가 " + writer + "인 글은 " + " 존재하지 않습니다.\n다시 입력해 주세요.");
			}	
		} while(!isExist);
		
		System.out.println("제목 입력 : ");
		String title = sc.next();
		
		sc.nextLine();
		
		System.out.println("내용 입력 : ");
		String content = sc.nextLine();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = " update jdbc_board "
				   + " set board_title =?, "
				   + " board_content =? "
				   + "where board_writer = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println( "수정 성공");
			} else {
				System.out.println("글 수정 실패!!");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	private void writing() {
		
		System.out.println("제목 입력 : ");
		String title = sc.next();
		
		System.out.println("작성자 입력 : ");
		String writer = sc.next();
		
		sc.nextLine();	
		
		System.out.println("내용 입력 : ");
		String content = sc.nextLine();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = " INSERT INTO jdbc_board ("
					 + "   board_no, "
					 + "   board_title, "
					 + "   board_writer, "
					 + "   board_date,  "
					 + "   board_content "
					 + ") VALUES(board_seq.nextval, ?, ?, sysdate, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2,writer);
			pstmt.setString(3,content);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0 ) {
				System.out.println("글 작성 완료");
			}else {
				System.out.println("글 작성 실패");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}
	
	private boolean checkInfo(String writer) {
		
		boolean isExist = false;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select count(*) as cnt from jdbc_board where board_writer = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			
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
	
	public String getRegDtDisplay(Date regDt) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// MM => 달(월)  /  mm => 분(시간) 으로 출력됨
		
		return sdf.format(regDt);
	}
}
