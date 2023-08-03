package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class T03LambdaTest {
	public static void main(String[] args) {
		
		List<String> strList = new ArrayList<String>();
		
		strList.add("나");
		strList.add("내왼쪽");
		strList.add("내오른쪽");
		
		for(String str : strList) {
			System.out.println(str);
		}
		
		///////////////////////////////////////////
		System.out.println("------------------------------------------");
		strList.forEach(name -> System.out.println(name));
		
//		strList.forEach(new Consumer<String>() {
//			
//			@Override
//			private void accept(String t) {
//				System.out.println(strList);
//
//			}
//		});
		
		//로또번호 출력
		System.out.println("============================================");
		new Random().ints(1, 46).distinct().limit(6).sorted().forEach(num -> System.out.print(num + " "));
		// 랜덤 객를 생성하고, 인스스트림. 중복방지 . 6개까지만 출력. 정렬. forEach로 하나씩 출력해줌 
		
	}

}
