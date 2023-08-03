package kr.or.ddit.basic;

public class T08ThreadPriorityTest {
	public static void main(String[] args) { // 우선순위는 스타트 전에 무조건 생성해야 함
		//....우선순위대로 안 나옴ㅎㅎㅎ^^;; 그냥 .. 설정해주면.. 그래도 낫다..?
		
		System.out.println("최대 우선순위 : " + Thread.MAX_PRIORITY);
		System.out.println("최소 우선순위 : " + Thread.MIN_PRIORITY);
		System.out.println("보통 우선순위 : " + Thread.NORM_PRIORITY);
		
		Thread[] thd = new Thread[] {
				new ThreadTest1(),
				new ThreadTest1(),
				new ThreadTest1(),
				new ThreadTest1(),
				new ThreadTest1(),
				new ThreadTest2()
		};
		
		// 우선 순위는 start()메서드를 호출하기 전에 설정해야 한다.
		for(int i=0; i<thd.length; i++) {
			if(i==5) {
				thd[i].setPriority(10);
			}else {
				thd[i].setPriority(1);
			}
		}
		
		//현재 설정된 우선순위 출력
		for(Thread th : thd) {
			System.out.println(th.getName()+"의 우선순위 : " + th.getPriority());
		}
		
		//스레드 시작
		for(Thread th : thd) {
			th.start();
		}
	}	
}

// 대문자를 A~Z까지 출력하는 스레드

class ThreadTest1 extends Thread {
	@Override
	public void run() {
		for(char ch='A'; ch<='Z'; ch++) {
			System.out.println(ch);
			
			// 아무것도 하지 않는 반복문(시간때우기용)
			for(long i=1; i<=1000000000L; i++) {}
		}
	}
}

//소문자를 a~z까지 출력하는 스레드

class ThreadTest2 extends Thread {
	@Override
	public void run() {
		for(char ch='a'; ch<='z'; ch++) {
			System.out.println(ch);
			
			// 아무것도 하지 않는 반복문(시간때우기용)
			for(long i=1; i<=1000000000L; i++) {}
		}
	}
}



