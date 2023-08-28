package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 서블릿 3부터 지원하는 Part 인터페이스를 이용한 파일업로드 예제
 * @author PC-10
 *
 */

@MultipartConfig
@WebServlet("/upload2.do")
public class T13UploadControllerTest2 extends HttpServlet {
// 멀티파트를 처리한다는 것을 알려줘야 기능을 동작함
	private static final String UPLOAD_DIR = "upload_files";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Multipart Parsing 전에 파라미터 값 조회해보기
		System.out.println("Multipart Parsing 전 => " + req.getParameter("sender"));
		
		// 웹애플리케이션 루트 디렉토리 기준... 업로드 경로 설정하기
		String uploadPath = "e:/D_Other/"+ UPLOAD_DIR;

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			
			String fileName = "";
			
			for(Part part : req.getParts()) { // getParts - 멀티파트를 하나씩 가져올 수 있는 것
			
			/*	System.out.println(part.getHeader("content-disposition")); // filename이 붙음
				
				System.out.println("전송된 part명 => " + part.getName());
				System.out.println("전송된 파일명 => " + part.getSubmittedFileName());
			*/
				
				fileName = part.getSubmittedFileName();
				
				if(fileName != null && !fileName.equals("")) { // 널 값, 빈 값
					// 파일 저장
					part.write(uploadPath + File.separator + fileName);
					// 직접 / 붙여도 되지만  'File.separator'로 작성하면 운영체제에 맞춰서 슬래시가 알아서 붙음 
					
					System.out.println("파일 업로드 완료!!! => " + uploadPath + File.separator + fileName);
					
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	
}
