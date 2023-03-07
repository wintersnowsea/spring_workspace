package com.edu.db.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
	/*
 	 ApplicationContext (스프링 컨테이너)
	 ServletContext (어플리케이션 내장객체의 자료형)
	 ServletConfig
	 (init()의 매개변수로써 초기화 파라미터인 init-param값을 가지고 있거나, ServletContext를 얻어올 때 사용되는 환경정보 객체 )
	 */
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//어플리케이션 내장객체 얻기
		ServletContext application = sce.getServletContext();
		String savePath = sce.getServletContext().getInitParameter("savePath");
		savePath = application.getRealPath(savePath);
		application.setAttribute("savePath", savePath);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
