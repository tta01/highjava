package H;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class PhoneBookHW {
	private Scanner scan;
	private Map<String, PhoneVO> phoneBookMap;
	
	public PhoneBookHW() {
		scan = new Scanner(System.in);
		phoneBookMap = new HashMap<String, PhoneVO>();
	}
	
	// 메뉴를 출력하는 메서드
	public void displayMenu(){
		System.out.println();
		System.out.println("메뉴를 선택하세요.");
		System.out.println(" 1. 전화번호 등록");
		System.out.println(" 2. 전화번호 수정");
		System.out.println(" 3. 전화번호 삭제");
		System.out.println(" 4. 전화번호 검색");
		System.out.println(" 5. 전화번호 전체 출력");
		System.out.println(" 0. 프로그램 종료");
		System.out.print(" 번호입력 >> ");		
	}
	
	// 프로그램을 시작하는 메서드
	public void phoneBookStart(){
		//loadData();
		System.out.println("===============================================");
		System.out.println("   전화번호 관리 프로그램(파일로 저장됨)");
		System.out.println("===============================================");
		
		while(true){
			
			displayMenu();  // 메뉴 출력
			
			int menuNum = scan.nextInt();   // 메뉴 번호 입력
			
			switch(menuNum){
				case 1 : insert();		// 등록
					break;
				case 2 : update();		// 수정
					break;
				case 3 : delete();		// 삭제
					break;
				case 4 : search();		// 검색
					break;
				case 5 : displayAll();	// 전체 출력
					break;
				case 0 :
					saveData();
					System.out.println("프로그램을 종료합니다...");
					return;
				default :
					System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}

	// 전화번호 정보를 검색하기 위한 메서드
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");

		String name = scan.next();

		PhoneVO pv = phoneBookMap.get(name);

		if (pv == null){
			System.out.println(name + "씨는 전화번호 정보가 없습니다.");
		}else {
			System.out.println(name + "씨 전화번호 정보");
			System.out.println("이름: "+pv.getName());
			System.out.println("전화: "+pv.getTel());
			System.out.println("주소: "+pv.getAddr());
		}
		System.out.println("검색 작업 완료...");
	}

	/*
	* 전체 자료를 출력하기 위한 메서드
	 */
	private void displayAll() {
		System.out.println("====================================");
		System.out.println("번호\t이름\t전화번호\t주소");
		System.out.println("====================================");

		Set<String> keySet = phoneBookMap.keySet();

		if (keySet.size() == 0){
			System.out.println("등록된 전화번호 정보가 없습니다.");
		}else {
			int cnt=0;

			for (String name : keySet){
				cnt++;
				PhoneVO pv = phoneBookMap.get(name);

				System.out.println(" "+cnt+"\t"+pv.getName()+"\t"+pv.getTel()+"\t"+pv.getAddr());
			}
		}

		System.out.println("====================================");
		System.out.println("출력 완료...");
	}

	/*
	* 전화번호 정보를 삭제하기 위한 메서드
	 */
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");

		String name = scan.next();

		//remove(key) -> 삭제 성공하면 삭제된 value값을 반환하고, 실패하면 null을 반환한다.
		if (phoneBookMap.remove(name) == null){
			System.out.println(name+ "씨는 등록된 사람이 아닙니다.");
		}else {
			System.out.println(name+ "씨 정보를 삭제했습니다.");
		}
		System.out.println("삭제작업 완료..");
	}

	/*
	전화번로 정보를 수정하기 위한 메서드
	 */
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");

		String name = scan.next();

		// 이미 등록된 사람인지 검사하기
		// get()메서드로 가져올때 가져올 데이터가 없으면 null을 반환한다.
		if (phoneBookMap.get(name) == null){
			System.out.println(name + "씨는 없는 사람입니다.");
			System.out.println("다시 입력해 주세요.");
			return;
		}

		System.out.print("전화번호 >> ");
		String tel = scan.next();

		scan.nextLine();//입력버퍼에 남아있는 엔터키를 읽어오는 용도.

		System.out.print("주소 >> ");
		String addr = scan.nextLine();

		PhoneVO pv = new PhoneVO(name, tel, addr);
		phoneBookMap.put(name, pv);

		System.out.println(name +"씨 수정완료");
	}

	/*
	새로운 전화번호 정보를 등록하기 위한 메서드
	이미 등록된 사람은 등록되지 않는다.
	 */
	private void insert() {
		System.out.println();
		System.out.println("새로 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");

		String name = scan.next();

		// 이미 등록된 사람인지 검사하기
		// get()메서드로 가져올때 가져올 데이터가 없으면 null을 반환한다.
		if (phoneBookMap.get(name) != null){
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			System.out.println("다시 등록해 주세요.");
			return;
		}

		System.out.print("전화번호 >> ");
		String tel = scan.next();

		scan.nextLine();//입력버퍼에 남아있는 엔터키를 읽어오는 용도.

		System.out.print("주소 >> ");
		String addr = scan.nextLine();

		PhoneVO pv = new PhoneVO(name, tel, addr);
		phoneBookMap.put(name, pv);

		System.out.println(name +"씨 등록완료");
	}

	public static void main(String[] args) {
		new PhoneBookHW().phoneBookStart();
	}

	private void saveData(){

		ObjectOutputStream oos = null;
		Set<String> keySet = phoneBookMap.keySet();

		try {
			oos = new ObjectOutputStream(
					new FileOutputStream("e:/D_Other/PhoneBook.bin"));

			//쓰기 작업
			for (String name : keySet) {
				PhoneVO pv = phoneBookMap.get(name);
				oos.writeObject(pv);

				System.out.println("전화번호부 저장 완료");
			}
		}catch (IOException e){
			e.printStackTrace();
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void loadData(){
		ObjectInputStream ois = null;

		try {

			ois = new ObjectInputStream(
					new FileInputStream("e:/D_Other/PhoneBook.bin"));

			Object obj = null;

			// 역직렬화
			while ((obj = ois.readObject()) != null){

				// 읽어온 데이터를 원래의 객체형으로 변환 후 사용한다.
				PhoneVO pv =(PhoneVO) obj;
				phoneBookMap.put(pv.getName(), pv);

			}

		}catch (IOException | ClassNotFoundException e){
			System.out.println("전화번호부 읽기 완료");
		}finally {
			try {
				ois.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}

}
/*
* 전화번로 정보를 저장히기 위한 VO클래스
 */
class PhoneVO implements Serializable{

	private String name;
	private String tel;
	private String addr;

	public PhoneVO(String name, String tel, String addr) {
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "PhoneVO{" +
				"name='" + name + '\'' +
				", tel='" + tel + '\'' +
				", addr='" + addr + '\'' +
				'}';
	}
}


