package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpClient {
	
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] msg; // 데이터 수진을 위한 바이트 배열
	
	public UdpClient() {
		try {
			msg = new byte[100];
			
			// 소켓 객체 생성(명시하지 않으면 포트번호는 이용가능한 임의의 포트번호가 할당됨)
			ds = new DatagramSocket();
			
		} catch (SocketException ex) {
			ex.printStackTrace();
		}
	}

	// 시작 메서드
	public void start() {
		try {
			
			InetAddress serverAddr = InetAddress.getByName("192.168.145.41");
			dp = new DatagramPacket(msg, 1, serverAddr, 8888);
			ds.send(dp);
			
			dp = new DatagramPacket(msg, msg.length); // 초기화
			ds.receive(dp); // 누군가가 나에게 보내줄 때까지 멈춰있음
			
			System.out.println("현재 서버 시간 => "  + new String(dp.getData()));
			
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new UdpClient().start();
	}
}
