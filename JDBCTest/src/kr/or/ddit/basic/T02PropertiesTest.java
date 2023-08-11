package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 외부의 properties 파일을 읽어와 Properties객체로 처리하기
 * @author PC-10
 */
public class T02PropertiesTest {
	public static void main(String[] args) {
		
		// 읽어온 정보를 저장할 Properties객체 생성
		Properties prop = new Properties();
		
		try {
			// Properties 객체로 파일내용 읽기
			prop.load(new FileInputStream("res/db.properties"));
			
			// 읽어온 내용 출력하기
			Enumeration<String> Keys = (Enumeration<String>) prop.propertyNames();
			
			while(Keys.hasMoreElements()) {
				String key= Keys.nextElement();
				String Value = prop.getProperty(key);
				System.out.println(key + " => " + Value);
				
			}
			System.out.println("출력 끝");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	

}
