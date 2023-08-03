package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T11DisplayCharacterTest {
/*
	 3개(명)의 스레드가 각각 알파벳 대문자를 출력하는데 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성
*/
	static int CURR_RANK = 1; // 현재 순위 정보 / 상수에는 final이 같이 붙어야 상수!! 얘는 상수 아님
	
	public static void main(String[] args) {
		
		List<DisplayCharacter> disCharList = new ArrayList<DisplayCharacter>();
		
		// 여기 () 값을 빼면 생성자 메서드의 String name도 빼줘야 하고, super안에 name를 빼주면 됨
		disCharList.add(new DisplayCharacter("홍길동"));
		disCharList.add(new DisplayCharacter("변학도"));
		disCharList.add(new DisplayCharacter("성춘향"));
		
		for(Thread th : disCharList) {
			th.start();
		}
		
		for(Thread th : disCharList) {
			try {
				th.join(); // 잠시 멈춤
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Collections.sort(disCharList); //정렬하기
		
		System.out.println("* 경기 끝 *");
		System.out.println("------------------------");
		System.out.println("경기결과");
		System.out.println();
		System.out.println("========================");
		System.out.println("순위\t:\t이름");
		System.out.println("========================");
		for(DisplayCharacter dc : disCharList) {
			System.out.println(dc.getRank() + " \t:\t" + dc.getName());
		}
		System.out.println("------------------------");
	}
}

// 영어 대문자를 출력하는 스레드
class DisplayCharacter extends Thread implements Comparable<DisplayCharacter>{
	private String name;
	
	private int rank;
	
	public DisplayCharacter(String name) {
		super(name); // String 값을 넣어주는데 그럼 스레드 이름을 부여할 수 있음
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public void run() {
		
		for(char ch='A'; ch<='Z'; ch++) {
			System.out.println(name + "의 출력문자 : " + ch);
		
			try {
				// 200 ~ 500 사이의 시간만큼 일시정지
				Thread.sleep((int)(Math.random() * 301 + 200)); // math.random = 0 => 0*301 = 0 + 200 = 200-> 최소값 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(name + "출력 끝!");
		
		setRank(T11DisplayCharacterTest.CURR_RANK++);
		
	}

	@Override
	public int compareTo(DisplayCharacter dc) {
		
		return new Integer(this.getRank()).compareTo(dc.getRank());
	}
	

}



