package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.or.ddit.Util.JDBCUtil3;
import kr.or.ddit.member.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//싱글톤패턴
	private static IBoardDao brdDao;
	
	private BoardDaoImpl() {
		brdDao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardDao getInstance() {
		
		if(brdDao == null) {
			brdDao = new BoardDaoImpl();
		}
		return brdDao;
	}
	

	@Override
	public int writing(BoardVO bv) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = " INSERT INTO mvc_board ("
					 + "   mvc_no, "
					 + "   mvc_title, "
					 + "   mvc_writer, "
					 + "   mvc_date,  "
					 + "   mvc_content "
					 + ") VALUES(mvc_seq.nextval, ?, ?, sysdate, ?)";
			
			pstmt = conn.prepareStatement(sql);
//			pstmt.setLong(1, bv.getBoardNo());
			pstmt.setString(1, bv.getTitle());
			pstmt.setString(2, bv.getWriter());
			pstmt.setString(3, bv.getContent());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int updataText(BoardVO bv) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " update mvc_board "
					   + " set mvc_title =?, "
					   + " mvc_content =? "
					   + "where board_no = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bv.getTitle());
				pstmt.setString(2, bv.getContent());
				pstmt.setLong(3, bv.getBoardNo());
				
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteText(Long boardNo) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from mvc_board where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}
	
	@Override
	public boolean searchText(Long boardNo) {
		
		boolean isExist = false;
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql ="Select * from mvc_board where board_no = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, boardNo);
			
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
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return isExist;	
	}

	@Override
	public List<BoardVO> selectAll() {
		
		List<BoardVO> brdList = new ArrayList<BoardVO>(); 

		try {
			
			conn = JDBCUtil3.getConnection();
			
			stmt = conn.createStatement();
			
			String sql = "select * from mvc_board";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long boardNo = rs.getLong("MVC_NO");
				String title = rs.getString("MVC_TITLE");
				String writer = rs.getString("MVC_WRITER");
				Date date = rs.getTimestamp("MVC_DATE");
				String content= rs.getString("MVC_CONTENT");
				
				// 꺼낸 데이터들을 VO에 담고 VO를 List에 다시 담아야 함
				
				BoardVO bv = new BoardVO(boardNo, title, writer, date, content);
				
				brdList.add(bv);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return brdList;
	}
}
