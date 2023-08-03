package kr.or.ddit.basic;

public class T09ThreadDaemonTest {
	public static void main(String[] args) {
		
		AutoSaveThread autoSave = new AutoSaveThread();
		
		// 데몬스레드로 설정하기 - 일반스레드 보조역할 => 일반스레드가 없으면 데몬스레드도 필요 없음
		// start() 메서드를 호출하기 전에 설정해야 한다!!! 스타트 전에 데몬스레드 설정
		autoSave.setDaemon(true);
		autoSave.start();
		
		try {
			for(int i=1; i<=20; i++) {
				System.out.println("작업- " + i);
				Thread.sleep(1000); // 1초(단위)
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("main 스레드 종료...");
	}

}

class AutoSaveThread extends Thread {
	public void save() {
		System.out.println("작업 내용을 저장합니다...");
	}

	@Override
	public void run() {
		while(true) {
			try {
			 Thread.sleep(3000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			save(); //저장기능 호출
		}
	}
}
