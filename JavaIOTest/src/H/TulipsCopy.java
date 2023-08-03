package H;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 	'd:/D_Other/'에 있는 'Tulips.jpg'파일을
	'복사본_Tulips.jpg'로 복사하는 프로그램을
	작성하시오.
*/

public class TulipsCopy {
	public static void main(String[] args) throws IOException { // 던지면 try/catch문 안 써도 됨
		
		FileInputStream fis = new FileInputStream("e:/D_Other/Tulips.jpg");
		FileOutputStream fos = new FileOutputStream("E:/D_Other/복사본_Tulips.jpg");
		
		int data = 0;
		
		while((data = fis.read()) != -1) {
			fos.write(data);
			
		}
		
		fis.close();
		fos.close();
		
		
	}	
	
}

