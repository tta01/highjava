package kr.or.ddit.basic;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class T02LambdaTest {
	public static void main(String[] args) {

		// 람다식을 사용하지 않았을 경우
		LambdaTestInterface1 lam1 = new LambdaTestInterface1() {

			@Override
			public void test() {
				System.out.println("안녕하세요");
				System.out.println("익명 구형 객체 방식입니다.");
			}
		};
		lam1.test(); // 메서드 호출

		LambdaTestInterface1 lam2 = () -> {
			System.out.println("반갑습니다.\n람다식으로 처리하는 방식입니다.");
		};
		lam2.test();

		System.out.println("------------------------------------");

		////////////////////////////////////////////////////////////////

		/*
		   	람다식 작성 방법
		   	객체 생성시 new 필요 없음 대신, () -> 로 표현하고 {}; 안에 작성해 줘야 하는건 똑같음
		  
		  	기본형식) (자료형이름 매개변수명, ...) -> {실행문들;...}
		  
			  1) 매개변수의 '자료형이름'은 생략할 수 있다.
			  	ex_) (int a) -> {System.out.println(a);}
			  	(a) -> {System.out.println(a);} 				! int를 빼고 써도 된다는 의미 !
		  
		  	2) 매개변수가 1개일 경우에는 괄호'()'를 생략할 수 있다.
		  		(단, '자료형이름'을 지정할 경우에는 괄호를 생략할 수 없다.) 		!int a 이렇게는 못 쓴다는 의미 !
		  		ex_) a -> {System.out.println(a);} 				1개!!!! 일 경우에만
		  
		  	3) '실행문'이 1개일 경우에는 '{}'를 생략할 수 있다.
		  	(이 때 문장의 끝을 나타내는 세미콜론도 생략한다.)
		  	ex_) a -> System.out.println(a) 					1개!! 일 경우에만
		  
		  	4) 매개변수가 하나도 없으면 괄호'()'를 생략할 수 없다.
		  		ex_) () -> System.out.println("안녕") 			!매개변수가 없다는 의미로 () 꼭 써야해 !
		  
		  	5) 반환값이 있을 경우에는 return문을 사용한다.
		  		ex_) (a, b) -> {return a + b;}
		  		(a, b) -> return a + b 							3번에 의해 {} 생략됨!
		  
		  	6) 실행문에 return문만 있을 경우에 return명령과 '{}'를 생략할 수 있다
		  		ex_) (a,b) -> a + b
		 */
/*--------------------------------------------------------------------------------------------		
		LambdaTestInterface2 lam3 =
				(int z) -> {
			int result = z + 100;
			System.out.println("result = " + result);
		};
		
		LambdaTestInterface2 lam4 =
				z -> {
			int result = z + 300;
			System.out.println("result = " + result);
		};
		lam4.test(60);

		LambdaTestInterface2 lam5 =
				z -> System.out.println("result = " + (z + 500));
		lam5.test(90);

		System.out.println("-----------------------------");

		LambdaTestInterface3 lam6 =
				(int x, int y) -> {
			int r = x + y;
			return r;
		};
		int k = lam6.test(20, 50);
		System.out.println("k = " + k); 

		LambdaTestInterface3 lam7 =
				(x, y) -> {
			return x + y;
		};
		k = lam7.test(80, 50);
		System.out.println("k = " + k);

		LambdaTestInterface3 lam8 =
				(x, y) -> x + y;
		k = lam8.test(100, 200);

		LambdaTestInterface3 lam9 =
				(x, y) -> x > y ? x : y;
		k = lam9.test(100, 200);
		System.out.println("k = " + k); 
		
---------------------------------------------------------------------------------------------------*/
		// 컨슈머 = 추상메소드가 1개인 인터페이스?
		Consumer<Integer> lam3 =
				(Integer z) -> {
					int result = z + 100;
					System.out.println("result = " + result);
				};
		lam3.accept(30);
		
		Consumer<Integer> lam4 =
				z -> {
			int result = z + 300;
			System.out.println("result = " + result);
		};
		lam4.accept(60);

		Consumer<Integer> lam5 =
				z -> System.out.println("result = " + (z + 500));
		lam5.accept(90);

		System.out.println("-----------------------------");
		
		BiFunction<Integer, Integer, Integer> lam6 =
				(Integer x, Integer y) -> {
			int r = x + y;
			return r;
		};
		int k = lam6.apply(20, 50);
		System.out.println("k = " + k); 

		BiFunction<Integer, Integer, Integer> lam7 =
				(x, y) -> {
			return x + y;
		};
		k = lam7.apply(80, 50);
		System.out.println("k = " + k);

		BiFunction<Integer, Integer, Integer> lam8 =
				(x, y) -> x + y;
		k = lam8.apply(100, 200);

		BiFunction<Integer, Integer, Integer>lam9 =
				(x, y) -> x > y ? x : y;
		k = lam9.apply(100, 200);
		System.out.println("k = " + k); 

	}
}