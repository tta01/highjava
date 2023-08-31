package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/member/update.do")
public class UpdateMemberController extends HttpServlet {
	
	// 기존 데이터를 조회하고, 수정할 부분 설정해주
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		
		IMemberService memService = MemberServiceImpl.getInstance();
		
		MemberVO mv = memService.getMember(memId);
		
		req.setAttribute("mv", mv);
		
		if(mv.getAtchFileId() > 0) {
			
			IAtchFileService fileService = AtchFileServiceImpl.getInstance();
			
			AtchFileVO atchFileVO = new AtchFileVO();
			atchFileVO.setAtchFileId(mv.getAtchFileId());
			
			
			List<AtchFileVO> fileList = fileService.getAtchFileList(atchFileVO);
			
			req.setAttribute("fileList", fileList);
			
		}
		
		req.getRequestDispatcher("/views/member/updateForm.jsp").forward(req, resp);
		
	}
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String memId = req.getParameter("memId");
			String memName = req.getParameter("memName");
			String memTel = req.getParameter("memTel");
			String memAddr = req.getParameter("memAddr");
			String atchFileId = req.getParameter("atchFileId");
			
			IAtchFileService fileService = AtchFileServiceImpl.getInstance();
			
			AtchFileVO atchFileVO = null; 
			
			try {
				
				atchFileVO = fileService.saveAtchFileList(req.getParts());
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			
			IMemberService memService = MemberServiceImpl.getInstance();
			MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
			
			if(atchFileVO == null) { // 새로운 첨부파일이 존재하지 않는 경우
				mv.setAtchFileId(Long.parseLong(atchFileId));
			} else {
				mv.setAtchFileId(atchFileVO.getAtchFileId());
			}
			
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
