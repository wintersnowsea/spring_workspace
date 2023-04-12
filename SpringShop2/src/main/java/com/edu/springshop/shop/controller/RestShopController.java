package com.edu.springshop.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.springshop.domain.Cart;
import com.edu.springshop.domain.Member;
import com.edu.springshop.model.product.CartService;
import com.edu.springshop.util.Message;

@RestController
@RequestMapping("/rest")
public class RestShopController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CartService cartService;
	
	//장바구니 담기 ( 클라이언트의  json 을 받기 )
	@PostMapping("/shop/cart")
	public ResponseEntity addCart(HttpServletRequest request, @RequestBody Cart cart) {
		logger.info("클라이언트가 보낸  json을 결과는 cart 로 받은 결과" + cart);
		
		Message message = new Message();
		message.setMsg("장바구니에 상품이 담겼습니다");
		
		//누가 담았는지에 대한 정보를  cart 에 심자 
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		
		cart.setMember(member);
		
		cartService.insert(cart);
		
		ResponseEntity<Message> entity = ResponseEntity.ok(message);
		return entity;
	}
}










