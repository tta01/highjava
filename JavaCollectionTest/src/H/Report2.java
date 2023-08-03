package H;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Report2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			    System.out.println("============================");
	            System.out.println("      Lotto 프로그램          ");
	            System.out.println("----------------------------");
	            System.out.println("  1. Lotto 구입");
	            System.out.println("  2. 프로그램 종료");
	            System.out.println("============================");
	            System.out.println();
	            System.out.print("메뉴선택 : ");
	            
	            switch (sc.nextInt()) {
	            	case 1:
	            		buyLotto(sc);
	            		break;
	            	case 2:
	            		System.out.println("프로그램을 종료합니다.");
	            		return;
	            	default : 
	            		System.out.println(" 잘못된 번호 입니다.");
	            }
		}
	}
	
	public static void buyLotto(Scanner sc) {
		System.out.println("Lotto 구입 시작");
		System.out.println("1000원에 로또번호 하나입니다.");
		System.out.println("금액입력 : ");
		
		int money = sc.nextInt();
		int change = money%1000;
		System.out.println();
		
		for(int i=0; i<money/1000; i++) {
			Set<Integer> lottoNum = new HashSet<Integer>();
			
			while(lottoNum.size() < 6) {
				int num = (int)(Math.random()*45+1);
				lottoNum.add(num);
			}
			System.out.println((i+1+"번째 로또번호 : ") + lottoNum);
		}
		
		System.out.println();
		System.out.println("받은 금액은 "+money+"원이고 거스름돈은"+change+"원 입니다.");
	}
}
