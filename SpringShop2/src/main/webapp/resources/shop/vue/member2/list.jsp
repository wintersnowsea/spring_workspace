<%@page import="java.util.List"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.jspshop.util.MessageObject"%>
<%@page import="com.jspshop.exception.MemberException"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.jspshop.mybatis.MybatisConfig"%>
<%@page import="com.jspshop.repository.MemberDAO"%>
<%@page import="com.jspshop.domain.Member"%>
<%@ page contentType="application/json;charset=UTF-8"%>
<%!
	MybatisConfig mybatisConfig = MybatisConfig.getInstance(); 
	MemberDAO memberDAO = new MemberDAO();
%>
<%
	SqlSession sqlSession  = mybatisConfig.getSqlSession();
	memberDAO.setSqlSession(sqlSession);
	
	List<Member> memberList = memberDAO.selectAll();
	
	mybatisConfig.release(sqlSession);
	Gson gson = new Gson();
	
	out.print(gson.toJson(memberList));
%>