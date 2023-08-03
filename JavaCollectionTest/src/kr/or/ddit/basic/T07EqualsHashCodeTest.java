package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class T07EqualsHashCodeTest {
	/*
	 	해시 함수(hash function)는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다.
	 	해시함수에 의해 얻어지는 값은 해시 값, 해시 코드 또는 간단히 해시라고도 한다.
	 	
	 	HashSet, HashMap, Hashtable과 같은 객체들을 사용할 경우
	 	객체가 서로 같은지를 비교하기 위해 equals()메서드와 hashCode() 메서드를 호출한다.
	 	그래서 객체가 서로 같은지 여부를 결정하려면 두 메서드를 재정의 해야 한다.
	 	HashSet, HashMap, Hashtable 에서 객체가 같은지 여부는 데이터를 추가할 때 검사한다.
	 	
	 	 - equals()는 두 객체의 내용(값)이 같은지 비교하는 메서드이고,
	 	 - hashCode()는 객체에 대한 해시코드 값을 반환하는 메서드이다.
	 	 
	 	 - equals()와 hashCode() 메서드에 관한 규칙(Convention)
	 	 1. 두 객체가 같으면 반드시 같은 해시코드를 가져야 한다.
	 	 2. 두 객체가 같으면 equals()메서드를 호출했을 때 true를 반환해야 한다.
	 	 즉, 객체 a,b가 같다면 a.equals(b)와 b.equals(a) 둘 다 true이어야 한다.
	 	 3. 두 객체의 해시코드가 같다고 해서 두 객체가 반드시 같은 객체는 아니다.
	 	 하지만 두 객체가 같으면 반드시  해시코드값이 같아야 한다.
	 	 4. HashCode()는 기본적으로 힙 메모리에 있는 각 객체에 대한 메모리 주소 매핑 정보를 기반으로 한 정수값을 반환한다.
	 	 그러므로, 클래스에서 HashCode()를 override하지 않으면 절대로 두 객체가 같은 것으로 간주 될 수 없다.
	 	 5. equals()메서드를 override하려면 반드시 HashCode()도 override 해야 한다.
	 	 
	 	 - HashCode() 에서 사용하는 '해싱 알고리즘'에서 서로 다른 객체에 대하여 같은 해시코드 값을 생성할 수 있다.
	 	 그래서 객체가 같지 않더라도 해시코드값이 같을 수 있다.

	 */
	public static void main(String[] args) {
		
		Person p1 =  new Person(1,"홍길동");
		Person p2 =  new Person(1,"홍길동");
		Person p3 =  new Person(1,"이순신");

		System.out.println("p1.equals(p2) : " + p1.equals(p2));
		System.out.println("p1 == p2 : " + (p1 == p2));
		
	
		System.out.println(p1.hashCode() +" : " + p2.hashCode());
		System.out.println(new String("홍길동").hashCode());
		System.out.println(new String("홍길동").hashCode());
		
//		System.out.println("AA".hashCode());
//		System.out.println("BB".hashCode());

		Set<Person> pSet = new HashSet<Person>();
		System.out.println("pSet.add1(p1) 성공여부 : " + pSet.add(p1));
		System.out.println("pSet.add1(p2) 성공여부 : " + pSet.add(p2));
		
		System.out.println("p1, p1 등록후 데이터 : " );
		for(Person p : pSet) {
			System.out.println(p.getId() + " : "+ p.getName());
		}
		
		System.out.println("pSet.add(p3) 성공여부 : " + pSet.add(p3) );
		System.out.println("p3 등록후 데이터 : " );
		for(Person p: pSet) {
			System.out.println(p.getId() + " : "+ p.getName());
		}
	}
}

class Person {
	private int id;
	private String name;

	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	// id, name가 같으면 같다고해줌
//	@Override
//	public boolean equals(Object obj) {
//		
//		Person p = (Person) obj;
//		
//		return this.getId() == p.getId()
//				&& this.getName().equals(p.getName());
//	}
//	
//	@Override
//	public int hashCode() {
//		
//		return (name+id).hashCode();
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	

	
}