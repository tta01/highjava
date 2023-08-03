package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 프린터기능 제공 보조스트림
 */
public class T15PrintStreamTest {

	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("e:/D_Other/print.txt");
		
		/*
		 	PrintStream은 모든 자료형을 출력할 수 있는 기능을 제공하는 보조스트림이다.
		 */
		PrintStream out = new PrintStream(fos);
		out.print("안녕하세요. PrintStream 입니다.\n");
		out.println("안녕하세요. PrintStream 입니다2.");
		out.println("안녕하세요. PrintStream 입니다3.");
		out.print(out); // 객체 출력
		out.println(3.14);
		
		out.close();
		
	System.out.println("출력 완료");
	/*
	 	PrintStream은 데이터를 문자로 출력하는 기능을 수행함.
	 	향상된 기능의 PrintWriter가 추가되었지만 계속 사용됨.
	 	
	 	PrintWriter가 PrintStream보다 다양한 인코딩 처리된 문자로 표현하는데 적합하다.
	 */
	
	FileOutputStream fos2 = new FileOutputStream("e:/D_Other/print2.txt");
	PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos2, "cp949"));
	pw.print("안녕하세요. PrintWriter 입니다.\n");
	pw.println("안녕하세요. PrintWriter 입니다2.\n");	
	pw.println("안녕하세요. PrintWriter 입니다3.\n");	
	pw.print(fos2);
	pw.println(3.14);
	
	pw.close();
	
	System.out.println("출력완료 2");
	
	}
}
