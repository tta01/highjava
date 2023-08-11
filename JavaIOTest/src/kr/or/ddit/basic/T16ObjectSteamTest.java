package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 객체 입출력 보조 스트립(직렬화와 역직렬화)
 */
public class T16ObjectSteamTest {
	// 객체를 저장하거나 읽기 위해 = IO작업을 하기 위해 필요???
	public static void main(String[] args) {
		//Member 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "경기");
		Member mem3 = new Member("이몽룡", 40, "강원");
		Member mem4 = new Member("성춘향", 50, "광주");
		
		ObjectOutputStream oos = null;
		
		try {
			// 기본스트림은 앞에 file 붙은거!! 외워!!
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("e:/D_Other/memObj.bin")));
			
			
			// 쓰기 작업
			oos.writeObject(mem1); //직렬화 : 객체를 연속적인 흐름으로 진행되게(?) / 내부적으로 일어남
			oos.writeObject(mem2); //직렬화
			oos.writeObject(mem3); //직렬화
			oos.writeObject(mem4); //직렬화
			
			System.out.println("! 객체 쓰기 작업 완료 !");
			
		} catch (IOException ex) {
			ex.printStackTrace();

		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		///////////////////////////////////////////////////////////////////
		

		ObjectInputStream ois = null;
		
		try {
			
			
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("e:/D_Other/memObj.bin")));
			
			Object obj = null;
			
			//역직렬화
			while((obj = ois.readObject()) != null) {
				// 읽어온 데이터를 원래 객체형으로 변환(복원) 후 사용한다.
				Member mem = (Member) obj;
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println();
			}
				
		} catch (IOException ex) {
//			ex.printStackTrace();
			System.out.println("출력 완료");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * 회원정보를 담기 위한 VO
 */
class Member implements Serializable { // Serizlizable를 implements하면 그 타입이 되는 것
	// 자바는 Serizlizable 인터페이스를 구현한 객체만 직렬화 할 수 있도록 제한하고 있음
	
	// transient => 직렬화가 되지 않을 멤버변수에 지정한다. / 일시적인
	//				직렬화가 되지 않는 멤버변수는 기본 값으로 저장된다.
	//				(참조변수: null, 숫자형 변수: 0)
	//				(*static 필드도 직렬화가 되지 않는다. IO대상 = 객체! static은 대상 자체가 아님)
	
//	transient를 붙이면 값이 null로 초기화 되어 뜨지 않음 
	
	
	transient private String name;
	transient private int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}