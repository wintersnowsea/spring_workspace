package com.edu.springshop.admin.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.jni.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edu.springshop.admin.domain.Product;
import com.edu.springshop.exception.PimgException;
import com.edu.springshop.exception.ProductException;
import com.edu.springshop.exception.UploadException;
import com.edu.springshop.model.product.ProductService;
import com.edu.springshop.util.Message;

//상품과 관련된 요청을 처리하는 하위 컨트롤러
@RestController
@RequestMapping("/rest")
public class RestProductController {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	// t서비스 존재가 없다면 컨트롤러 계층과 모델 계층의 구분이 모호해진다..
	//추후 모델을 완전히 재사용을 위해 ㅇ분리시키려고 할때 분리가 불가능
	@Autowired
	private ProductService productService;
	
	//상품등록 요청처리
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public ResponseEntity<Message> regist(Product product, HttpServletRequest request) {
		logger.info("product is "+product);
		
		//웹 환경과 관련된 코드이므로 컨트롤러의 책임이다!
		//왜?? 모델은 중립적이니까 관심이 없다.
		ServletContext application= request.getSession().getServletContext(); 		
		String path = application.getRealPath("/resources/data/");
		logger.info("저장될 실제 경로는 "+path);
		
		//3단계
		productService.regist(product, path);
		
		Message message = new Message();
		message.setMsg("상품 등록성공");
		
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.OK); 
		return entity;
	}
	
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<String> handle(ProductException e){
		
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
	
	@ExceptionHandler(UploadException.class)
	public ResponseEntity<String> handle(UploadException e){
		
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
	
	@ExceptionHandler(PimgException.class)
	public ResponseEntity<String> handle(PimgException e){
		
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		ResponseEntity entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
	
}
