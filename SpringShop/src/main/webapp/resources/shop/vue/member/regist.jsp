<%@page import="com.google.gson.Gson"%>
<%@page import="com.jspshop.util.MessageObject"%>
<%@page import="com.jspshop.exception.MemberException"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.jspshop.mybatis.MybatisConfig"%>
<%@page import="com.jspshop.repository.MemberDAO"%>
<%@ page contentType="application/json;charset=UTF-8"%>
<%!
	MybatisConfig mybatisConfig = MybatisConfig.getInstance();
	MemberDAO memberDAO = new MemberDAO();
%>
<%
	//날라오는 파라미터를 insert하는 게 목적!
	//dao에게 주입(injection)
	SqlSession sqlSession = mybatisConfig.getSqlSession();
	memberDAO.setSqlSession(sqlSession);
	
	//파라미터 받기
	request.setCharacterEncoding("utf-8"); //파라미터받기전에 한글깨지지않도록 설정해야함
%>
<jsp:useBean id="member" class="com.jspshop.domain.Member" />
<jsp:setProperty property="*" name="member"/>
<%
	System.out.println(member.getId());
	System.out.println(member.getPass());
	System.out.println(member.getName());
	System.out.println(member.getEmail());
	
	MessageObject mo = new MessageObject();
	try{	
		memberDAO.insert(member);
		mo.setCode(1);
		mo.setMsg("등록성공");
		sqlSession.commit();
	}catch(MemberException e){
		mo.setCode(0);
		mo.setMsg(e.getMessage()); //에러메세지자동대입
	}
	Gson gson = new Gson();
	out.print(gson.toJson(mo)); //JSON 문자열로 변환하여 결과 전송
	
	mybatisConfig.release(sqlSession);
%>