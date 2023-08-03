package kr.or.ddit.basic;
// 08.03
// @FunctionalInterface를 붙이면 추상메서드가 한 개만 갖을 수 있게 오류를 잡아줌

@FunctionalInterface 
public interface LambdaTestInterface1 {
	//반환 값이 없고, 매개변수도 없는 추상메서드 선언
	public void test();
}
@FunctionalInterface
interface LambdaTestInterface2 {
	// 반환값이 없고 매개변수는 있는 추상메서드 선언
	public void test(int a);
	
}
@FunctionalInterface
interface LambdaTestInterface3 {
	// 반환값도 있고  매개변수도 있는 추상메서드 선언
	public int test(int a, int b);
	
	
}