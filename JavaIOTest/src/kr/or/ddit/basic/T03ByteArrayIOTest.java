package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class T03ByteArrayIOTest {
	public static void main(String[] args) { // 바이트 배열 복사
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		/* 직접 복사하는 방법
		outSrc = new byte[inSrc.length];
		for(int i=0; i<inSrc.length; i++) {
		outSrc[i] = inSrc[i];
		}
	*/
		
		// arraycopy를 이용한 배열 복사 방법
/*		outSrc = new byte[inSrc.length]; // 공간확보
		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);
		
		System.out.println("arraycopy 후 outSrc => " + Arrays.toString(outSrc));
*/		
		// 스트림 객체를 이용한 방법
		 ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data = 0; // 읽어온 데이터를 저장할 변수
		
//		read()메서드 => byte단위로 데이터를 읽어와 int형으로 반환
//						더이상 읽을 데이터가 없으면 -1을 반환
		while((data = bais.read()) != -1) {
			baos.write(data);
		}
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));

	}
}
