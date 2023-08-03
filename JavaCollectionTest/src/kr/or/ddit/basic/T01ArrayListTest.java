package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T01ArrayListTest {
	public static void main(String[] args) {
		
		//Default Capacity = 10
		List list1 = new ArrayList();
		
		// add() 메서드를 이용해 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);
		list1.add('k'); 
		list1.add(true); 
		list1.add(12.34); // f를 붙이지 않아서 double타입 / 12.34f(F)
		
		// size() => 데이터의 개수
		System.out.println("size => " + list1.size());
		System.out.println("list1 => " + list1);
		
		//get()으로 데이터 꺼내기
		System.out.println("1번째 자료 : " + list1.get(0)); // 매개변수 필요 0(1)번째부터 꺼내겠다는 의미
		
		//데이터 끼워넣기도 같다.
		list1.add(0,"zzz");
		System.out.println("list1 => " + list1);
		
		//데이터 변경하기
		String temp = (String) list1.set(0, "YYY"); // 바꾸고 싶은 인덱스 값(0)을 YYY로 바꾸겠다는 의미. 그걸 temp에 저장!
		System.out.println("temp : " + temp);
		System.out.println("list1 = > " + list1);
		
		//데이터 삭제하기
		list1.remove(0); // 0번째 값을 지우고 
		System.out.println("삭제후 : " + list1);
		
		list1.remove("bbb"); // 들어있는 데이터 값은 알지만 인덱스 값을 모를 때 사용 함! bbb는 들어 있는 데이터 값을 의미
		System.out.println("bbb 삭제후 : " + list1);
		System.out.println("======================");
		
		// 111을 삭제 하는 방법
		
//		list1.remove(111); 이렇게는 삭제 할 수 없음!
//		System.out.println("111 삭제 후 : " + list1);
		
//		list1.remove(1);
//		System.out.println("111 삭제 후 : " + list1);
		
		list1.remove(new Integer(111));
		System.out.println("111 삭제 후 : " + list1);
		
		System.out.println("------------------------");
		//제너릭을 지정하여 선언할 수 있다.
		List<String> list2 = new ArrayList<String>(); // list에 String값을 넣어줘서 String만 넣을 것이라는 의미
		list2.add("AAA"); // 만약 add 안에 String값이 아닐 경우 오류남!
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		
		for(String str : list2) { // 향상된 for문
			System.out.println("str값 : " + str);
		}
		
		System.out.println("---------------------");
		
		// contains(비교객체) => 리스트에'비교객체'가 있으면 true 그렇지 않으면 false
		
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));
		
		//indexOf(비교객체) => 리스트에서 '비교객체'를 찾아 '비교객체'가 있는 index 값을 반환함.
		//					 없으면 '-1'을 반환함.
		System.out.println("DDD의 index값 : " + list2.indexOf("DDD"));
		System.out.println("zzz의 index값 : " + list2.indexOf("ZZZ"));
		System.out.println("--------------------------------------");

		// 리스트 삭제 처리
//		for(int i=0; i<list2.size(); i++) {
//			list2.remove(i); // for문을 돌면서 A부터 하나씩 지워지는데 A가 지워지면 B부터 A자리로 들어옴 
			// 두 번째 지워지는건 B가 아니라 C가 지워짐 / 거꾸로 지우면(-1) 인덱스가 다 지워짐!!
			
		for(int i=list2.size()-1; i>=0; i--) { // 뒤에서부터 지워야 하기 때문에 i의 기준이 배열의 길이가 되어야 함 / 0이 될 때까지 지워야 하니까 i가 0과 같거나 작을 때까지 돌리는 것임! / 뒤에서 부터니까 5 4 3 2 1  하나씩 빼줘야해서 i--
			list2.remove(i); // 결국 i는 0이됨
		}

		System.out.println("삭제 후 리스트2의 크기 : " + list2.size()); 
	}		
}
