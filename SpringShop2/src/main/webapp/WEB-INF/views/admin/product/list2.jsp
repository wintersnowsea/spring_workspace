<%@page import="com.edu.springshop.domain.Product"%>
<%@page import="com.edu.springshop.domain.Category"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Product> productList = (List)request.getAttribute("productList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Dashboard</title>
<%@ include file="../inc/header_link.jsp"%>
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
							<h1 class="m-0">상품목록</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">상품관리> 상품목록</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content" id="app1">
				<div class="container-fluid">
					<div class="row">
					    <div class="col-12">
					        <div class="card">
					            <div class="card-header">
					                <h3 class="card-title">Responsive Hover Table</h3>
					
					                <div class="card-tools">
					                    <div class="input-group input-group-sm" style="width: 150px;">
					                        <input type="text" name="table_search" class="form-control float-right" placeholder="Search">
					
					                        <div class="input-group-append">
					                            <button type="submit" class="btn btn-default">
					                                <i class="fas fa-search"></i>
					                            </button>
					                        </div>
					                    </div>
					                </div>
					            </div>
					            <!-- /.card-header -->
					            <div class="card-body table-responsive p-0">
					                <table class="table table-hover text-nowrap">
					                    <thead>
					                        <tr>
					                            <th>No</th>
					                            <th>카테고리</th>
					                            <th>제품명</th>
					                            <th>브랜드</th>
					                            <th>가격</th>
					                            <th>할인가격</th>
					                        </tr>
					                    </thead>
					                    <tbody>
					                        <template v-for="(product, i) in currentList">
					                        	<product :key="product.product_idx" :num="num-i" :obj="product"/>
					                        </template>
					                        <tr>
					                        	<td colspan="6" id="paging-area"></td>
					                        </tr>
					                    </tbody>
					                </table>
					            </div>
					            <!-- /.card-body -->
					        </div>
					        <!-- /.card -->
					    </div>
					</div>
				
				</div>
				<!-- /.container-fluid -->
			
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
	
	<script type="text/javascript" src="/resources/js/Pager.js"></script>
	<script type="text/javascript">
		let pager = new Pager();
		let currentPage=1;
		
		let app1;
		let key=0;
		
		const product={
			template:`
	            <tr>
	                <td>{{n}}</td>
	                <td>{{json.category.category_name}}</td>
	                <td><a href="#" v-on:click="getDetail()" >{{json.product_name}}</a></td>
	                <td>{{json.product_name}}</td>
	                <td><span class="tag tag-success">{{json.price}}</span></td>
	                <td>{{json.discount}}</td>
	            </tr>
			`,
			props:["obj", "num"],
			data(){
				return{
					json:this.obj,
					n:this.num
				};
			}
		};
		
		app1=new Vue({
			el:"#app1",
			components:{
				product
			},
			data:{
				count:5,
				productList:[],  //files(read only) 배열의 정보를  담아놓을 배열
				currentList:[], //현재 페이지에 보여질 배열
				num:0
			}
		});
		
		function pageLink(n){
			pager.init(app1.productList , n);
			
			console.log("pageSize is ", pager.pageSize);
			console.log("curPos is ", pager.curPos);
			console.log("num is ", pager.num);
			
			
			app1.num=pager.num;
			app1.currentList.splice(0, app1.currentList.length);
			
			for(let i=pager.curPos;i<pager.curPos+pager.pageSize; i++){
				
				if(pager.num<1){
					break;
				}
				pager.num--;
				
				app1.currentList.push(app1.productList[i]);				
			}
			
		}
		
		function getList(){
			$.ajax({
				url:"/admin/rest/product",
				type:"get",
				success:function(result, status, xhr){
					console.log(result);
					
					app1.productList=result;
					
					//페이징 처리
					pageLink(currentPage);
					
					//페이지 번호 출력
					$("#paging-area").append("<a href='#'>«</a>");
					for(let i=pager.firstPage;i<=pager.lastPage;i++){
						if(i >pager.totalPage)break;
						$("#paging-area").append("<a href='javascript:pageLink("+i+")' style='margin:3px'>"+i+"</a>");
					}
					$("#paging-area").append("<a href='#'>»</a>");
					
				}
			});	
		}
		
		//서머노트 적용하기
		$(function(){
			
			//등록 이벤트 연결
			$("#bt_regist").click(function(){
				regist();
			});
			
			getList();	
		});
	
	</script>
</body>
</html>
