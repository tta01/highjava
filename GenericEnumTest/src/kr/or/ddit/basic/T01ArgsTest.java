package kr.or.ddit.basic;

public class T01ArgsTest {
/* 
 	가변형 인수 => 메서드의 매개변수의 개수가 실행될 때 마다 다를 때 사용한다.
 	 - 가변형 인수는 메서드 안에서는 배열로 처리된다.
 	 - 가변형 인수는 한 가지 자료형만 사용할 수 있다/
	 
*/
	/**
	 *  배열을 이용한 메서드
	 */
	public int sumArr(int[] data) {
		
		int sum = 0;
		
		for(int i=0; i < data.length; i++) {
			sum += data[i];
		}		
		
		return sum;
	}
	/**
	 * 가변형 인수를 이용한 메서드
	 */
	public int sumArgs(int... data) { // 들어올 데이터 값이 정해지지 않았을 때, 넣어줄 갯수 상관 없이 넣어줄 수 있음
		
		int sum = 0;
		
		for(int i=0; i < data.length; i++) {
			sum += data[i];
		}		
		
		return sum;
	}
	
	/**
	 * 가변형 인수(...)와 일반적인 인수를 같이 사용할 경우에는 가변형 인수를 제일 뒤쪽에 배치해야 한다.
	 */
	
	public String sumArgs2(String name, int...data) { // 가변형 인수... 맨 뒤에 배치
		
		int sum = 0;
		
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		
		return name + "씨 점수 : " + sum;
	}
	
	public static void main(String[] args) {
		
		T01ArgsTest arg = new T01ArgsTest();
		
		int[] nums = {100,200,300};
		System.out.println(arg.sumArr(nums));
		System.out.println(arg.sumArr(new int[] {1,2,3,4,5}));
		System.out.println();
		
		System.out.println(arg.sumArgs(100,200,300));
		System.out.println(arg.sumArgs(1,2,3,4,5));
		System.out.println();		
		
		System.out.println(arg.sumArgs2("홍길동",1,2,3,4,5,6,7,8));
		
	}
}
