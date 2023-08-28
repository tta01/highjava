package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet(value = "/member/list.do")
public class ListMemberController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		IMemberService memService = MemberServiceImpl.getInstance();
		List<MemberVO> memList = memService.selectAll();
		
		req.setAttribute("memList", memList);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/member/list.jsp"); // 여기 주소는 정확하게 써줘야 해
		// forward - 아직 전달 전
		dispatcher.forward(req, resp); // forward를 호출해 두개의 객체를 위의 jsp주소로 보내서 처리되는 것
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
