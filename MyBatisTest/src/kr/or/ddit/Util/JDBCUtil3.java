package kr.or.ddit.Util;
// properties파일을 읽어옴
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * db.properties파일의 내용으로 DB정보를 설정하는 방법
 * 방법2) ResourceBundle 객체 이용하기
 */

public class JDBCUtil3 {
	
	static ResourceBundle bundle; //객체변수 선언
	
	static {
		
		bundle = ResourceBundle.getBundle("db");
		
		try {
			
			// 드라이버 로딩 확인
			Class.forName(bundle.getString("driver"));
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
			return DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("username"),
					bundle.getString("password"));

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
