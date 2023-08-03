package kr.or.ddit.basic;

public class T02ThreadTest {

	public static void main(String[] args) {

	/*
		  멀티스레드 프로그램 방식
		  
		  방법1 : Thread클래스를 상속한 클래스의 인스턴스를 생성한 후
		  이때 생성된 Thread객체의 start() 메서드를 호출하여 실행한다.
	*/

		Thread th1 = new MyThread1(); // 스레드는 start가 필요함
		th1.start();

	/*
		  방법2 : Runnable 인터페이스를 구현한 클래스의 인스턴스를 생성한 후
		  이 인스턴스를 Thread클래스의 생성자를 호출하면서 매개변수로 넘겨준다.
		  이때 생성된 Thread객체의 start() 메서드를 호출하여 실행한다.
		  extends! 자바는 다중 상속이 안됨
		  
	*/  Runnable r = new MyThread2();
		Thread th2 = new Thread(r);
		th2.start();

		// 방법3 : 익명 클래스를 이용하는 방법
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=200; i++) {
					System.out.print("@");
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}				
			}
		});
		th3.start();
	}
}

class MyThread1 extends Thread {

	@Override
	public void run() {
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");

			try { // 컨트롤 1 누르면 생성됨!!!!
					// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
					// 시간은 밀리세컨드 단위를 사용한다. 즉, 1000은 1초를 의미한다.
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable {

	@Override
	public void run() {
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");

			try {
				// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다. 즉, 1000은 1초를 의미한다.
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}