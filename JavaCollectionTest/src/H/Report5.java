package H;
/*
10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.(Comparable 인터페이스 구현) 

말 10개? 만들고 / 구간 -- 이거 50개?
출력하는 화면 구현..? 말들이 정렬되서 순서대로 나오게

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 >로 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수를 기준으로 정렬하여 출력한다.
 */

import java.util.ArrayList;
import java.util.List;

public class Report5 {

	static int CURR_RANK = 1;

	static List<Horse> hlist = new ArrayList<Horse>();

	public static void main(String[] args) {
		
		// 객체를 생성해서 리스트에 담았어
		for (int i = 1; i <= 10; i++) {
			hlist.add(new Horse(i + "번말"));
		}
		
		for (Horse a : hlist) {
			a.start();
		}
	}
}

class Horse extends Thread implements Comparable<Horse> {
	// private String name;
	private int rank;

	public Horse(String name) {
		super(name);
		// this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Horse [name=" + getName() + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Horse h) {
		return new Integer(this.getRank()).compareTo(h.getRank());
	}

	@Override
	public void run() { // 구간만들기
		String rail = "--------------------------------------------------";
		char[] rail2 = rail.toCharArray();

		for (int i = 0; i < rail2.length; i++) {
			if (i == 0) {
				rail2[i] = '>';
			} else {
				rail2[i] = '>';
				rail2[i - 1] = '-';
			}
			try {
				Thread.sleep((int)(Math.random()*201)+100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			rail = new String(rail2);
			System.out.println(this.getName() + " : " +  rail);
		}
		
		setRank(Report5.CURR_RANK++);
	}
}
