package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T06ServletSessionTest extends HttpServlet {
	/*
	  	세션(HttpSession)객체(서버에서 사용자별 관리)
	  	
	  	- 세션을 통해서 사용자(웹 브라우저)별로 구분하여 정보를 관리할 수 있다.(세션 ID이용) -> 사용자별 한 개만 생성
	  	- 쿠키를 사용할 때보다 보안이 향상된다.(정보가 서버에 저장되기 때문에)
	  	
	  	- 세션 객체를 가져오는 방법
	  	HttpSession session = req.getSession(boolean값);
	  	
	  	boolean값 : true인 경우 => 세션 객체가 존재하지 않으면 새로 생성한다.
	  			  false인 경우 => 세션 객체가 존재하지 않으면 null을 리턴함 / 세션이 이미 만들어져 있는지 확인할 경우에 사용(굳이 만들지 않아도 돼!!)
	  				
	  	- 세션 삭제처리 방법
	  	1. invalidate() 메서드 호출
	  	2. setMaxInactiveInterval(int interval) 메서드 호출
	  		=> 일정시간(초)동안 요청이 없으면 세션객체 삭제됨
	  	3. web.xml에 <session-config> 설정하기 (분단위)
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 세션을 가져오는데 없으면 새로 생성한다. 생성 안 하면 false??
		HttpSession session = req.getSession(true); // ()비워두면 true
		
		// 생성시간 가져오기
		Date createTimeData = new Date(session.getCreationTime());
		
		// 마지막 접근시간 가져오기
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		
		String title = "재방문을 환영합니다.";
		int visitCnt = 0;				// 방문횟수
		String userId = "sem";	// 사용자 ID
		
		if (session.isNew()) { // 세션이 새로 만들어졌는지 확인
			title = "처음 방문을 환영합니다.";
			session.setAttribute("userId", userId);
		} else {
			visitCnt = (Integer) session.getAttribute("visitCnt");
			visitCnt++;
			userId = (String) session.getAttribute("userId");
		}

		session.setAttribute("visitCnt", visitCnt);
		
//		session.invalidate(); // 세션 삭제(무효화처리)
		session.setMaxInactiveInterval(30);
		
		/////////////////////////////////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<!DOC_TYPE html><html><head><title>"
				+ title + "</title></head><body>"
				+ "<h1 align=\"center\">" + title + "</h1>"
				+"<h2 align=\"center\">세션정보</h2>"
				+"<table border=\"1\" align=\"center\">"
				+ "<tr bgcolor=\"orange\"><th>구분</th><th>값</th></tr>"
				+ "<tr><td>세션ID</td><td>"
				+ session.getId() + "</td></tr>"
				+ "<tr><td>생성시간</td><td>"
				+ createTimeData + "</td></tr>"
				+ "<tr><td>마지막 접근시간</td><td>"
				+ lastAccessTime + "</td></tr>"
				+ "<tr><td>User ID</td><td>"
				+ userId + "</td></tr>"
				+ "<tr><td>방문 횟수</td><td>"
				+ visitCnt + "</td></tr>"
				+ "</table></body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
