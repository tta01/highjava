package kr.or.ddit.basic;

public class T19WaitNotifyTest {
/*
 	wait() => 동기화 영역에서 락을 풀고 Wait-Set영역(공유 객체별로 존재)으로 이동시킨다.
 	
 	notify() 또는 notifyAll() => Wait-Set영역에 있는 스레드를 깨워서 실행할 수 있는 상태로 만든다.
 	(notify()는 하나, notifyAll()은 전부를 깨운다.)
 	
 	=> wait()와 notify(), notyfyAll()은 동기화 영역에서만 의미가 있고, 
 		Object클래스에서 제공하는 메서드이다.
 */
	
	public static void main(String[] args) {
		
		WorkObject workObj = new WorkObject();
		
		Thread thA = new ThreadA(workObj);
		Thread thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
		
	}
}

// 공통으로 사용할 객체
class WorkObject {
	public synchronized void methodA() { // 동기화 처리
		System.out.println("methodA()에서 작업 중'O'/...");
		
		System.out.println(Thread.currentThread().getName() + " : nofity() 호출");
		notify(); // 기다리고 있을 스레드 중 하나를 깨워줌
		
		try {
			System.out.println(Thread.currentThread().getName() + " : wait() 호출");
			wait(5000); // 락을 풀고 작업하던 것이 나감. 다음 것이 들어 올 수 있게
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public synchronized void methodB() { //동기화 처리
		System.out.println("methodB()에서 작업 중'O'/...");
		
		System.out.println(Thread.currentThread().getName() + " : nofity() 호출");
			notify();
		
		try {
			System.out.println(Thread.currentThread().getName() + " : wait() 호출");
			wait(5000);
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
// workObject의 methodA()만 호출하는 스레드
class ThreadA extends Thread {
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		super("ThreadA");
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=5; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료");
	}	
}

//workObject의 methodB()만 호출하는 스레드
class ThreadB extends Thread {
	private WorkObject workObj;

	public ThreadB(WorkObject workObj) {
		super("ThreadB");
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=5; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료");
	}	
}

/*
methodA()에서 작업 중'O'/... -> 작업진행하고 끝나면
ThreadA : nofity() 호출	  -> 다음 스레드 깨워서 부르고
ThreadA : wait() 호출		  -> 락을 풀어줘서 다음 스레드가 들어올 수 있게 함

methodB()에서 작업 중'O'/...  -> 락을 풀어줘서 들어온 다음 것이 작업을 진행항
ThreadB : nofity() 호출	  -> 위의 작업들이 끝날 때까지 무한반복함
ThreadB : wait() 호출

notifyAll -> 한 번에 다 깨움
*/