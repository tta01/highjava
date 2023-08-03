package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T03ListSortTest {

	public static void main(String[] args) {
	
		List<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");

		System.out.println("정렬 전 : " + list); // * 기본 작성되어 있는대로 정렬하고 / [일지매, 홍길동, 성춘향, 변학도, 이순신]

		
		// 정렬은 Collerctions.sort() 메서드를 이용하여 정렬한다. / Collections = 클래스!
		// 정렬은 기본적으로 '오름차순 정렬'을 수행한다.
		Collections.sort(list); // 정적 메소드, 클래스 메소드
		
		System.out.println("정렬 후 : " + list); // * 기본 오름차순으로 정렬하고 / [변학도, 성춘향, 이순신, 일지매, 홍길동]

		
		Collections.shuffle(list);
		System.out.println("섞기 후 : " + list);// * 한 번 섞어준 후 / [일지매, 변학도, 이순신, 성춘향, 홍길동]

		
		// 정렬방식을 결정하는 외부정렬자를 이용하여 정렬하기-
		
		
//		Desc desc = new Desc(); 이렇게 만들면 아래에 desc를 넣으면 되고 아니면 
		Collections.sort(list,new Desc()); // new Desc()로 바로 넣으면 됨
		System.out.println("외부정렬자를 이용한 정렬 후 : " + list); // * 외부정렬자를 이용해 정렬 / [홍길동, 일지매, 이순신, 성춘향, 변학도]

	}

}

// 정렬 방식을 결정하는 정렬자 클래스
class Desc implements Comparator<String> {
/*
 	- 오름차순 정렬일 경우
	=> 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수를 반환
*/
	
// 두 개를 비교해 주는 메소드 그래서 아래 값이 두 개
	@Override
	public int compare(String str1, String str2) {
		
		return str1.compareTo(str2) * -1;
	}
	
}