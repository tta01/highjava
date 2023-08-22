package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿을 이용한 파라미터 데이터 처리 예제
 */
public class T03ServletParameterTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); // encoding처리 요청과 응답 둘 다 해줘야 함!!!

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String hobby = req.getParameter("hobby");

		String rlgn = req.getParameter("rlgn");
		String[] foods = req.getParameterValues("foods");

		//////////////////////////////////////////////////////////////

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/HTML"); // contentType은 꼭 써야 함

		PrintWriter out = resp.getWriter();

		out.println("<html><body>");
		out.println("<p>username : " + username + "</p>");
		out.println("<p>password : " + password + "</p>");
		out.println("<p>gender : " + gender + "</p>");
		out.println("<p>hobby : " + hobby + "</p>");
		out.println("<p>rlgn : " + rlgn + "</p>"); // 종교

		if (foods != null) {
			for (String food : foods) {
				out.println("<p>foods : " + food + "</p>");
			}
		}

		Enumeration<String> params = req.getParameterNames();

		while (params.hasMoreElements()) {
			String param = params.nextElement();
			out.println("<p> 파라미터 이름 : " + param + "</p>");
		}

		out.println("</body></html>");

	}

}
