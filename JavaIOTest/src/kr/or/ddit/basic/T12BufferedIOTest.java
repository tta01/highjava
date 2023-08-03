package kr.or.ddit.basic;
//08.02
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 성능향상을 위한 보조스트림 예제
 * (바이트기반의 Buffered스트림 사용 예제)
 */
public class T12BufferedIOTest {
	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		
		try {
			
			fos = new FileOutputStream("e:/D_Other/bufferTest.txt");
			
			// 버퍼의 크기를 지정하지 않으면 기본적으로 8192byte(8KB)로 설정된다.
			bos = new BufferedOutputStream(fos,5);
			
			for(char ch='1'; ch<='9'; ch++) {
				bos.write(ch);
			}
			
			System.out.println("작업 끝!");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			} // 보조 스트림만 닫아도 된다.
		}
		}
	}

/* 8월 2일 날씨 뜨거움
 * 오늘은 편의점에서 점심을 먹었다.
 * 오랜만에 육개장을 먹었는데 사고 보니까 삼양 육개장이지 뭐야!?
 * 이미 물을 부어서 무를 수 없는 상태였다....
 * 심지어 물도 많이 부음.....
 * 육개장... 맛이 싱숭맹숭한게.. 맛이 그냥 그랬다....
 * 세상에 이럴 수 있나..?.. 
 * 삼각김밥이랑 같이 먹었다... 참치마요... 다음엔 그 육개장 안 먹을란다..
 * 배가 빠바바바ㅏ밥방 한 것도 아닌데 왜 이렇게 잠이 오는 걸까?
 */