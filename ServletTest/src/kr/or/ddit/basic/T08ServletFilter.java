package kr.or.ddit.basic;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class T08ServletFilter implements Filter {
	/*
	서블릿 필터에 대하여
	
	1. 사용목적
	- 클라이언트의 요청을 수행하기 전에 가로채 필요한 작업을 수행할 수 있다.
	- 클라이언트에 응답정보를 제공하기 전에 응답정보에 필요한 작업을 수행할 수 있다.
	
	2. 사용 예
	- 인증필터 : 로그인 => 로그인 했으면 화면으로 넘겨주고 / 로그인 안 했으면 로그인 or 가입 페이지로 넘겨주는거
	- 데이터 압축필터
	- 인코딩 필터 : encoding 매번 req, resp에 안 써도 되게 끔!!
	- 로깅 및 감사처리 필터
	- 이미지 변환 필터 등.
	
	 */

	@Override
	public void destroy() {
		System.out.println("T08ServletFilter => destory() 호출됨");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("T08ServletFilter : doFilter() 시작...");
		
		// 클라이언트의 IP주소 가져오기
		String ipAddr = req.getRemoteAddr();
		
		System.out.println("IP주소 : " + ipAddr + "\n포트번호 : "
							+ req.getRemotePort() + "\n현재 시간 : "
							+ new Date());
		
		//필터체인을 실행한다. (요청 및 응답객체 전달)
		chain.doFilter(req,resp); // 요청하던 것을 보내려면 체인을 실행해야 함..?
		
		System.out.println("T08ServletFilter : doFilter() 끝...");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("T08ServletFilter => init() 호출됨");
		
		// 초기화 파라미터정보 가져오기
		String initParam = filterConfig.getInitParameter("init-param");
		
		System.out.println("init-param : " + initParam);
		
	}
	
}
