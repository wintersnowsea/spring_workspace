package com.edu.springshop.shop.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	//결제 요청 성공시 호출되는 콜백메서드 정의 
	@GetMapping("/payment/callback/success")
	public ModelAndView paymentCallback(HttpServletRequest request, String paymentKey, String orderId, int amount) {
		logger.info("결제요청 성공");
		
		//일치하는 경우
		/*
		if(내가요청한 금액과 == 결제요청성공시 날아온 금액){
			
		}
		*/
		
		//결제승인 요청!!!(post로 승인을 요청해야 한다..)
		HttpRequest req = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
			    .header("Authorization", "Basic  본인꺼적기")
			    .header("Content-Type", "application/json")
			    .method("POST", HttpRequest.BodyPublishers.ofString("{\"paymentKey\":\""+paymentKey+"\",\"amount\":"+amount+",\"orderId\":\""+orderId+"\"}"))
			    .build();
			try {
				HttpResponse<String> response = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
				logger.info("결제승인 요청 결과 : "+response.body());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		
			//1)주문요약  ordersummary -  pk를 selectkey로 얻어와 orderdetail에 적용해야 함
			 
			//2) 주문상세 orderdetail (2건 이상일 경우 반복문으로 - 트랜잭션 걸어서... )
			//    장바구니에 들어있는 상품 만큼 반복문 돌린다..
			
			//3) 장바구니 테이블 비우기 (delete)
			
			//4) 이메일전송, 문자전송, 카톡메시지 전송...
			
			//5) 결제결과
			
		return null; //결제 결과 페이지 이동(우리가 디자인 한 페이지..)
	}
}












