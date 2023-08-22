package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T04ErrorHandler extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 예외 객체 가져오기
		Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.excetion");
		
		// 에러상태 코드 가져오기
		Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
		
		// 에러 메세지 가져오기
		String message = (String) req.getAttribute("javax.servlet.error.message");
		
		// 에러 발생한 서블릿 이름
		String servletName = (String) req.getAttribute("javax.servlet.error.servlet.name");
		
		if(servletName == null) {
			servletName = "알 수 없는 서블릿 이름";
		}
		// 에러발생 URI 정보
		String reqUri = (String) req.getAttribute("javax.servlet.error.request_uri");
		
		if(reqUri == null) {
			reqUri = "알 수 없는 URI";
		}
		
		///////////////////////////////////////////////////
	
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "에러/예외 정보";
		
		out.println("<html><head><title>" + title + "</title><head>");
		out.println("<body>");
		
		if(throwable == null && statusCode == null) {
			out.println("<h2>에러/예외 정보 없음</h2>");
		}else {
			out.println("<h2>에러/예외 정보 </h2>");
			out.println("<p>상태코드 : " + statusCode + "</P><br><br>");
			out.println("<p>에러/예외 메시지 : " + message + "</P><br><br>");
			out.println("<p>서블릿 이름 : " + servletName + "</P><br><br>");
			out.println("<p>요청 URI : " + reqUri + "</P><br><br>");
			
			if (statusCode != null) {
				out.println("<p>예외타입 : " + throwable.getClass().getName() + "</P><br><br>");
				out.println("<p>예외/에러 메시지 : " + throwable.getMessage()+"</p>");
			}
		}
			out.println("</body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
}
