package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class T01FileTest {
	public static void main(String[] args) throws IOException {
		
		// File객체 만들기 연습
		 
		// 1. new File(String 파일 또는 경로명)
		//	=> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는
		//	  '\\'를 사용하거나 '/'를 사용할 수 있다.
		File file = new File("e:/D_Other/test.txt");
		System.out.println("파일명 : " + file.getName());
		System.out.println("파일여부 : " + file.isFile());
		System.out.println("디렉토리(폴더)여부 : " + file.isDirectory());
		System.out.println("-----------------------------");
		
		File file2 = new File("e:/D_Other");
		System.out.println(file2.getName() + "은");
		if(file2.isFile()) {
			System.out.println("파일");
		} else if(file2.isDirectory()) {
			System.out.println("디렉토리(폴더)");
		}
		System.out.println("-----------------------------");
		
		// 2. new File(File parent, String child)
		//	=> 'patent' 디렉토리 안에 있는 'child'파일 또는 디렉토리를 갖는다.
		File file3 = new File(file2, "test.txt");
		System.out.println(file3.getName() + "의 용량(크기) : " + file3.length());
		// 파일 용량 확인 할 때 파일명.length()
		
		// 3. new File(String parent, String child)
		File file4 = new File(".\\D_Other\\test\\..", "test.txt");
		System.out.println("절대경로 : " + file4.getAbsolutePath());
		System.out.println("경로 : " + file4.getPath());
		System.out.println("표준 경로 : " + file4.getCanonicalPath());
		System.out.println("---------------------------------------");
		
/*		file4 = new File("E:/highjava/workspace/JavaIOTest/src/img/hello.jpg");
 
	 .. -> 상위폴더  / . -> 내 위치	(상대경로 : 내가 있는 자리에 따라 내 위치가 달라짐)
	window를 제외하고는 루트의 시작은 드라이브 이름으로 시작 => 절대경로
	 상대경로의 장점 : 계속 옮길 때? & 프로젝트 할 때 & 

	----------------------------------------------------------------------------------	

	디렉토리 (폴더) 만들기
	
	1. mkdir() => File 객체의 경로 중 마지막 위치의 디렉토리를 만든다. / mkdir => 메이크디렉터리
		중간의 경로가 모두 미리 만들어져 있어야 한다.
		
	2. mkdirs() => 중간의 경로가 없으면 중간의 경로도 새롭게 만든 후 마지막 위치의 경로 디렉토리를 만들어 준다.
	
	=> 위 두 메서드 모두 만들기를 성공하면 true, 실패하면 false 반환
 */ 
		
	File file5 = new File("e:/D_Other/연습용");
	if(file5.mkdir()) {
		System.out.println(file5.getName() + "만들기 성공!");
	} else {
		System.out.println(file5.getName() + "만들기 실패!!!");
	}
	File file6 =  new File("e:/D_Other/test/java/src");
	if(file6.mkdirs()) {
		System.out.println(file6.getName() + "만들기 성공!");
	} else {
		System.out.println(file6.getName() + "만들기 실패!!!");
	}
	}
}
