package kr.or.ddit.basic;

class NonGenericClass {
	private Object val;

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}
}

class MyGeneric<T> { // 현재는 무슨 타입인지 모르는 상태
	private T val;

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
}

public class T03GenericClassTest {
	
	/*
	 제너릭 = 일반적인
	 제너릭 클래스를 만드는 방법
	 	
	 형식 )
	 	class 클래스명<제너릭 타입 글자> { 
	 	
	 	제너릭타입글자 변수명;  // 변수선언에 제너릭을 사용할 경우
	 	...
	 	제너릭타입글자 메서드명() { // 반환값이 있는 메서드에서 사용하는 경우
	 	...
	 	
	 	return값;
	 	}
	 	
	 	...
	 }
	 
	 -- 제너릭 타입 글자 -- ( 아무거나 올 수 있음 )
	 T => Type
	 K => Key
	 V = Value
	 E => Element(요소,원소)
	 
	 */
	
	public static void main(String[] args) {
		
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		
		// 꺼낼 때(get)는 타입 꼭 써줘야 함
		String rtnVal1 = (String)ng1.getVal(); // 타입 스트링을 지우면 오브젝트 타입이 됨으로 꼭 스트링이라고 써 줘야 함
		System.out.println("문자열 반환 값 rtnVal1 => " + rtnVal1);

		Integer rtnVal2 = (Integer) ng2.getVal();
		System.out.println("정수형 반환 값 rtnVal2 => " + rtnVal2);
		System.out.println();
		
		MyGeneric<String> mg1 = new MyGeneric<String>();
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		
		mg1.setVal("우리나라");
		mg2.setVal(500);
		
		rtnVal1 = mg1.getVal();
		rtnVal2 = mg2.getVal();
		
		System.out.println("제너릭 문자열 반환값 : " + rtnVal1);
		System.out.println("제너릭 정수형 반환값 : " + rtnVal2);
		
	}
}
