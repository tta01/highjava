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
import kr.or.ddit.member.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static IMemberDao memDao;

	public static IMemberDao getInstance() {

		if (memDao == null) {
			memDao = new MemberDaoImpl();
		}

		return memDao;
	}

	@Override
	public int insertMember(MemberVO mv) {

		int cnt = 0;

		try {
			conn = JDBCUtil3.getConnection();

			String sql = " INSERT INTO mymember ( " + "   mem_id, 	 " + "   mem_name, " + "   mem_tel,  "
					+ "   mem_addr, " + "   reg_dt    " + " ) VALUES ( ?, ?, ?, ?, sysdate) ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());

			// cnt => 갯수를 세어주는게 아니라 회원 정보의 유무를 묻는 것.
			cnt = pstmt.executeUpdate(); // try안에 있어서 리턴에 오류가 남. 그래서 밖으로 빼줌

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {

		int cnt = 0;

		try {
			conn = JDBCUtil3.getConnection();

			String sql = " update mymember " + " set mem_name =?, " + " mem_tel =?, " + " mem_addr=? "
					+ "where mem_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());

			cnt = pstmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		int cnt = 0;

		try {

			conn = JDBCUtil3.getConnection();

			String sql = "delete from mymember where mem_id = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			cnt = pstmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}

		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {

		boolean isExist = false;

		try {
			conn = JDBCUtil3.getConnection();

			String sql = "select count(*) as cnt from mymember where mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			rs = pstmt.executeQuery(); // select문으로 쓸 때만 '쿼리'!!!!

			int cnt = 0;

			while (rs.next()) {
				cnt = rs.getInt("CNT");
			}

			if (cnt > 0) {
				isExist = true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}

		return isExist;
	}

	@Override
	public List<MemberVO> selectAll() {

		List<MemberVO> memList = new ArrayList<MemberVO>(); // 뒤 <> 안써도 되는데 넣어주는게 좋음
		// new~~ 안 쓰면 null이 나옴 / 메모리를 생성하는 것!! 메모리가 있어야 담을 수 있어!!!
		try {

			conn = JDBCUtil3.getConnection();

			stmt = conn.createStatement();

			String sql = "select * from mymember";

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String memId = rs.getString("mem_Id");
				String memName = rs.getString("mem_Name");
				String memTel = rs.getString("mem_Tel");
				String memAddr = rs.getString("mem_Addr");
				Date regDt = rs.getTimestamp("reg_Dt");

				// 꺼낸 데이터들을 VO에 담고 VO를 List에 다시 담아야 함

				MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
				mv.setRegDt(regDt);

				memList.add(mv);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}

		return memList;
	}
}
