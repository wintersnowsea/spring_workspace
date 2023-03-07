<%@page import="com.edu.db.domain.Board"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Board board = (Board)request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
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
		
		$("#bt_edit").click(function() {
			if(confirm("수정하시겠어요?")){
				$("#form1").attr({
					action:"/board/edit",
					method:"post"
				});
				$("#form1").submit();
			}
		});
		
		$("#bt_del").click(function() {
			if(confirm("삭제하시겠어요?")){
				$("#form1").attr({
					action:"/board/delete",
					method:"post"
				});
				$("#form1").submit();
			}
		});
			
		
		
		$("#bt_list").click(function() {
			location.href="/board/list";
		});
	});
</script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<h2>상세보기</h2>
				<form id="form1">
					<input type="hidden" value="<%= board.getBoard_idx()%>" name="board_idx">
					<div class="form-group">
						<label for="title">Title:</label>
						<input type="text" class="form-control" value="<%= board.getTitle()%>" name="title">
					</div>
					<div class="form-group">
						<label for="writer">Writer:</label>
						<input type="text" class="form-control" value="<%= board.getWriter() %>" name="writer">
					</div>
					<div class="form-group">
						<label for="content">Content:</label>
						<textarea class="form-control" rows="5" name="content"><%= board.getContent() %></textarea>
					</div>
					<button type="button" class="btn btn-primary" id="bt_edit">수정</button>
					<button type="button" class="btn btn-primary" id="bt_del">삭제</button>
					<button type="button" class="btn btn-primary" id="bt_list">목록</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>