package kr.or.ddit.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	static {
		try {

			// 드라이버 로딩 확인
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 완료!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 커넥션 객체 가져오기
	 * @return Connection 객체, 예외 발생시 null 리턴함.
	 */
	public static Connection getConnection() {
		
		try {
//			// 커넥션(연결)할 객체를 가져옴 주소,cmd에서 생성한 유저아이디, 비밀번호
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","tta","java");

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 자원반납
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection conn,
								Statement stmt,
								PreparedStatement pstmt,
								ResultSet rs) {
		
		if(rs != null) try {rs.close();} catch(SQLException ex) {}
		if(stmt != null) try {stmt.close();} catch(SQLException ex) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
		if(conn != null) try {conn.close();} catch(SQLException ex) {}
		
	}
}
