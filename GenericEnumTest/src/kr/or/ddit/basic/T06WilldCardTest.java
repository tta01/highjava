package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T06WilldCardTest { //제너릭 & 와일드카드 = 짝꿍
	/*
	  	와일드카드에 대하여...
	  
	  	와일드 카드(?)는 제너릭 타입을 이용한 타입 안전한 코드를 위해 사용되는 특별한 종류의 인수(argument)로서
	  	변수선언, 객체생성 및 메서드 정의할 때 사용된다. (타입을 알 수 없을 때)
	  	
	  	<? extends T> => 와일드 카드의 상한 제한. T와 그 자손들만 가능
	  	<? super T> => 와일드 카드의 하한 제한. T와 그 조상들만 가능
 		<?> => 허용 가능한 모든 타입 가능.
 		
	 */

	public static void main(String[] args) {
		
		List<?> List = new ArrayList<>(); // list가 제너릭 인터페이스이기 때문에 와일드카드<?> 사용가능

		FruitBox<Fruit> fruitBox = new FruitBox<Fruit>(); // jdk8 부터 new뒤에 <> 안 생략해도 됨. 같은 값
		FruitBox<Apple> appleBox = new FruitBox<>();

		fruitBox.add(new Apple());
		fruitBox.add(new Grape());

		System.out.println(fruitBox.getFruitList());

		appleBox.add(new Apple());
		appleBox.add(new Apple());

		System.out.println(appleBox.getFruitList());

		Juicer.makeJuiece(fruitBox);

		Juicer.makeJuiece(appleBox);

	}
}

class Juicer {

	// static void makeJuice(FruitBox<Fruit> box> {
	//static <T extends Fruit> void makeJuiece(FruitBox<T> box) {
		// 제너릭 타입으로 변경해줌. & extends를 사용해 타입제한을 걸어줌
	//static void makeJuiece(FruitBox<? extends Fruit> box) {
		// 일반 메서드. FruitBox가 제너릭 타입이라 <? extends ~> 이렇게 쓸 수 있음
	
	static void makeJuiece(FruitBox<?> box) {


		String fruitListStr = ""; // 과일목록

		int cnt = 0;
//		for (T f : box.getFruitList()) { // 위에서 제너릭으로 변경했으니 여기 타입도 같게(T)로 변경
		for (Object f : box.getFruitList()) {
			if (cnt == 0) {
				fruitListStr += f;
			} else {
				fruitListStr += ", " + f;
			}

			cnt++;
		}
		System.out.println(fruitListStr + " => 쥬스 완성!!! ");

	}
}

class Fruit {
	private String name;

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일 (" + name + ")";
	}
}


class Apple extends Fruit {

	public Apple() {
		super("사과");
	}
}


class Grape extends Fruit {

	public Grape() {
		super("포도");
	}
}


class FruitBox<T extends Fruit> { // 타입 제한을 걸어줌 과일박스에 과일만 올 수 있음

	private List<T> FruitList;

	public FruitBox() {
		FruitList = new ArrayList<T>();
	}

	// 과일 담기
	public void add(T fruit) {
		FruitList.add(fruit);
	}

	public List<T> getFruitList() {
		return FruitList;
	}
}
