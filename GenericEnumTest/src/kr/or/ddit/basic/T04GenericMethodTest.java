package kr.or.ddit.basic;

class Util {

	/*
	 * 제너릭 메서드란? 파라미터타입과 리턴타입으로 제너릭 타입글자를 가지는 메서드 선언 방법 : 리턴타입 앞에 <>기호를 추가하고 타입
	 * 제너릭타입 글자를 기술한 후 사용함 ex_) <T, R> R method(T t)
	 */

	public static <K, V> boolean
		compare(Pair<K, V> p1, Pair<K, V> p2) {

		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());

		return keyCompare && valueCompare;

	}
}

public class T04GenericMethodTest {
	
	public static void main(String[] args) {
		
		Pair<Integer, String> p1 = new Pair<Integer, String>(1,"홍길동");
		Pair<Integer, String> p2 = new Pair<Integer, String>(1,"홍길동");
		
		boolean result1 = Util.<Integer, String>compare(p1,p2);
		
		if(result1) {
			System.out.println("논리(의미)적으로 동일한 객체임");
		}else { 
			System.out.println("논리(의미)적으로 동일한 객체가 아님");
		}
		
		Pair<String, String> p3 = new Pair<String, String>("001","홍길동");
		Pair<String, String> p4 = new Pair<String, String>("002","홍길동");
		
		//구체적 타입 생략 가능 => Util.<String, String>compare(p3, p4); 원래<>타입까지 작성해야 함
		boolean result2 = Util.compare(p3, p4);
		
		if(result2) {
			System.out.println("논리(의미)적으로 동일한 객체임");
		} else { 
			System.out.println("논리(의미)적으로 동일한 객체가 아님");
		} 		
		
		p1.<String, Integer>printKeyValue("키",180); // 일반메서드, 제너릭타입? / <String, Integer>가 생략될 수 있음
		
	}
}

class Pair<K, V> { // 제너릭 클래스. 마음대로 써도 됨..?

	private K key;
	private V value;

	public Pair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
	public <K, V> void printKeyValue(K key, V val) {
		System.out.println(key + " : " + val);		
	}
}
