package kr.or.ddit.basic;

public class T03ThreadTest {
	public static void main(String[] args) {
		//스레드의 수행시간 체크해보기
		
		Thread th = new Thread(new MyRunner()); // 두 번째 방법
		
		//UTC(Universal Time Doodinated : 협정 세계 표준시)를 사용하여
		// 1970년 1월 1일 0시 0분 0초를 기준으로 경과한 시간을 밀리세컨드 단위로 나타낸다.
		long startTime = System.currentTimeMillis(); // 수행시간 구하는 함수
		
		th.start(); //스레드 구동 시작
		
		try {
			th.join(); // 현재 실행 중인 스레드에서 작업 중인 스레드(지금은 th스레드)가 종료될 때까지 기다린다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과 시간 : " + (endTime - startTime) + "(ms)");
	}
}

// 1~10억 까지의 합계를 구하기
class MyRunner implements Runnable {
	
	@Override
	public void run() {
		long sum = 0;
		for(int i=1; i<=1000000000; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}
