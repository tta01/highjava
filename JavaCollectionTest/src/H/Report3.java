package H;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Report3 {
	public static void main(String[] args) {
// 스캐너를 여기서 호출하거나	
		new Report3(). HotelStart();
 
	}
// 스캐너 객체를 여기서 생성하거나
	static Scanner scan;
	static Map<String, String> HotelMap; // 키 = 방 번호
// 생성자로 생성해서 호출하거나
	public Report3() {
		scan = new Scanner(System.in);
		HotelMap = new HashMap<String, String>();
	}

	public void HotelStart() {
		
		System.out.println("***********************");
		System.out.println("    호텔 문을 열었습니다.");
		System.out.println("***********************");
		
		while (true) {
			System.out.println("**********************************");
			System.out.println("       어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인 2.체크아웃 3.객실예약현황 4.업무종료");
			System.out.println("**********************************");
			System.out.print("메뉴 선택 : ");
			
			int menu = scan.nextInt(); // 메뉴 번호 입력란			

			switch (menu) {
			case 1:
				System.out.println(1);
				checkIn(); // 체크인
				break;
			case 2:
				checkOut(); // 체크아웃
				break;
			case 3:
				roomCon(); // 객실상태(현황)
				break;
			case 4:
				System.out.println("업무가 종료되었습니다. 감사합니다.");
				break;
			default:
				System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
			}
		}
	}

	// 객실 상태
	private static void roomCon() {
//		 interator
		Set<String> keySet = HotelMap.keySet();

		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			String key = it.next();
			System.out.println("방 번호" + key + "호의 " + HotelMap.get(key) + "님");
		}
		
		
		// for문 name오류남..
//		for(String roomNum : name) {
//			System.out.println(roomNum + " : " + HotelMap.get(name));			
//		}
		
		
		// value 값들 이용
//		for(String value : HotelMap.values()) {
//			System.out.println(value);
//		}
		
	//entrySet()
//		Set<Map.Entry<String, String>> entrySet = HotelMap.entrySet();
//		
//		Iterator<Map.Entry<String,String>> entry = entryIt = entrySet.iterator();
//		
//		while(entryIt.hasNext()) {
//			Map.Entry<String, String> entry = entryIt.next();
//			
//			System.out.println(entry.getKey() + "호실");
//		}
	
	}
	
	private static void checkOut() {
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.println("방 번호 입력 : ");
		String roomNum = scan.next();

		if (HotelMap.get(roomNum) != null) {
			HotelMap.remove(roomNum);
			System.out.println("체크아웃 되었습니다.");
		} else {
			System.out.println("예약된 방이 아닙니다.");
		}
	}

	private static void checkIn() {
		System.out.println("어느 방을 체크인 하시겠습니까?");
		System.out.println("방 번호 : ");
		String roomNum = scan.next();
//		 sc.nextInt();

		System.out.println("체크인 하시겠습니까?");
		System.out.println("이름 입력 : ");
		String name = scan.next();
		scan.nextLine();

		if (HotelMap.get(roomNum) != null) {
			System.out.println(roomNum + "호실은 이미 예약이 되어있습니다.");
			System.out.println("다른 객실을 선택해주세요.");
		} else {
			HotelMap.put(roomNum, name);
			System.out.println("체크인 되었습니다.");
		}

	}
}



//
//class HotelVO {
//	
//	private Integer roomNum;
//	private String name;
//	
//	public HotelVO(Integer roomNum, String name) {
//		super();
//		this.roomNum = roomNum;
//		this.name = name;
//	}
//
//	public Integer getRoomNum() {
//		return roomNum;
//	}
//
//	public void setRoomNum(Integer roomNum) {
//		this.roomNum = roomNum;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	@Override
//	public String toString() {
//		return "HotelVO [roomNum=" + roomNum + ", name=" + name + "]";
//	}
//
//	
//	
//}