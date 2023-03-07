package com.edu.springshop.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.edu.springshop.admin.domain.Category;
import com.edu.springshop.model.category.CategoryService;

/*
 * 쇼핑몰 어플리케이션에 전반적으로 적용될 수 있는 공통코드를 AOP의 advice로 정의해놓고
 * 필요한 곳에 적용시켜본다
 * */
public class CategoryAdvice {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private CategoryService categoryService;
	
	
	//아래의 메서드에서 서비스의 selectAll() 을 호출하여 ModelAndView에 저장하자
	public Object getCategoryList(ProceedingJoinPoint joinPoint) throws Throwable {
		//원래 호출하려던 메서드 호출 전, 후에 관여할 수 있는 기능을 지원
		Object returnObj = null;
		
		String target = joinPoint.getTarget().getClass().getName();
		logger.info("원래 호출하려던 객체는 target is "+target);
		
		Signature sig = joinPoint.getSignature();
		String method = sig.getName();
		logger.info("원래 호출하려던 메서드는 "+ method);
		
		//호출하려던 메서드의 매개변수에서 request 객체 가져오기
		Object[] args = joinPoint.getArgs();
		HttpServletRequest request = null;
		
		for(Object arg : args) {
			if(arg instanceof HttpServletRequest) {
				request = (HttpServletRequest)arg;
			}
		}
		
		String uri = request.getRequestURI();
		
		if(
			uri.equals("/rest/member") || //비동기방식의 가입요청은 메뉴 제외
			uri.equals("/member/regist") //동기방식의 가입요청은 메뉴 제외
		) { //제외될 요청 uri (카테고리처리가 필요없는 요청들)
			returnObj = joinPoint.proceed();
		}else {			
			//원래는 컨트롤러들에서 매번 수행해야 했던 Category 가져오기공통코드를
			//여기서 수행하자
			List categoryList = categoryService.selectAll();
			
			//원래 호출하려던 메서드를 진행시킨다
			ModelAndView mav = null;
			
			returnObj = joinPoint.proceed(); //원래 호출하려던 메서드 호출이 여기서 일어남
			if(returnObj instanceof ModelAndView) { //returnObj의 자료형이 ModelAndView라면..
				mav = (ModelAndView)returnObj; //넘겨받은 메서드에 카테고리 추가
				mav.addObject("categoryList", categoryList);
				returnObj=mav; //반환값에 mav 대입
			}
		}
		
		return returnObj;
		
	}
	
}
