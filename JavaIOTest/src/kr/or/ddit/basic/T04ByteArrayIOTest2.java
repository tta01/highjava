package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest2 {
	public static void main(String[] args) throws IOException { // 바이트 배열 복사
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4]; // 자료를 읽을 때 사용할 배열
		
		
		// 스트림 객체를 이용한 방법
		 ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int readBytes = 0; // 읽어온 바이트 수
		
//		read()메서드 => byte단위로 데이터를 읽어와 int형으로 반환
//						더이상 읽을 데이터가 없으면 -1을 반환
		while((readBytes = bais.read(temp)) != -1) {
			
			System.out.println("temp => " + Arrays.toString(temp));

			baos.write(temp, 0, readBytes);
		}
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));

	}
}
