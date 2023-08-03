package kr.or.ddit.reflection;

/**
 * (클래스 정보를 담고 있는) Class 오브젝트를 생성하기
 */

public class T01ClassObjectCreationTest {
/*
 		Java Reflection에 대하여...
 	
 	1. 자바리플렉션은 런타입 시점에 클래스 또는 멤버변수, 메서드, 생성자등에 대한
 	     정보를 가져오거나 수정할 수 있고, 새로운 객체를 생성하거나 메서드를 실행 할 수 있다.
 	   (컴파일 시점에 해당 정보를 알 수 없는 경우(소스코드 부재)에 유용하게 사용할 수 있다.)
 	2. Reflection API는 java.lang.reflect 패키지와 java.lang.Class를 통해 제공됨.
 	3. java.lang.Class의 주요메서드
 		-getName(), getSuperclass(), getInterfaces(), getModifiers() 등.
 	4. java.lang.reflect 패키지의 주요 클래스
 		-Field, Method, Constructor, Modifier 등.	
 */
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		// 첫 번째 방법 : Class.forName() 메서드 사용
		Class<?> klass = Class.forName("kr.or.ddit.reflection.SampleVO");
		
		// 두 번째 방법 : getClass() 메서드 이용 
		SampleVO sampleVO = new SampleVO();
		klass = sampleVO.getClass();
		
		// 세 번째 방법 : .class 이용하기
		klass = SampleVO.class;
		
		
		
		
		
	}
}
