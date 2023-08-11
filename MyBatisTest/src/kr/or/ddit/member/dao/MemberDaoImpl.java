package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.Util.JDBCUtil3;
import kr.or.ddit.Util.MybatisUtil;
import kr.or.ddit.member.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao{
	
	private static IMemberDao memDao = null;
	private MemberDaoImpl() {
		
	}
	public static IMemberDao getInstance() {
		if(memDao == null) {
			
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}
	
	
	@Override
	public int insertMember(MemberVO mv) {
		
		SqlSession session = MybatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			
			cnt = session.insert("member.insertMember", mv);
			if(cnt > 0) {
				session.commit();
			}
			
		} catch (PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		SqlSession session = MybatisUtil.getInstance();
		
		try {
			cnt = session.update("member.updateMember", mv);
			
			if(cnt > 0) {
				session.commit();
			}
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		
		SqlSession session = MybatisUtil.getInstance();
		
		try {
			
			cnt = session.delete("member.deleteMerber", memId);
			
			if(cnt > 0) {
				session.commit();
			}
			
		} catch (PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
			
		} finally {
			session.close();
		}
			
			return cnt;
		}

	@Override
	public boolean checkMember(String memId) {
		
		boolean isExist = false;
		
		SqlSession session = MybatisUtil.getInstance(true);
		
		try {
			int cnt = session.selectOne("member.checkMember", memId);
			
			if(cnt > 0 ) {
				isExist = true;
			}
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		
		return isExist;	
	}

	@Override
	public List<MemberVO> selectAll() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>(); // 뒤 <> 안써도 되는데 넣어주는게 좋음
		// new~~ 안 쓰면 null이 나옴 / 메모리를 생성하는 것!! 메모리가 있어야 담을 수 있어!!!
		
		SqlSession session = MybatisUtil.getInstance();
		
		try {
			
			memList = session.selectList("member.selectAll");
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		
		return memList;
	}

	@Override
	public List<MemberVO> searchMemberVO(MemberVO paramMv) {
		
		List<MemberVO> memList = new ArrayList<MemberVO>(); 

		SqlSession session = MybatisUtil.getInstance(true);
		
		try {
			
			memList = session.selectList("member.searchMember", paramMv);
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			session.rollback();
		}finally {
			
		}
		return memList;
		}

	}	

