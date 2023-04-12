<%@page import="com.jspshop.mybatis.MybatisConfig"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.jspshop.domain.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.jspshop.repository.ProductDAO"%>
<%@ page contentType="application/json;charset=UTF-8"%>
<%!
	MybatisConfig mybatisConfig =MybatisConfig.getInstance();
	ProductDAO productDAO  = new ProductDAO();
%>
<%
	productDAO.setSqlSession(mybatisConfig.getSqlSession());

	String category_idx = request.getParameter("category_idx");
	List<Product> productList=productDAO.selectByCategory(Integer.parseInt(category_idx));
	
	Gson gson = new Gson();
	String jsonList = gson.toJson(productList);
	
	out.print(jsonList);
%>