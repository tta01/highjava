package kr.or.ddit.Util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * SQLSession객체를 제공하는 팩토리 클래스 
 */
public class MybatisUtil {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
	
	try {
		// 1-1. xml문 읽어오기
		// 설정파일의 인코딩 설정(한글처리를 위해서)
		Charset charset = Charset.forName("UTF-8");
		Resources.setCharset(charset);
		Reader rd = 
				Resources.getResourceAsReader("config/mybatis-config.xml"); // 
		
		// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
		
		rd.close(); //Reader 객체 닫기.
		
	} catch (IOException ex) {
		ex.printStackTrace();
	}
	}
	/**
	 *  SqlSession 객체를 제동하는 팩토리 메서드
	 * @return SqlSession 객체
	 */
	public static SqlSession getInstance() {
		return sqlSessionFactory.openSession();
	}
/**
 * SqlSession 객체를 제동하는 팩토리 메서드
 * @param autoCommit 오토커밋 여부
 * @return SqlSession 객체
 */
	public static SqlSession getInstance(boolean autoCommit) { // 오버로드
		return sqlSessionFactory.openSession(autoCommit);
	}
	
}
