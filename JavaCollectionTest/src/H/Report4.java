package H;

/*
 	문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오.
	(단, enum 객체 생성시 반지름을 이용하도록 정의하시오.) 
	(태양계 행성의 반지름을 이용하여 면적구하기)
	예) 행성의 반지름(KM):
	수성(2439), 
	금성(6052), 
	지구(6371), 
	화성(3390), 
	목성(69911), 
	토성(58232), 
	천왕성(25362), 
	해왕성(24622)
	
	//enum = 상수, private
 */
public class Report4 {

	public enum Planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);

		public static double PI = 3.141592; // 파이 값 static으로 지정
		
		private double radius;

		private Planet(double radius) {
			this.radius = radius;
		}

		private double getRadius() {
			return radius;
		}
		
		private double getArea() {
			return 4*PI*radius*radius;
		}
	}

	public static void main(String[] args) {

		for (Planet planet : Planet.values()) {
			System.out.println(planet.name() + "의 면적은 " + planet.getArea());

		}
	}
}

// 생성자 메소드에 면접을 구하는 식을 넣어도 됨! 따로 구해도 되고!