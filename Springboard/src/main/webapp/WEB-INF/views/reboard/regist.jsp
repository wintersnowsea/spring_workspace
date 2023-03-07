<%@ page contentType="text/html; charset=UTF-8"%>
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
	
	function regist() {
		$("#form1").attr({
			action:"/reboard/regist",
			method:"post"
		});
		$("#form1").submit();
	}
	
	$(function() {
		$("#bt_regist").click(function() {
			regist();
		});
		
		$("#bt_list").click(function() {
			location.href="/reboard/list";
		});
	});
</script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<h2>글쓰기</h2>
				<form id="form1">
					<div class="form-group">
						<label for="title">Title:</label>
						<input type="text" class="form-control" id="title" placeholder="Enter title" name="title">
					</div>
					<div class="form-group">
						<label for="writer">Writer:</label>
						<input type="text" class="form-control" id="writer" placeholder="Enter writer" name="writer">
					</div>
					<div class="form-group">
						<label for="content">Content:</label>
						<textarea class="form-control" rows="5" id="content" name="content"></textarea>
					</div>
					<button type="button" class="btn btn-primary" id="bt_regist">등록</button>
					<button type="button" class="btn btn-primary" id="bt_list">목록</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>