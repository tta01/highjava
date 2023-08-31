package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.eclipse.jdt.internal.compiler.apt.model.Factory;

/**
 * 자카르타 프로젝트의 fileupload '모듈을 이용한 파일 업로드' 예제 알아두기만 하면 됨
 */

@WebServlet("/upload.do")
public class T12UploadControllerTest extends HttpServlet {

	private static final String UPLOAD_DIR = "upload_files";
	// 메모리 임계크키(이 크기가 넘어가면 레파지토리에 임시파일로 저장됨) : 메모리 공간 절약을 위해서?
	private static final int MEMORY_THREHOLD = 1024 * 1024 * 3; // 3MB넘어가면 임시파일로 저장
	// 파일 1개당 최대 크기
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 40;
	// 요청 파일 최대 크기
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 50;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// request의 body부분 출력하는거얌
		/*
		    System.out.println("=======================================");
		  
		    BufferedReader br = new BufferedReader(new
		    InputStreamReader(req.getInputStream()));
		  
		    String readLine = ""; while((readLine = br.readLine()) != null) {
		    System.out.println(readLine); }
		  
		    System.out.println("======================================="); }
		*/

		// Multipart Parsing 전에 파라미터 값 조회해보기
		System.out.println("Multipart Parsing 전 : " + req.getParameter("sender")); // 가져올 수 없음. null값

		if (ServletFileUpload.isMultipartContent(req)) { // commons로 활용 ( 톰캣이 없다는 가정 하에 이거 사용 예시 보여주기 위해서)

			Map<String, String> formDataMap = new HashMap<String, String>();

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THREHOLD);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir"))); // 임시디렉토리
			System.out.println(System.getProperty("java.io.tmpdir")); // 경로 궁금해서 찍어본거

			ServletFileUpload upload = new ServletFileUpload(factory);

			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);

			// 웹애플리케이션 루트 디렉토리 기준... 업로드 경로 설정하기
			String uploadPath = req.getServletContext().getRealPath("/") + UPLOAD_DIR;

			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			// 멀티파트 파싱 시작하기

			try {

				List<FileItem> formItems = upload.parseRequest(req);

				if (formItems != null && formItems.size() > 0) {
					for (FileItem item : formItems) {
						if (!item.isFormField()) {// 파일인 경우
							String fileName = item.getName();
							String filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);
							item.write(storeFile); // 업로드 파일 저장 => 실제 파일을 저장해주는 것이 write
							System.out.println("업로드 완료됨. => 파일 경로 :" + filePath);
						} else { // 폼데이터인 경우
							formDataMap.put(item.getFieldName(), item.getString("UTF-8"));
						}
					}
				}

				// Multipart Parsing 후에 파라미터 값 조회해보기
				System.out.println("Multipart Parsing 후 : " + formDataMap.get("sender"));

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
