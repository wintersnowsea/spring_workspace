package com.edu.mvc2.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//연습용 서블릿
public class FilterServlet extends HttpServlet{

	//게시판의 폼으로부터 전송된 파라미터들의 깨짐 여부 테스트
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title=req.getParameter("title");
		System.out.println("넘어온 제목은 "+title);
	}
}
