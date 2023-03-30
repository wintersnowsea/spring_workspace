<%@page import="com.edu.springshop.admin.domain.Product"%>
<%@page import="com.edu.springshop.admin.domain.Cart"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//장바구니를 세션으로 구현했으므로 세션에 들어있는 장바구니 관련객체를 끄집어 내서 표로 출력하자
	List<Cart> cartList=(List)request.getAttribute("cartList");
	
%>

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

	<!-- Shop Cart Section Begin -->
    <section class="shop-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shop__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            	<%
                            		int subtotal=0; //구매총액
                            		int point=1000; //사용가능 쿠폰 총액
                            		int totalpay=0; //결제할 금액
                            	%>
                            	<% for(Cart cart : cartList){ %>
                            	<% Product product = cart.getProduct(); %>
                            	<% subtotal+=product.getDiscount(); %>
                            	<% totalpay=subtotal-point; %>
                                <tr>
                                    <td class="cart__product__item">
                                        <img width="50px" src="/data/<%=product.getPimgList().get(0).getFilename() %>" alt="">
                                        <div class="cart__product__item__title">
                                            <h6><%= product.getProduct_name() %></h6>
                                            <div class="rating">
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="cart__price"><%=product.getDiscount() %></td>
                                    <td class="cart__quantity">
                                        <div class="pro-qty">
                                            <input type="text" value="<%=cart.getEa() %>">
                                        </div>
                                    </td>
                                    <td class="cart__total"><%= product.getDiscount() * cart.getEa() %></td>
                                    <td class="cart__close"><span class="icon_close" onClick="delCart()"></span></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn">
                        <a href="#">Continue Shopping</a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn update__btn">
                        <a href="#"><span class="icon_loading"></span> Update cart</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="discount__content">
                        <h6>Discount codes</h6>
                        <form action="#">
                            <input type="text" placeholder="Enter your coupon code">
                            <button type="submit" class="site-btn">Apply</button>
                        </form>
                    </div>
                </div>
                <div class="col-lg-4 offset-lg-2">
                    <div class="cart__total__procced">
                        <h6>Cart total</h6>
                        <ul>
                            <li>Subtotal <span><%= subtotal %></span></li>
                            <li>적용포인트 <span><%= point %></span></li>
                            <li>Total <span><%= totalpay %></span></li>
                        </ul>
                        <a href="javascript:showPayform()" class="primary-btn">Proceed to checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->



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
//결제관련
const clientKey = 'test_ck_kZLKGPx4M3MP2ykgOeE8BaWypv1o';
var tossPayments = TossPayments(clientKey) // 클라이언트 키로 초기화하기

function showPayform(){
	tossPayments.requestPayment('카드', { // 결제 수단
		  // 결제 정보
		  amount: 10000, //결제할 금액
		  orderId: 'mvKgqoCp-U5_QfwrGEElN', //주문번호 - 문자열로 임의로 만들기(주문을 구분하기 위함)
		  orderName: '토스 티셔츠 외 2건', //문자열 처리해야함
		  customerName: '박토스', //로그인 고객의 이름, 닉네임 등
		  //결제 성공이 아닌 결제요청 성공 시 받을 콜백 (cf:결제승인 과는 다름)
		  successUrl: 'http://localhost:7777/payment/callback/success', //결제요청이 성공되면 들어가지는 url
		  failUrl: 'http://localhost:7777/payment/callback/fail', //결제요청이 실패했을 때
		})
		.catch(function (error) {
		  if (error.code === 'USER_CANCEL') {
		    // 결제 고객이 결제창을 닫았을 때 에러 처리
		  } else if (error.code === 'INVALID_CARD_COMPANY') {
		    // 유효하지 않은 카드 코드에 대한 에러 처리
		  }
		})
}

function delCart() {
	//장바구니 삭제요청
	if(confirm("선택한 상품을 장바구니에서 삭제할까요?")){
		location.href="/payment/delcart.jsp";
	}
}
</script>
</body>

</html>