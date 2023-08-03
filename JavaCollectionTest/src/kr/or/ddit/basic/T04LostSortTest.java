package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04LostSortTest {

	public static void main(String[] args) {
		
		List<Member> memList = new ArrayList<Member>(); // 멤버 리스트 목록 생성. 번호는 랜덤으로 막 집어 넣음
		memList.add(new Member(1,"홍길동","010-1111-1111"));
		memList.add(new Member(5,"변학도","010-2222-1111"));
		memList.add(new Member(9,"성춘향","010-3333-1111"));
		memList.add(new Member(3,"이몽룡","010-4444-1111"));
		memList.add(new Member(6,"일지매","010-5555-1111"));
		memList.add(new Member(2,"홍길순","010-6666-1111"));
		
		System.out.println("정렬 전 : "); // 작성된 순서대로 출력
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		System.out.println("--======================--");
		Collections.sort(memList);
		
		System.out.println("정렬 후 : "); // 이름 기준으로 정렬함
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		Collections.shuffle(memList); // 섞기
		System.out.println("섞은 후 : "); 
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		Collections.sort(memList,new SortNumDesc());
		System.out.println("번호 내림차순 정렬 후 : "); 
		for(Member mem : memList) {
			System.out.println(mem);
		}
	}

}
// 회원이름을 기준으로 오름차순 정렬되도록 할 수 있는 클래스 / 기준점을 두고 정렬할 때 - comparable / compareTo
class Member implements Comparable<Member> { // 사용자 번호, 이름, 전화번호를 담기 위해 클래스를 생성함
	// 멤버 오버라이드 해줘야 함!
	private int num;
	private String name;
	private String tel;
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

// 이름이 String이기 때문에 멤버 타입 수정할 필요 없이 그대로 두면 됨
// 내 이름과 다른 이름을 비교 한다는 의미 / 정렬할거니까
	@Override
	public int compareTo(Member mem) {
		
		return this.getName().compareTo(mem.getName());
	}
}

// 회원번호를 이용한 내림차순 정렬을 위한 외부 정렬자 
class SortNumDesc implements Comparator<Member> {

	@Override
	public int compare(Member mem1, Member mem2) {
		
//		if(mem1.getNum() > mem2.getNum()) {
//			return -1;
//		} else if(mem1.getNum() == mem2.getNum()) {
//			return 0;
//		} else {
//			return 1;
//		}
		
		//Wrapper클래스를 이용한 방법
		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1; //오름차순
	}
	
}

	
	