package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/update.do")
public class UpdateMemberController extends HttpServlet {
	
	// 기존 데이터를 조회하고, 수정할 부분 설정해주
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		
		IMemberService memService = MemberServiceImpl.getInstance();
		
		MemberVO mv = memService.getMember(memId);
		
		req.setAttribute("mv", mv);
		
		req.getRequestDispatcher("/views/member/updateForm.jsp").forward(req, resp);
		
	}
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String memId = req.getParameter("memId");
			String memName = req.getParameter("memName");
			String memTel = req.getParameter("memTel");
			String memAddr = req.getParameter("memAddr");
			
			IMemberService memService = MemberServiceImpl.getInstance();
			MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
			
			int cnt = memService.modifyMember(mv);
			
			String msg= "";
			
			if(cnt > 0 ) {
				msg = "성공";
			} else {
				msg = "실패";
			}
			
			HttpSession session = req.getSession();
			
			session.setAttribute("msg", msg);
			
			resp.sendRedirect(req.getContextPath() + "/member/list.do");
		
		}
		
	}
