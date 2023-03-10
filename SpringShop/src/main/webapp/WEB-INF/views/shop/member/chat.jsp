<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html lang="zxx">

<head>
	<%@ include file="../inc/header.jsp" %>
</head>

<body>
<!-- Page Preloder -->
<%@ include file="../inc/preloader.jsp" %>

<!-- Offcanvas Menu Begin -->
<!-- 
	jsp자체에서 지원하는 태그
	why? 사실 jsp는 디자인영역이므로, 개발자만 사용하는 것이 아니라 퍼블리셔나 웹디자이너와 공유를 한다
	이때 java에 대한 비전문가들은 java코드를 이해할 수 없기 때문에 그들이 좀 더 쉽게 다가갈 수 있도록
	태그를 지원해준다 (협업때문에)
 -->
<%@ include file="../inc/main_navi.jsp" %>
<!-- Offcanvas Menu End -->

<!-- Header Section Begin -->
<%@ include file="../inc/header_section.jsp" %>
<!-- Header Section End -->


<section>
	<div class="container">
		<div class="row mt-3">
			<div class="col">
				<form id="form1">
					<h2>상담</h2>
					<div class="form-group mt-3">
						<label for="t_send">상담내용:</label>
						<input type="text"	class="form-control" placeholder="상담하실 메세지를 입력하세요..." id="t_send">
					</div>
					<div class="form-group">
						<label for="t_receive">답변:</label>
						<textarea	class="form-control" id="t_receive"></textarea>
					</div>

					<button type="button" class="btn btn-primary" id="bt_send">Send</button>
				</form>
			</div>
		</div>
	</div>
</section>

<!-- Instagram Begin -->
<%@ include file="../inc/insta.jsp" %>
<!-- Instagram End -->

<!-- Footer Section Begin -->
<%@ include file="../inc/footer.jsp" %>
<!-- Footer Section End -->

<!-- Search Begin -->
<%@ include file="../inc/search.jsp" %>
<!-- Search End -->

<!-- Js Plugins -->
<%@ include file="../inc/footer_link.jsp" %>

<script type="text/javascript">
let ws;
//웹소켓을 이용한 서버에 접속
function connect() {
	ws = new WebSocket("ws://localhost:7777/chat");
	
	ws.onopen=function(){
		console.log("서버에 접속된 onopen");
	}
	
	ws.onmessage=function(e){
		console.log("서버가 보낸 데이터", e.data);
		
		//서버가 보낸 메세지를 area에 누적
		$("#t_receive").append(e.data+"\n");
	}
	
	ws.onclose=function(e){
		console.log("서버와 접속이 끊김");
		//끊기는 시점을 발견할 때, 1초의 시간 뒤에 다시 재접속하여 프로그램의 안정성을 높이자
		setTimeout("connect",1000);
	}  
	
	ws.onerror=function(){
		console.log("에러발생", e);
	}
	
}

//서버에 메세지 전송하기
function sendMsg(){
	let msg = $("#t_send").val();
	ws.send(msg);
	$("#t_send").val(""); //입력값 초기화
}

$(function(){
	connect();
	
	$("#bt_send").click(function(){
		sendMsg();
	});	
});
</script>
</body>

</html>