package kr.or.ddit.comm.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.vo.AtchFileVO;

@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String atchFileId = req.getParameter("fileId");
		String fileSn = req.getParameter("fileSn");
		
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVO fileVO = new AtchFileVO();
		fileVO.setAtchFileId(Long.parseLong(atchFileId));
		fileVO.setFileSn(Integer.parseInt(fileSn));
		AtchFileVO atchFileVO = fileService.getAtchFileDetail(fileVO);
		
	/*
		Content-Disposition 헤더에 대하여 (multi part에서 스쳐지나감..)
		
		Content-Disposition: inline (default)
		Content-Disposition: attachment						// 파일 다운로드 - url경로가 파일 이름으로 지정됨
		Content-Disposition: attachment; filename="a.jpg"	// 파일다운로드 - 다운받으려는 파일을 지정한 이름으로 받을 수 있음
		 
	*/
		
		resp.setContentType("image/jpeg"); // => 바이너리 데이터 / 알아서 다운 받음
		
		// URL에는 공백문자를 포함할 수 없다. URLEncoding을 하면 공백은(+)로 표시되기 때문에 +문자를 공백문자인 %20으로 바꿔준다.
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(atchFileVO.getOrignlFileNm(), "UTF-8").replaceAll("\\+" , "%20") + "\"");
		// 헤더에 넣으면 파일 이름이 한글일 때 깨짐! 인코딩 처리 따로 안해줘서 => 해주면 되는데 !!인코딩 처리 후!! 작업
		// 서블릿 WebContent 안에 집어 넣으면 브라우저에서 전부 다 접근 가능함 !!(lib빼고)
		// list에서 링크주소 복사해서 src에 집어 넣으면..? 웹 컨텐트에 없는 것도 접근 가능함
		
		// 파일을 읽어서 응답으로 보내주기(response body로 들어감)
		FileInputStream fis = new FileInputStream(atchFileVO.getFileStreCours());
		OutputStream out = resp.getOutputStream();
		
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		
		int data = 0;
		
		while((data = bis.read()) != -1) {
			bos.write(data);
		}
		
		bis.close();
		bos.close();
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
