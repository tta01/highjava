package kr.or.ddit.basic;

import java.util.LinkedList;

public class T02StackQueueTest {

	public static void main(String[] args) { // 데이터 삽입,삭제에 용이함 but, 데이터를 찾기가 번거로움?
		/*
		    Stack => 후입선출(LIFO)의 자료구조
		    Queue => 선입선출(FIFO)의 자료구조
		*/
		
		LinkedList<String> stack = new LinkedList<String>();
		
		/*
		 	Stack 명령
		 	1) 데이터 입력 : push(저장할 값)
		 	2) 데이터 출력 : pop() => 데이터를 꺼내온 후 꺼내온 데이터를 stack에서 삭제
		 */
		
		stack.push("홍길동");
		stack.push("일지매");
		stack.push("변학도");
		stack.push("강감찬");
		
		System.out.println("현재 stack값들 : " + stack); // 출력시 마지막에 넣은 것 부터 출력됨
		
		String data = stack.pop(); // 스택을 꺼낼 건데 스트링 타입으로 받아서 데이터에 저장됨 & 강감찬부터 저장됨
		System.out.println("꺼내온 데이터 : " + data ); // 데이터 하나를 꺼냄 - 강감찬이 꺼내지고
		System.out.println("꺼내온 데이터 : " + stack.pop()); // 데이터 하나 더 꺼냄 - 변학도가 꺼내짐
		System.out.println("현재 stack 값들 : " + stack); // 꺼내고 남은 값들이 거꾸로 출력됨 일지매-홍길동
		
		stack.push("성춘향");
		System.out.println("현재 stack값들 : " + stack); // 성춘향을 맨 마지막에 넣었기 때문에 가장 먼저 출력됨
		System.out.println("꺼내온 데이터 : " + stack.pop()); //맨 마지막에 넣은 것을 가장 먼저 빼내기 때문에 성춘향이 꺼내짐
		System.out.println();
		System.out.println("=============================");
		System.out.println();
		
		LinkedList<String> queue = new LinkedList<String>();
		
		/*
		 	Queue 명령
		 	1) 데이터 입력 : offer(저장할 값)
		 	2) 데이터 출력 : poll => 데이터를 Queue에서 꺼내온 후 꺼내온 데이터는
		 				 Queue에서 삭제한다.
		 */
		queue.offer("홍길동");
		queue.offer("일지매");
		queue.offer("변학도");
		queue.offer("강감찬");
		System.out.println("현재 Queue값 : " + queue); // 가장 먼저 집어 넣은 데이터부터 출력됨 / 홍길동부터 강감찬 순서대로
		
		String temp = queue.poll();
		System.out.println("꺼내온 자료 : " + temp); // 넣은 것부터 빠져나옴. 홍길동꺼내고
		System.out.println("꺼내온 자료 : " + queue.poll()); // 일지매 꺼내고
		System.out.println("현재 Queue 값 : " + queue);	// 남은거 변학도 - 강감찬
		
		if(queue.offer("성춘향")) { // 성춘향을 목록에 집어 넣음
			System.out.println("신규 등록 자료 : 성춘향");
		}
		
		System.out.println("현재 Queue의 값 : " + queue); // 가장 나중에 넣은 성춘향이 맨 마지막에 입력됨 / 변학도 - 강감찬 - 성춘향
		System.out.println("꺼내온 자료 : " + queue.poll()); // 변학도가 꺼내짐
		
	}
}

