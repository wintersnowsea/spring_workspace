<%@page import="com.edu.springboard.domain.ReBoard"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List reboardList = (List)request.getAttribute("reboardList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리 글등록</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script type="text/javascript">

$(function() {
	$("#bt_regist").click(function() {
		location.href="/reboard/registform";
	});
});
</script>
</head>
<body>
	<div class="container">
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>No</th>
					<th>Title</th>
					<th>Writer</th>
					<th>Regdate</th>
					<th>Hit</th>
				</tr>
			</thead>
			<tbody>
                <% for(int i=0;i<reboardList.size();i++){ %>
                <% ReBoard reboard = (ReBoard)reboardList.get(i); %>
                <tr>
                        <td><%= i %></td>
                        <td><a href="/reboard/detail?reboard_idx=<%= reboard.getReboard_idx()%>"><%= reboard.getTitle() %></a></td>
                        <td><%= reboard.getWriter() %></td>
                        <td><%= reboard.getRegdate() %></td>
                        <td><%= reboard.getHit() %></td>
                    </tr>
				<tr>
				<% } %>
					<td colspan="5">
  						<button type="button" class="btn btn-primary" id="bt_regist">글등록</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>