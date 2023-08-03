package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T02FileTest {
	public static void main(String[] args) {
	
		File f1 = new File("e:/D_Other/sample.txt");
		File f2 = new File("e:/D_Other/test.txt");
		
		if(f1.exists()) {
			System.out.println(f1.getAbsolutePath() + "은 존재합니다.");
		} else {
			System.out.println(f1.getAbsolutePath() + "은 존재하지 않습니다.");
		}
		
		try {
			if(f1.createNewFile()) {
				System.out.println(f1.getAbsolutePath() + "파일을 새로 만들었습니다.");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		
		if(f2.exists()) {
			System.out.println(f2.getAbsolutePath() + "은 존재합니다.");
		} else {
			System.out.println(f2.getAbsolutePath() + "은 존재하지 않습니다.");
			
		}
		System.out.println("-----------------------------------------");
		
		File f3 = new File("e:/D_Other");
		File[] files = f3.listFiles(); // 폴더에 있는 파일을 정렬할테얌 = listFiles
		for(File f : files) {
			System.out.print(f.getName() + " => " );
			if(f.isFile()) {
				System.out.println("파일");
			} else if(f.isDirectory()) {
				System.out.println("디렉토리(폴더)");
			}
		}
		System.out.println("========================================");
		String[] strFiles = f3.list();
		for (String str : strFiles) {
			System.out.println(str);
		}
		System.out.println("-----------------------------------------");
		
		//출력할 디렉토리 정보를 갖는 파일객체
		File f4 = new File("e:/D_Other");
		
		displayFileList(f4);
			
	}
	/**
	 * 지정된 디렉토리(폴더)에 포함된 파일과 디렉토리 목록을 보여주는 메서드
	 * @param f4 파일과 디렉토리 목록을 보고싶은 디렉토리(폴더)
	 */
	private static void displayFileList(File dir) {
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리 내용");
		
		// 디렉토리의 안의 모든 파일 목록을 가져온다.
		File[] files = dir.listFiles();
		
		//하위 디렉토리 정보를 저장할 ArrayList 생성(File객체 인덱스값 저장용)
		List<Integer> subDirList = new ArrayList<Integer>();
		
		//날짜를 출력하기 위한 형식 설정
		SimpleDateFormat sdf = 
				new SimpleDateFormat("yyyy-MM-dd a hh:mm"); // a = 오전,오후
		
		for (int i = 0; i < files.length; i++) {
			String attr = ""; // 파일속성정보(읽기, 쓰기, 숨김, 디렉토리구분)
			String size =""; //파일크기
			
			if(files[i].isDirectory()) {
				attr = "<DIR>";
				subDirList.add(i); //인덱스 정보 저장
			} else {
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : " "; // 3항 연산자 / true면 앞 / false이면 뒤
				attr += files[i].canWrite() ? "W" : " "; // 누적
				attr += files[i].isHidden() ? "H" : " "; // 누적
			}
			
			System.out.printf("%s %-5s %12s %s\n",
			sdf.format(new Date(files[i].lastModified())),
			attr, size, files[i].getName());
		}
		int dirCount = subDirList.size(); // 폴더 개수
		int fileCount = files.length - dirCount; // 파일 개수
		
		System.out.println(fileCount + "개의 파일, " + dirCount + "개의 디렉토리(폴더)");
		System.out.println();
		
		for (Integer i : subDirList) { // 재귀호출
			displayFileList(files[i]);
		}
	}
}
