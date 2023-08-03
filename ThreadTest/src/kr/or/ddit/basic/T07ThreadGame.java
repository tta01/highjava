package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.

컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.

JOptionPane 안에 showInputDialog => 사용자에게 직접 입력 받아올 수 있음

입력시간은 5초로 제한하고 카운트 다운을 진행한다.
5초안에 입력이 없으면 게임을 진것으로 처리한다.

5초안에 입력이 완료되면 승패를 출력한다.

결과예시)
	=== 결 과 ===
	컴퓨터 : 가위
	당  신 : 바위
	결  과 : 당신이 이겼습니다.

*/
public class T07ThreadGame {
	public static boolean inputCheck = false;
	public static String user = ""; // 사용자의 가위바위보가 저장될 변수

	public static void main(String[] args) {

		// 난수를 이용해 컴퓨터의 가위 바위 보를 정한다.
		
		UserInput2 input = new UserInput2();
		input.start();
		// 카운트 다운 스레드 실행
		CountDown2 cd = new CountDown2();
		cd.start();
		String[] data = {"가위", "바위", "보"}; // 배열로 가위, 바위, 보 나열
		int index = (int)(Math.random()*3); // 0~2의 난수를 발생 시킬껀데 출력할게 아니라 굳이 1부터 할 필요가 없어서 +1을 해주지 않은 것.
		String com = data[index];
		// 사용자로부터 가위, 바위, 보 입력받기
		
		
		try {
			input.join(); // 입력이 끝날 때 까지 기다림
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 결과 판정
		String result ="";
		if(user.equals(com)) {
			result = "비겼습니다.";
		}else if((user.equals("가위") && com.equals("보")
					||(user.equals("바위") && com.equals("가위"))
					||(user.equals("보") && com.equals("바위")))){
			result ="사용자 승리";
		}else {
			result = "컴퓨터 승리";
		}
		
		// 결과 출력
		System.out.println("=== 결 과 ===");
		System.out.println(" 컴퓨터 : " + com);
		System.out.println(" 사용자 : " + user);
		System.out.println(" 결   과 : " + result);
	}
}

/**
 * 카운트다운
 */
class CountDown2 extends Thread {

	@Override
	public void run() {

		for (int i = 5; i >= 1; i--) { // 5초부터 거꾸로 세기
			System.out.println(i);
			if (T07ThreadGame.inputCheck) {
				return;
			}
			
			try {
				Thread.sleep(1000); // 5초만 세
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("시간 초과. 당신이 졌습니다.");
		System.exit(0); // 프로그램 종료
	}
}

class UserInput2 extends Thread {
	
	@Override
	public void run() {
		
		String inputData = "";
		
		do {
			inputData = JOptionPane.showInputDialog("가위, 바위, 보를 입력하세요");
			} while(!inputData.equals("가위") && !inputData.equals("보") && !inputData.equals("바위"));
		
		T07ThreadGame.inputCheck = true; //입력이 완료됨을 알려주는 변수 값을 변경
		T07ThreadGame.user = inputData; // 입력 값 설정
	}
}





















