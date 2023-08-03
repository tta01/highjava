package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T07WildCardTest {

	// 장바구니 항목조회를 위한 메서드(모든 타입의 장바구니 허용)
	public static void displayCartItemInfo(Cart<?> cart) {
		System.out.println(" = 음식류 장바구니 항목 리스트 : ");
		for (Object obj : cart.getList()) {
			System.out.println(obj); // obj로 써 넣어주면 뒤에 (toString) 생략된 상태.
		}
		System.out.println("----------------------------");
	}

	// 장바구니 항목조회를 위한 메서드(음료나 그 하위(extends) 타입의 장바구니 허용)
	public static void displayCartItemInfo2(Cart<? extends Drink> cart) {
		System.out.println(" = 음료나 그 하위 타입의 장바구니 항목 리스트 : ");
		for (Object obj : cart.getList()) {
			System.out.println(obj);
		}
		System.out.println("----------------------------");
	}
	
	// 장바구니 항목조회를 위한 메서드(고기나 그 상위(super) 타입의 장바구니 허용)
		public static void displayCartItemInfo3(Cart<? super Meat> cart) { 
			System.out.println(" = 고기나 그 상위 타입의 장바구니 항목 리스트 : ");
			for (Object obj : cart.getList()) {
				System.out.println(obj);
			}
			System.out.println("----------------------------");
		}
		
	public static void main(String[] args) { // 스태틱의 반대 인스턴스 -> 인스턴스는 객체를 생성해줘야 사용 가능 / 스태틱은 객체를 생성할 필요가 없음
			
		Cart<Food> foodCart = new Cart<>();
		foodCart.add(new Meat("돼지고기", 5000));
		foodCart.add(new Meat("소고기", 50000));
		foodCart.add(new Juice("오렌지쥬스", 2000));
		foodCart.add(new Coffee("마끼아또", 4000));
		
		Cart<Meat> meatCart = new Cart<>();
		meatCart.add(new Meat("돼지고기", 5000));
		meatCart.add(new Meat("소고기", 50000));
		
		Cart<Drink> drinkCart = new Cart<>();
		drinkCart.add(new Juice("오렌지쥬스", 2000));
		drinkCart.add(new Coffee("마끼아또", 4000));
		
		// 타입 제한 없음
		displayCartItemInfo(foodCart);
		displayCartItemInfo(meatCart);
		displayCartItemInfo(drinkCart);
		
		// extends. 드링크의 하위만 올 수 있음. 드링크와 주스,커피
		//displayCartItemInfo2(foodCart);
		//displayCartItemInfo2(meatCart);
		displayCartItemInfo2(drinkCart);
		
		// super. 미트의 상위만 올 수 있음
		displayCartItemInfo3(foodCart);
		displayCartItemInfo3(meatCart);
		//displayCartItemInfo3(drinkCart);

		// 제너릭 = 클래스를 선언할 때, 메소드는 선언할 때.
		// 와일드 카드 => 제너릭 타입 중에서 알 수 없는 다양한 유형의 타입을쓸 수 있게 하려고..?
		
	}
}

class Food {
	private String name; // 음식 이름
	private int price; // 음식 가격

	public Food(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return this.name + "(" + this.price + "원)";
	}
}

class Meat extends Food {

	public Meat(String name, int price) {
		super(name, price);
	}
}

class Drink extends Food {

	public Drink(String name, int price) {
		super(name, price);
	}
}

class Juice extends Drink {

	public Juice(String name, int price) {
		super(name, price);
	}
}

class Coffee extends Drink {

	public Coffee(String name, int price) {
		super(name, price);
	}
}

/**
 * 장바구니
 * 
 * @param <T>
 */
class Cart<T> {
	private List<T> list;

	public Cart() {
		list = new ArrayList<T>();
	}

	public void add(T item) {
		list.add(item);
	}

	public List<T> getList() {
		return list;
	}
}
