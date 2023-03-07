<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
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

<!-- Vue -->
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

<script type="text/javascript">
let app1;
const row={
	template:`
		<tr>
			<td>{{obj.notice_idx}}</td>
			<td @click="getDetail(obj.notice_idx)"><a href="#">{{obj.title}}</a></td>
			<td>{{obj.writer}}</td>
			<td>{{obj.regdate}}</td>
			<td>{{obj.hit}}</td>
		</tr>
	`,
	props:['notice'],
	data(){
		return {
			obj:this.notice
		};
	},
	methods:{
		getDetail:function(notice_idx){
			//alert(notice_idx);
			location.href="/notice/detail?notice_idx="+notice_idx;
		}
	}
};

function init() {
	app1 = new Vue({
		el:"#app1",
		components:{
			row
		},
		data:{
			noticeList:[]
		}
	});	
}

//목록가져오기
function getList() {
	$.ajax({
		url:"/rest/notices",
		type:"get",
		success:function(result, status, xhr){
			console.log("서버에서 전송된 결과 : ", result);
			app1.noticeList=result;
		}
	});
}

$(function() {
	init();
	getList();
	
	$("#bt_regist").click(function() {
		location.href="/notice/registform";
	});
});
</script>
</head>
<body>
	<div class="container" id="app1">
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
				<!-- template : 디자인에 관여안함 -->
				<template v-for="notice in noticeList">
	                <row :key="notice.notice_idx" :notice="notice"/>
				</template>
				
				<tr>
					<td colspan="5">
  						<button type="button" class="btn btn-primary" id="bt_regist">글등록</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>