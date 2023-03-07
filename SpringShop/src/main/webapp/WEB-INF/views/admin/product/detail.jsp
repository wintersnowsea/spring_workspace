<%@page import="com.edu.springshop.admin.domain.Product"%>
<%@page import="com.edu.springshop.admin.domain.Category"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Category> categoryList = (List)request.getAttribute("categoryList");
	Product product = (Product)request.getAttribute("product");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | 상품등록</title>
<%@ include file="../inc/header_link.jsp" %>

<style type="text/css">
	.box-style{
		width:90px;
		height:95px;
		border:1px solid #ccc;
		display:inline-block;
		margin:5px;
	}
	.box-style img{
		width:75px;
		height:70px;
	}
</style>

</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <!-- Preloader -->
	<%@ include file="../inc/preloader.jsp" %>

  <!-- Navbar -->
  	<%@ include file="../inc/navbar.jsp" %>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <%@ include file="../inc/sidebar_left.jsp" %>
 
   <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">상품등록</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">상품등록 v1</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content" id="app1">
       <div class="card card-info">
              <div class="card-header">
                <h3 class="card-title">상품등록 상세보기</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form>
                <div class="card-body">
	                <div class="form-group">
	                  <label>카테고리</label>
	                    <select class="form-control select2bs4 select2-hidden-accessible" style="width: 100%;" data-select2-id="17" tabindex="-1" aria-hidden="true" name="category_idx">
	                    <option selected="selected" value="0">카테고리선택</option>
						<% for(Category category : categoryList){ %>
						<option value="<%= category.getCategory_idx() %>"><%= category.getCategory_name() %></option>
						<% } %>
	                  </select>
	                </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">상품명</label>
                    <input type="text" class="form-control" value="<%=product.getProduct_name()%>" name="product_name">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">브랜드</label>
                    <input type="text" class="form-control" value="<%=product.getBrand() %>" name="brand">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">가격</label>
                    <input type="number" class="form-control"value="<%=product.getPrice() %>"name="price">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">할인가격</label>
                    <input type="number" class="form-control" value="<%=product.getDiscount()%>"name="discount">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputFile">파일</label>
						<input type="file" class="form-control" name="file" multiple>
                  </div>
                  
				<div class="form-group row">
                   <div class="col">
       					<template v-for="json in imageList">
       						<imagebox :key="json.key" :obj="json" />
                        </template>   
                   </div>
                 </div>
                 
                  <div class="form-group">
                    <label for="exampleInputPassword1">내용</label>
                    <textarea class="form-control" name="detail" id="detail"><%=product.getDetail() %></textarea>
                  </div>
                </div>
                <!-- /.card-body -->
                <div class="card-footer">
                  <button type="button" class="btn btn-info" id="bt_regist">등록</button>
                  <button type="button" class="btn btn-info" id="bt_list">목록</button>
                </div>
              </form>
            </div>
    </section>
   
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
  <%@ include file="../inc/footer.jsp" %>

  <!-- Control Sidebar -->
	<%@ include file="../inc/sidebar_right.jsp" %>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->
<%@ include file="../inc/footer_link.jsp" %>

<script type="text/javascript">
	let app1;
	let key = 0;
	
	const imagebox={
		template:` 
            <div class="box-style">
            	<div>X</div>
            	<img :src="json.binary" />
            </div>
		`,
		props:["obj"],
		data(){
			return{
				json:this.obj
			};
		}
	};
	
	app1=new Vue({
		el:"#app1",
		components:{
			imagebox
		},
		data:{
			count:5,
			imageList:[] //files(read only) 배열의 정보를 담아놓을 배열
		}
	});
	
	/*-----------------------------------
		중복된 이미지체크
	-------------------------------------*/
	function checkDuplicate(filename) {
		let count=0;
		
		for(let i=0; i<app1.imageList.length; i++){
			let json = app1.imageList[i];
			if(json.name==filename){ //중복발견
				count++;
				break;
			}
		}
		
		return count;
	}
	
	
	/*-----------------------------------
		미리보기
	-------------------------------------*/
	function preview(files){
		for(let i=0; i<files.length; i++){
			let file = files[i];
			
			//중복여부체크
			if(checkDuplicate(file.name) < 1){ //중복된 이미지 없을 때만
				let reader = new FileReader(); //스트림 생성
				reader.onload=(e)=>{
					
					key++; //사용자가 이미지를 선택할 때마다 1씩 증가하여 중복을 불허한다
					let json=[]; //imageList배열에 복합적인 정보를 담아놓기 위해
					json['key']=key; //추후 이미지 삭제시 기준값으로 사용
					json['name']=file.name; //중복이미지가 추가되지 않도록
					json['binary']=e.target.result; //src에 대입할 바이너리 정보
					json['file']=file; //전송할때 파라미터에 심을 파일
					
					app1.imageList.push(json);
				};
				reader.readAsDataURL(file); //파일읽기
			}
		}
	}
	
	
	/*-----------------------------------
		등록
	-------------------------------------*/
	function regist() {
		//파일업로드를 커스터마이징 시켰기 때문에
		let formData = new FormData();
		
		formData.append("category.category_idx", $("select[name='category_idx']").val());
		formData.append("product_name", $("input[name='product_name']").val());
		formData.append("brand", $("input[name='brand']").val());
		formData.append("price", $("input[name='price']").val());
		formData.append("discount", $("input[name='discount']").val());
		formData.append("detail", $("textarea[name='detail']").val());
		
		//선택한 이미지 수만큼 formData에 추가
		for(let i=0; i<app1.imageList.length; i++){
			let json = app1.imageList[i];
			formData.append("photo", json.file); 		//반복문 배열이기 떄문에, product 에서 멀티파일 []배열로 받은것.
		}
		
		$.ajax({
			url:"/admin/rest/product",
			type:"post",
			data:formData,
			processData:false, /*query string 사용여부*/
			contentType:false, /*application/x-www-form~ 사용여부*/
			success:function(result, status, xhr){
				console.log(result);
			}
		});
		
	}
	
	$(function(){
		//서머노트 적용하기
		$("#detail").summernote({
			height:300
		});
		
		//파일에 이벤트 연결
		$("input[name='file']").change(function(){
			console.log(this.files);
			preview(this.files);
		});
		
		//등록 이벤트 연결
		$("#bt_regist").click(function(){
			regist();
		});
		
		//목록 이벤트 연결
		$("#bt_list").click(function(){
			location.href="/admin/product/list";
		});
		
		
	});

</script>
</body>
</html>
