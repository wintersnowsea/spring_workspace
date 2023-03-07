<%@page import="com.google.gson.Gson"%>
<%@page import="com.jspshop.domain.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.jspshop.repository.CategoryDAO"%>
<%@ page contentType="application/json;charset=UTF-8"%>
<%!
	CategoryDAO categoryDAO=new CategoryDAO();
%>
<%
	//모든카테고리 가져오기
	 List<Category> categoryList=categoryDAO.selectAll();
	Gson gson=new Gson();
	String jsonList=gson.toJson(categoryList);
	out.print(jsonList);
%>