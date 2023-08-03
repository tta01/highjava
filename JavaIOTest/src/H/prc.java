package H;

import java.io.File;
import java.io.IOException;

public class prc {
	
	public static void main(String[] args) throws IOException {
		// file 객체 만들기
		
		File file = new File("e:/D_Other/test10.txt");
		System.out.println("파일 이름 : " + file.getName());
		System.out.println("파일 여부 : " + file.isFile());
		System.out.println("디렉토리(폴더)여부 : " + file.isDirectory());
		
		File file2 = new File("e:/D_Other");
		System.out.println(file.getName() + "은");
		
		if(file.isFile()) {
			System.out.println("파일이다.");
		} else if (file2.isDirectory()) {
			System.out.println("디렉토리(폴더)다.");
		}  // 파일도 폴더도 아닐 때 어떻게 출력할꺼야
		System.out.println("========================");
		
		File file3 = new File(file2, "test10.txt");
		System.out.println(file3.getName() + "의 용량(크기) : " + file3.length());
		
		File file4 = new File(".\\D_Other\\test\\..","test.txt");
		System.out.println("절대경로 : " + file4.getAbsolutePath());
		System.out.println("경로 : " + file4.getPath());
		System.out.println("표준 경로 : " + file4.getCanonicalPath());
		System.out.println("-------------------------------------");
		
		File file5 = new File("e:/D_Other/연습");
		if(file5.mkdir()) {
			System.out.println();
		}
		
	}
}
