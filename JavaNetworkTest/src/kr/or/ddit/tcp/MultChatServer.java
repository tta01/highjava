package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultChatServer {

	// 대화명, 클라이언트 Socket을 저장하기 위한 Map변수 선언
	private Map<String, Socket> clients;

	public MultChatServer() {
		clients = Collections.synchronizedMap(new HashMap<String, Socket>());
	}

	// 시작 메서드
	public void serverStart() {

		ServerSocket server = null;
		Socket socket = null;

		try {

			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");

			while (true) {
				// 클라이언트의 접속을 대기한다.
				socket = server.accept();

				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속하였습니다.");

				// 메시지 전송 처리를 하는 스레드 생성 및 실행
				Thread th = new ServerReciver(socket);
				th.start();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 대화방 즉, Map에 저장된 전체 유저에게 안내메시지를 전송하기 위한 메서드
	 * @param msg 안내메시지
	 */
	public void sendMessage(String msg) {
	
		Iterator<String> iter = clients.keySet().iterator();
		
		while(iter.hasNext()) {
			
			String name = iter.next(); //대화명
			Socket socket = clients.get(name);
			
			// 대화명에 해당하는 Socket객체 이용하여 메시지 보내기
			try {
				
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(msg); // 메시지 보내기
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 대화방, 즉 Map에 저장된 전체 유저에게 대화메시지를 전송하기 위한 메서드
	 * @param msg
	 * @param from
	 */
	public void sendMessage(String msg, String from) {
		sendMessage("[" + from + "]" + msg);
	}

	// 서버에서 클라이언트로부터 수신한 메시지를 처리하기 위한 클래스
	// Inner클래스로 정의함(Inner클래스에서는 부모클래스의 멤버들을 직접 접근할 수 있다.)
	class ServerReciver extends Thread { // 클래스 안에 클래스 = 내부 클래스

		private Socket socket;
		private DataInputStream dis;
		private String name; // 대화명

		public ServerReciver(Socket socket) {
			this.socket = socket;

			try {
				dis = new DataInputStream(socket.getInputStream()); // 기본 스트림. 아래 readUTF때문에 필요함
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		@Override
		public void run() {

			try {
				// 서버에서는 클라이언트가 보내는 최초의 메시지 즉, 대화명을 수신해야 한다.
				name = dis.readUTF();

				// 대화명을 받아서 다른 모든 클라이언트들에게 대화방 참여 메시지를 보낸다.
				sendMessage("#" + name + "님이 입장했습니다.");
				
				//대화명과 소켓정보를 Map에 저장한다.
				clients.put(name,socket);
				
				System.out.println("현재 서버 접속자 수는 " + clients.size() + "명 입니다.");
				
				// 이후의 메시지 처리는 반복문으로 처리한다.
				// 한 클라이언트가 보낸 메시지를 다른 모든 클라이언트에게 보내준다.
				while(dis != null) {
					sendMessage(dis.readUTF(),name);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
				}
				
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				// 이 finally영역이 실행된다는 것은 클라이언트의 접속이 종료되었다는 의미이다.
				
				sendMessage(name + "님이 나가셨습니다.");
				
				// Map에서 해당 대화명을 삭제한다.
				clients.remove(name);
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속을 종료하였습니다.");


				}
				
				System.out.println("현재 서버 접속자 수는 " + clients.size() + "명 입니다.");

			}
		}
	public static void main(String[] args) {
		new MultChatServer().serverStart();
	}
	}


