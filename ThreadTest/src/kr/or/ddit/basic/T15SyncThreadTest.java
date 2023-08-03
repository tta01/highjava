package kr.or.ddit.basic;
// 임계영역 = 동기화
public class T15SyncThreadTest {
	
	public static void main(String[] args) {
		
		// 동기화를 시키지 않으려면 줄지어 하나씩만 순서대로 실행해야 함. 그럼 단일 스레드..
		
		ShareObject sObj = new ShareObject();
		
		Thread th1 = new WorkerThread("1번스레드", sObj);
		Thread th2 = new WorkerThread("2번스레드", sObj);
		
		th1.start();
		th2.start();
	}
}

// 공통으로 사용할 객체
class ShareObject {
	//동기화 하는 방법1 : 메서드자체에 동기화 설정하기
	private int sum = 0;
	
	public synchronized void add() {
		// 동기화 하는 방법2 : 동기화 블럭으로 설정하기
		// mutex : Mutual exclusion Object(상호배제 : 동시접근차단)
//		synchronized (this) {
		
		
		for(int i=0; i<1000000000; i++) {} // 동기화 전까지 시간벌기용
		
		
		int n = sum;
		
		n += 10;
		
		sum = n;
		
		System.out.println(Thread.currentThread().getName() + " 합계 : "
				 + sum);
		}
	}
//}

//작업을 수행하는 스레드
class WorkerThread extends Thread {
	
	private ShareObject sObj;
	
	public WorkerThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() { // 10번의 add호출
		for(int i=1; i<=10; i++) {
			
			synchronized (sObj) {
				sObj.add();
				
			}
		}
	}
}