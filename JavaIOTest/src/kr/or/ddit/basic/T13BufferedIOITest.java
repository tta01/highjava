package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 입출력 성능향상을 위한 보조스트림 예제2 (문자기반의 Buffered스트림 예제)
 */

public class T13BufferedIOITest {

	public static void main(String[] args) {

		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader("src/kr/or/ddit/basic/T12BufferedIOTest.java");

			br = new BufferedReader(fr);
			
			String tmpStr = "";
			
			while((tmpStr = br.readLine()) != null) {
				System.out.println(tmpStr);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
