package prt;

class Flower {
	static final int ROSE = 1;
	static final int SUNFLOWER=2;

}

class Animal {
	static final int LION = 1;
	static final int TIGER=2;
}

public class Enum2 {
	//enum = 클래스명 { };
	public enum City { 서울, 대전, 대구, 부산, 광주, 울산 };
	
	
	public enum Season {
		봄("3월 ~ 5월"), 여름("6월~8월"), 가을("9월 ~ 11월"), 겨울("12월~2월 ");
		
		private String data;
		
		Season(String data) {
			this.data=data;
		}
		
		public String getData() {
			return data;
		}
	}
	
	public static void main(String[] args) {
		
		City myCity1;
		City myCity2;
		
		myCity1 = City.대전;
		myCity2 = City.valueOf("대전");
		
		System.out.println("mc1 : " + myCity1.name()); //이름을 출력
		System.out.println("mc1의 or : " + myCity1.ordinal()); // 자릿수(인덱스)를 출력
		
		System.out.println();
		
		System.out.println("myc2 : " + myCity2.name()); // 이름을 출력
		System.out.println("myc2 : " + myCity2.ordinal()); // 자릿수(인덱스)를 출력
		
		
		
		System.out.println("----------------------------------------");
		
		Season ss = Season.valueOf("가을");
		System.out.println(ss.name()); // 이름 출력
		System.out.println(ss.ordinal()); // 자릿수? 출력
		System.out.println(ss.getData()); // 데이터 내용 출력
		
		Season[] enumArr = Season.values();
		
		for(int i=0; i<enumArr.length; i++) {
			System.out.println(enumArr[i].name() + " : " + enumArr[i].getData());
		}
		
		for(City city : City.values()) {
			System.out.println(city + " : " + city.ordinal());
		}

		City city = City.광주;
		
		System.out.println(city == City.대구);
		System.out.println(city == City.부산);
		System.out.println(city == City.광주);
		System.out.println();
		System.out.println(city.compareTo(city.대구));
		System.out.println(city.compareTo(city.부산));
		System.out.println(city.compareTo(city.광주));
		
		
		
		
	}
}

