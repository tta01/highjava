package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 기본타입 입출력 보조 스트림
 */
public class T14DataIOStreamTest {

	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		try {
			
			fos = new FileOutputStream("e:/D_Other/test.txt");
			dos = new DataOutputStream(fos);
			
			dos.writeUTF("홍길동");		// 문자열 데이터 출력
			dos.writeInt(17);			// 정수형으로 데이터 출
			dos.writeFloat(3.14f);		// 실수형(Float)으로 출력
			dos.writeDouble(3.14);		// 실수형(Double)으로 출력
			dos.writeBoolean(true);		// 논리형으로 출력
			
			System.out.println("데이터 출력 완료");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		////////////////////////////////////////////////////////////
		// 출력한 데이터 읽어와보기
		
		FileInputStream fis = null;
		
		DataInputStream dis = null;
		
		try {
			fis = new FileInputStream("e:/D_Other/test.txt");
			dis = new DataInputStream(fis);
			
			System.out.println("문자열 자료 : " + dis. readUTF()); 
			System.out.println("정수형 자료 : " + dis. readInt()); //4
			System.out.println("실수형(Double) 자료 : " + dis. readDouble()); // 4
			System.out.println("실수형(Float) 자료 : " + dis. readFloat()); // 8 더블과 플롯 자리를 바꾸니 읽어오는 바이트가 달라서 읽히는 수가 달라짐
			System.out.println("논리형 자료 : " + dis. readBoolean());//1
			
			System.out.println("읽기 작업 완료");
			
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try{
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
}
