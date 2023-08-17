package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
// 서버쪽의 방화벽이 꺼져있어야 실행이 되는 경우가 있음
public class TcpSocketClientTest {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		String serverIp = "127.0.0.1"; // = 로컬호스트 주소! 내 ip주소로 써도 됨
		// 자기 자신 컴퓨터를 나타내는 방법
		// IP : 127.0.0.1
		// hostname : localhost
		
		System.out.println(serverIp + " 서버에 접속 중 *3* ");
		
		// 소켓을 생성해서 서버에 연결을 요청한다.
		Socket socket = new Socket(serverIp, 7777); // 소켓서버 클래스에서 생성한 서버소켓번호 (7777)같아야 연결 가눙
		
		// 연결이 되면 이후의 명령이 실행된다.
		System.out.println(" 연결 완료<'3'> ");
		
		// 서버에서 보내온 메시지 받기
		// 메시지를 받기 위해 InputStream 객체를 생성한다.
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		//서버로부터 받은 메시지 출력하기
		System.out.println("받은 메시지 : " + dis.readUTF());
		
		System.out.println("연결 종료ㅜ__ㅜ");
		
		dis.close();
		
		socket.close();
		
		
		
		
		
		
	}

}
