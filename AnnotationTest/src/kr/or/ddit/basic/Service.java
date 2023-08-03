package kr.or.ddit.basic;
// 어노테이션 = 주석 
public class Service {

	// 붙여놔도 주석처럼 별 문제 없음. 정보를 제공하기 위해 붙임
	@PrintAnnotation(count = 10, value = "#")
	public void method1() {
		System.out.println("메서드 1에서 호출되었습니다.");	
	}
	
	@PrintAnnotation(count = 30, value = "$")
	public void method2() {
		System.out.println("메서드 2에서 호출되었습니다.");	
	}
	
	@PrintAnnotation(value = "%") // ("%")만 써도 됨
	public void method3() { // 기본값 : - / PrintAnnotaion에서 기본 값 - 으로 설정해줬음 
		System.out.println("메서드 3에서 호출되었습니다.");	
	}
}
