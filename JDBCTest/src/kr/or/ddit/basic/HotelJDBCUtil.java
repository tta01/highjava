package kr.or.ddit.basic;
/*
  호텔 운영을 관리하는 프로그램 작성.(DB 이용)
 - 키값은 방번호 
 
**************************
호텔 문을 열었습니다.
**************************

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 101 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 홍길동 <-- 입력
체크인 되었습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 성춘향 <-- 입력
체크인 되었습니다

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향
방번호 : 101, 투숙객 : 홍길동

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 => 101 <-- 입력
체크아웃 되었습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 허준 <-- 입력
102방에는 이미 사람이 있습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 => 101 <-- 입력
101방에는 체크인한 사람이 없습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 4 <-- 입력

**************************
호텔 문을 닫았습니다.
**************************
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import kr.or.ddit.Util.JDBCUtil3;

public class HotelJDBCUtil {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public static Map<Integer, HotelVO2>hotelMap = new HashMap<Integer, HotelVO2>();
	
	static Scanner sc = new Scanner(System.in); 
	
	public static void main(String[] args) {
		new HotelJDBCUtil().HotelStart();
		
	}	
	
	public void HotelStart() {
		
		System.out.println("**************************");
		System.out.println("★ 호텔 문을 열었습니다 ☆");
		System.out.println("**************************");
		
	while(true) {
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		System.out.println("메뉴를 선택하세요 >> ");
		
		int menu = sc.nextInt();
		
		switch(menu) {
		case 1:
//			Input();
			checkIn();
			break;
		case 2:
			checkOut();
			break;
		case 3:
			room();
			break;
		case 4:
//			save();
			System.out.println("업무를 종료합니다.");
			System.exit(0);
			break;
		default:
			System.out.println("잘못된 번호입니다. 다시 선택해주세요!");
		}
	}
}
	private void room() {
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			stmt = conn.createStatement();
			
			String sql = "select * from hotel_mng";
			
			rs = stmt.executeQuery(sql);

			Set<Integer> keySet = hotelMap.keySet();
			Iterator<Integer> it = keySet.iterator();
			
			while(it.hasNext()) {
				Integer key = it.next();
				System.out.println("방 번호 " + key + "호의 " + hotelMap.get(key).getName() + "님");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}
	
	private void checkOut() {
		
		System.out.println("체크아웃 하시겠습니까?");
		System.out.println("방 번호 : ");
		int roomNum = sc.nextInt();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from hotel_mng where room_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			
			int cnt = pstmt.executeUpdate();
			
			if(hotelMap.get(roomNum) != null) {
				hotelMap.remove(roomNum);
				System.out.println("☆ 체크아웃 완료 ☆");
			} else {
				System.out.println("예약된 방이 아닙니다!");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
	}
	
	private void checkIn() {
		
//		boolean isExist = false;
		
		int room_num = 0;

		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.println("방 번호 : ");
		int roomNum = sc.nextInt();
		if(hotelMap.get(roomNum) != null ) {
			System.out.println(roomNum + "호실은 이미 예약 되어있습니다.");
			System.out.println("다른 객실을 선택해주세요.");
			checkIn();
			return;
		} else {
			System.out.println("★ 체크인 가능합니다  ★ ");
		}
		
		System.out.println("체크인 하시려면 이름을 입력해주세요!");
		System.out.println("이름 : ");
		String name = sc.next();
		System.out.println("★ 체크인 완료 ★");
		hotelMap.put(roomNum, new HotelVO2(roomNum,name));
		// Map에 담아
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql =" INSERT INTO hotel_mng ( "
					 + "   guest_name, 	 "
					 + "   room_num "
					 + " ) VALUES (?, ?) ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setInt(2, room_num);
		
		int rs = pstmt.executeUpdate();
	
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
	
	}
}

class HotelVO2 {

	private String name;
	private int roomNum;
	
	
	public HotelVO2(int roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getRoomNum() {
		return roomNum;
	}


	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}


	@Override
	public String toString() {
		return "HotelVO2 [name=" + name + ", roomNum=" + roomNum + "]";
	}

}
