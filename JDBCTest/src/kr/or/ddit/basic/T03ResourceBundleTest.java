package kr.or.ddit.basic;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class T03ResourceBundleTest {
/*
 	ResourceBundle 객체 => 확장자가 properties인 파일 정보를 읽어와
 						 Key값과 value값을 분리한 정보를 갖는 객체
 	
 	=> 읽어올 파일은 'key값 = value값' 형태로 되어 있어야 한다.
 */
	public static void main(String[] args) {
		//ResourceBundle객체 생성하기
		// => 파일명을 지정할 때 '파일명'만 지정하고 확장자는 지정하지 않는다.
		//	  (확장자는 항상.properties이기 때문에)
		ResourceBundle bundle = ResourceBundle.getBundle("db"); // properties파일만 읽음! 그 중에서 db를 읽으라는 의미
															//"DB",Locale.JAPANESE = 해당 나라에 맞춰서 언어를 바꿔줌
		
		// 모든 값 출력하기
		
		Enumeration<String> keys = bundle.getKeys();
		
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			
			// key값을 이용하여 value 값을 읽어오는 방법
			// => bundle객체변수.getString(key값)
			String value = bundle.getString(key);
			
			System.out.println(key + " => " + value);
		}
		
		System.out.println("☆ 출력 끝 ☆");
}
}
