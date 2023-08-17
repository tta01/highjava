package kr.or.ddit.basic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class URLTest {
	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		// URL 클래스 => 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 주소를 관리하는 클래스
		
		URL url = new URL("http","ddit.or.kr",80, "/main/index.html?ttt=123&age=18"); // String으로 써도 됨
		
		System.out.println("전체 URL주소 : " + url.toString());
		// url을 구성하는 요소들
		System.out.println("protocol : " + url.getProtocol()); 	// http
		System.out.println("host : " + url.getHost()); 			// ddit.or.kr
		System.out.println("query : " + url.getQuery());		// ttt=123
		System.out.println("file : " + url.getFile());			// /main/index.html?ttt=123 (쿼리 정보 포함)
		System.out.println("path : " + url.getPath());			// /main/index.html (쿼리 정보 미포함)
		System.out.println("port : " + url.getPort());			// 80
		System.out.println("ref : " + url.getRef());			// kkk (참조값)
		System.out.println();
		
		System.out.println(url.toExternalForm());
		System.out.println(url.toString());
		System.out.println(url.toURI().toString());
		
	}
}
// URL객체를 param으로 요구하는 경우들이 있음!
