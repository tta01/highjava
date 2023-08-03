package H;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Report1 {

	public static void main(String[] args) {

		List<Student> stdList = new ArrayList<Student>();

		// add로 자료 입력!!!! 생각하기!!
		stdList.add(new Student("230701", "짱구", 60, 75, 90));
		stdList.add(new Student("230702", "유리", 80, 83, 90));
		stdList.add(new Student("230703", "훈이", 80, 64, 40));
		stdList.add(new Student("230704", "철수", 100, 95, 90));
		stdList.add(new Student("230705", "맹구", 81, 60, 90));
		stdList.add(new Student("230706", "미선", 85, 78, 90));
		
		
		new Report1().setRanking(stdList);
		
		
		System.out.println("정렬 전 섞기 : "); // 학번을 순서대로 써놔서 섞음!
		Collections.shuffle(stdList);
		for (Student std : stdList) {
			System.out.println(std);
		}
		System.out.println();

		Collections.sort(stdList);
		System.out.println("학번 오름차순 정렬 : ");
		for (Student std : stdList) {
			System.out.println(std);
		}
		System.out.println();

		Collections.sort(stdList, new SortSumDesc());
		System.out.println("총점 역순(내림차순) 정렬 : ");
		for (Student std : stdList) {
			System.out.println(std);
		}
	}

	public void setRanking(List<Student> stdList) {
		for (Student std : stdList) { // 등수를 구할 자료(기준 자료)
			int rank = 1;
			for (Student std2 : stdList) { // 비교할 자료
				if (std.getSum() < std2.getSum()) {
					rank++;
				}
			}
			std.setRank(rank);
		}
	}
}

class Student implements Comparable<Student> { // 정렬이 가능하게 comparable을 사용

	private String stdNum;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;

	public Student(String stdNum, String name, int kor, int eng, int math) {
		super();
		this.stdNum = stdNum;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;

		sum = this.kor + this.eng + this.math;

	}

	public String getStdNum() {
		return stdNum;
	}

	public void setStdNum(String stdNum) {
		this.stdNum = stdNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;

	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	// 학번 기준으로 오름차순 정렬의 오버라이드
	@Override
	public int compareTo(Student std) {
		return this.getStdNum().compareTo(std.getStdNum());
	}

	@Override
	public String toString() {
		return "Student [stdNum=" + stdNum + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", sum=" + sum + ", rank=" + rank + "]";
	}

}

// 총점의 역순 내림차순 정렬의 오버라이드 / 외부정렬자 따로 생성
class SortSumDesc implements Comparator<Student> {// 하나를 두고 비교해야 하니까 comparator

	@Override
	public int compare(Student std1, Student std2) {
		
		int sum = new Integer(std1.getSum()).compareTo(std2.getSum()) * -1;

		if (sum != 0) {
			return sum;
		} else {
			return std1.getStdNum().compareTo(std2.getStdNum());
		}
	}
}
