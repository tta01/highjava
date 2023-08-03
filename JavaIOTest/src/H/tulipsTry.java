package H;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/*
	'd:/D_Other/'에 있는 'Tulips.jpg'파일을
	'복사본_Tulips.jpg'로 복사하는 프로그램을
	작성하시오.
*/
public class tulipsTry {

	public static void main(String[] args) {
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
	
		try {
			
			fis = new FileInputStream("e:/D_Other/Tulips.jpg");
			fos = new FileOutputStream("e:/D_Other/복사본_Tulips.jpg");
			
			int data = 0;
			
			while((data = fis.read()) != -1) {
				fos.write(data);
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}		
	}
}
