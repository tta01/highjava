package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MyBatisTest {
	
	public static void main(String[] args) {
		// myBatis를 이용하여 DB자료를 처리하는 작업 순서
		
		// 1. myBatis의 환경설정파일을 읽어와 실행시킨다.
		
		SqlSessionFactory sessionFactory = null;
		
		try {
			// 1-1. xml문 읽어오기
			// 설정파일의 인코딩 설정(한글처리를 위해서)
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = 
					Resources.getResourceAsReader("config/mybatis-config.xml"); // 
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close(); //Reader 객체 닫기.
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.

		// 2-1. insert작업 연습
		System.out.println("insert작업 시작");
		
		// 1) 저장할 데이터를 VO에 담는다.
		MemberVO mv = new MemberVO();
		mv.setMemId("d002");
		mv.setMemName("강감찬");
		mv.setMemTel("010-1234-4567");
		mv.setMemAddr("경남 진해시");
		
		// 2) SqlSession 객체 변수를 이용하여 해당 쿼리문을 실행한다.
		//	형식) SqlSession객체.insert("namespace값.id값", 파라미터객체)
		//	반환값 : 성공한 레코드 수
		SqlSession sqlSession = sessionFactory.openSession(false); // autoCommit여부
		
		try {
			int cnt = sqlSession.insert("memberTest.insertMember", mv);
			if(cnt > 0 ) {
				System.out.println("insert작업 성공");
				sqlSession.commit(); //커밋
			}else {
				System.out.println("insert작업 실패!!!");
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			sqlSession.rollback(); // 롤백
		}finally {
			sqlSession.close(); //세션 닫기
		}		
		System.out.println("------------------------------------");
		
		// 2-2. update 작업 연습
		System.out.println("update작업 시작!");
		
		MemberVO mv2 = new MemberVO();
		mv2.setMemId("d002");
		mv2.setMemName("메롱");
		mv2.setMemTel("010-1111-2222");
		mv2.setMemAddr("대전시");
		
		sqlSession = sessionFactory.openSession();
		
		try {
			
			// update()메서드의 반환값도 성공한 레코드 수 이다.
			int cnt = sqlSession.update("memberTest.updateMember", mv2);
			
			if(cnt > 0) {
				System.out.println("update작업 성공");
				sqlSession.commit(); //커밋
			}else {
				System.out.println("update작업 실패!!!");
			}
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			sqlSession.rollback();
		}finally {
			sqlSession.close();
		}
		
		
		
		
	}
}
