package com.edu.springshop.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.edu.springshop.exception.EmailException;
import com.edu.springshop.exception.HashException;
import com.edu.springshop.exception.MemberException;
import com.edu.springshop.util.Message;

//쇼핑몰과 관련된 글로벌(전역적) 예외객체 정의하되,
//jsp로 에러 결과를 보여줘야 하므로 반환값이 ModelAndView가 되어야 함
@ControllerAdvice(annotations = Controller.class)
public class ShopGlobalException {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@ExceptionHandler(HashException.class)
	public ModelAndView handle(HashException e) {
		ModelAndView mav = new ModelAndView("shop/error/result"); 
		mav.addObject("e",e);
		return mav;
	}

	@ExceptionHandler(EmailException.class)
	public ModelAndView handle(EmailException e) {
		ModelAndView mav = new ModelAndView("shop/error/result"); 
		mav.addObject("e",e);
		return mav;
	}
	
	@ExceptionHandler(MemberException.class)
	public ModelAndView handle(MemberException e) {
		ModelAndView mav = new ModelAndView("shop/error/result"); 
		mav.addObject("e",e);
		return mav;
	}
	
}
