package kr.or.ddit.basic;

public class MySingleton {
/*
 	싱글턴패턴 이란?
 	 => 객체(인스턴스)를 한 개만 만들어지게 하는 프로그래밍 방법
 	
 	▶   싱글턴 클래스를 구성하는 방법
 	1. 자기자신 class 참조변수 멤버변수로 선언한다.
  	  (이 변수는 private static으로 선언한다.)
  	2. 생성자를 private으로 한다.
  	  (외부에서 생성자에 접근하지 못하게 하기 위해서! 즉, 외부에서 new명령을 사용할 수 없게 하기 위함)
  	3. 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 메서드를 만든다.
  	  (이 메서드는 static으로 선언하고, 이름은 보통 getInstance()로 지정한다.)
  	 * 겟인스턴스는 객체를 생성해야 만들수 있는데, static은 객체를 생성하지 않아도 쓸 수 있기 때문에 static으로 선언함
  	  
 */
	// 자기 자신의 class 참조값을 저장하는 멤버변수 선언
	private static MySingleton single;
	
	private MySingleton() {
		System.out.println("생성자 입니다.");
	}
	
	public static MySingleton getInstance() {
		if(single == null) { // 없으면 하나 생성하고
			single = new MySingleton();
		}
		
		return single; // 있으면 받아서 쓰고
	}
	
	public void displayText() {
		System.out.println("안녕하세요. 싱글턴 객체입니다.");
	}
}
