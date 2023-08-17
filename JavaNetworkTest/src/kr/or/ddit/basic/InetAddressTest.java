package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	
/* 	InetAddress 클래스 => IP주소 정보를 다루기 위한 클래스
	
	getByName()메서드는 www.naver.com 또는 SEM-PC 등과 같은
	머신 이름이나 IP주소를 파라미터로 사용하여 유효한 InetAddress객체를 제공한다.
	IP주소 자체를 넣으면 주소 구성 자체의 유효성 정도만 체크가 이루어진다.
	(유효하지 않으면 UnknownHostException 예외 발생!)
*/
	
	public static void main(String[] args) throws UnknownHostException {
		// 네이버 사이트의 IP정보 가져오기
		InetAddress naverIP = InetAddress.getByName("www.naver.com");
		
		System.out.println("Host Name => " + naverIP.getHostName());
		System.out.println("Host Address => " + naverIP.getHostAddress());
		
		System.out.println();
		
		
		// 자기 자신 컴퓨터의 IP정보 가져오기
		InetAddress localIP = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 Host Name => " + localIP.getHostName());
		System.out.println("내 컴퓨터의 Host Address => " + localIP.getHostAddress());
		System.out.println();
		
		// IP정보가 여러개인 호스트의 정보 가져오기
		InetAddress[] naverIps = InetAddress.getAllByName("www.naver.com");
		
		for(InetAddress iAddr : naverIps) {
			System.out.println(iAddr.toString());
		}
	}
}
