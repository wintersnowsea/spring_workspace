package com.edu.springshop.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.springshop.admin.domain.Member;
import com.edu.springshop.exception.EmailException;
import com.edu.springshop.exception.HashException;
import com.edu.springshop.exception.MemberException;
import com.edu.springshop.model.member.MemberService;
import com.edu.springshop.sns.GoogleLogin;
import com.edu.springshop.util.Message;

@RestController
@RequestMapping("/rest")
public class RestMemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private GoogleLogin googleLogin;
	
	//회원가입 요청처리
	@PostMapping("/member")
	public ResponseEntity<Message> regist(HttpServletRequest request, Member member){
		//3단계
		memberService.regist(member);
		Message message = new Message();
		message.setMsg("회원가입 성공");
		
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		
		return entity;
	}
	
	/*
	//구글로그인 인증화면(사용자가 보게될) 요청처리
		@GetMapping("/member/authform/google")
		public ResponseEntity<Message> getUrl(HttpServletRequest request, Member member){
			//
			String url = googleLogin.handle();
			
			Message message = new Message();
			message.setMsg(url);
			
			ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.OK);
			
			return entity;
		}
	 */
	
	/*
	@ExceptionHandler(HashException.class)
	public ResponseEntity<Message> handle(HashException e){
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
	
	@ExceptionHandler(EmailException.class)
	public ResponseEntity<Message> handle(EmailException e){
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
	
	@ExceptionHandler(value = {MemberException.class, EmailException.class, })
	public ResponseEntity<Message> handle(MemberException e){
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
	*/
}
