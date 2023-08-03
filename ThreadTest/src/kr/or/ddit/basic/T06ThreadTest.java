package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class T06ThreadTest {
	// 실행 후 값을 입력하지 않을시 main이 가장 먼저 종료 됨 -> run -> start
	// 세 가지가 다 끝나야 프로그램이 완전히 종료 된 것
	
	//입력여부를 확인하기 위한 변수 선언
	// 모든스레드에서 공통으로 사용할 변수
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		
		Thread th1 = new DataInput();
		Thread th2 = new CountDown();
		
		th1.start();
		th2.start();
		
	}
}

/**
 * 데이터 입력 받는 스레드
 */
class DataInput extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 써 봐");
		
		// 입력이 완료되면 inputCheck변수를 true로 변경한다.
		T06ThreadTest.inputCheck = true;
		
		System.out.println("입력한 값은 " + str + " (이)야");
		
	}
}
/**
 * 카운트다운 처리를 위한 스레드
 */
class CountDown extends Thread {
	@Override
	public void run() {
		
		for(int i=10; i>=1; i--) {
			// 입력이 완료 되었는지 여부를 검사하고 입력이 완료되면
			// run() 메서드를 종료시킨다. 즉 현재 스레드를 종료시킨다.
			if(T06ThreadTest.inputCheck) {
				return; // 현재 메서드를 벗어남 (여기서는 run) / run() 메서드가 종료되면 스레드도 끝남
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 10초가 경과되었는데도 입력이 안 되면 프로그램을 종료한다.
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0); // 프로그램을 종료시키는 명령
		// 스레드가 다 종료되어야 프로그램이 종료되는 것
		
	}
}
