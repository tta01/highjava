package prt;

/*
  	문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오.
	(단, enum 객체 생성시 반지름을 이용하도록 정의하시오.) 
		(태양계 행성의 반지름을 이용하여 면적구하기)
		예) 행성의 반지름(KM):
		수성(2439), 
		금성(6052), 
		지구(6371), 
		성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622)
 */
public class A {
	
	public static void main(String[] args) {
		Planet[] planet = Planet.values();
		
		for (Planet radius : planet) {
			System.out.println("반지름: " + radius.name() +  " : " +  radius.getRadius());
		}
		
		System.out.println("===================================");
		
		
		for (int i = 0; i < planet.length; i++) {
			System.out.println("면적: " + planet[i].name() + " : " + planet[i].getCalc());
		}
	}
}

enum Planet {
	수성(2439), 금성(6052), 지구(6371), 성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
	
	private int radius;
	private final double PI;
	
	Planet(int radius){
		this.radius = radius;
		PI =3.14;
	}
	
	public double getCalc() {
		return Math.pow(radius, 2)* 4 * PI;
	}
	
	public int getRadius() {
		return radius;
	}
	
}