package kr.or.ddit.basic;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/*
 *  톰켓이 리스너를 듣고 있음
 *  따로 생성해주면
 *  듣고 있다가 특정 이벤트 발생시 자동으로 실행시킴 
 */
public class MyHttpSessionListener implements HttpSessionListener, HttpSessionAttributeListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
 		System.out.println("[MyHttpSessionListener] sessionCreated 호출됨 ");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("[MyHttpSessionListener] sessionDestroyed 호출됨");
		
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("[MyHttpSessionListener] attributeAdded 호출됨 => "
							+ event.getName() + " : " + event.getValue() );		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("[MyHttpSessionListener] attributeRemoved 호출됨 =>"
				+ event.getName() + " : " + event.getValue() );		
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("[MyHttpSessionListener] attributeReplaced 호출됨 =>"
				+ event.getName() + " : " + event.getValue() );		
		
	}
	

}
