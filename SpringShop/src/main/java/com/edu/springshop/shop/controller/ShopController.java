package com.edu.springshop.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.springshop.admin.domain.Member;
import com.edu.springshop.admin.domain.Product;
import com.edu.springshop.model.product.CartService;
import com.edu.springshop.model.product.ProductService;

//상품 리스트부터 구매단계까지의 쇼핑 전반적인 요청을 처리할 하위 컨트롤러
@Controller
public class ShopController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;
	
	//상품리스트 페이지 요청
	@GetMapping("/shop/list")
	public ModelAndView getList(HttpServletRequest request) {
		//3단계
		List productList = productService.selectAll();
		
		//4단계
		ModelAndView mav = new ModelAndView();
		mav.addObject("productList", productList);
		mav.setViewName("shop/shop");
		
		return mav;
	}
	
	
	//상품상세 요청처리
	@GetMapping("/shop/detail")
	public ModelAndView getDetail(HttpServletRequest request, int product_idx) {
		//3단계
		Product product = productService.select(product_idx);
		
		//4단계
		ModelAndView mav = new ModelAndView("shop/shop_detail");
		mav.addObject("product", product);
		
		return mav;
	}
	
	//장바구니 추가
	@GetMapping("/shop/cart")
	public ModelAndView getCartList(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		
		List cartList = cartService.selectAll(member); //어떤회원의 목록인지...
		
		ModelAndView mav = new ModelAndView("shop/payment/cartlist");
		mav.addObject("cartList", cartList);
		
		
		return mav;
	}
	
	//컨트롤러 메서드의 반환형은 오직 ModelAndView만 지원되는 것이 아니라
	//String형도 지원한다
	@GetMapping("/shop/test")
	public String test() {
		return "shop/test_result"; //ModelAndView에서 Model을 제외한 View
	}
	
}
