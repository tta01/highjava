package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@MultipartConfig
@WebServlet(value = "/member/insert.do")
// url 주소!!!!!! 이걸로 치면 됨
public class InsertMemberController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/views/member/insertForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVO atchFileVO = null;
		try {
			atchFileVO = fileService.saveAtchFileList(req.getParts()); // 인서트가 잘 되면 값이 들어가고 인서트 값이 없으면 설정했던 -1이 들어감
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		IMemberService memService = MemberServiceImpl.getInstance();
		MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
		if(atchFileVO != null) {
			mv.setAtchFileId(atchFileVO.getAtchFileId());
		}
		
		int cnt = memService.registMember(mv);
		
		String msg= "";
		
		if(cnt > 0 ) {
			msg = "성공";
		} else {
			msg = "실패";
		}
		
		HttpSession session = req.getSession();
		
		session.setAttribute("msg", msg);
		
//		req.setAttribute("msg", msg);
//		req.getRequestDispatcher("/member/list.do").forward(req, resp);
		
		resp.sendRedirect(req.getContextPath() + "/member/list.do");
// jsp가 가지고 있는 request는 insert의 request와 다름. 새로운 request객체라서 js에서 설정해준 alert이 뜨지 않는 것
// 그래서 session객체에 넣어줌 
	}
}
