package com.edu.springshop.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.springshop.admin.domain.Category;
import com.edu.springshop.exception.CategoryException;
import com.edu.springshop.model.category.CategoryService;
import com.edu.springshop.util.Message;

@RestController
@RequestMapping("/rest")
public class RestCategoryController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CategoryService categoryService;
	
	
	//카테고리 등록하기
	@PostMapping("/category")
	public Message regist(HttpServletRequest request, Category category) {
		//3단계:
		categoryService.insert(category);
		Message message = new Message();
		message.setMsg("카테고리등록성공");
		
		return message;
	}
	
	//카테고리 리스트 불러오기
	@GetMapping("/category")
	public List<Category> getList(HttpServletRequest request){
		//3단계
		return categoryService.selectAll();
	}
	
	
	//수정요청 처리 
	//@ResponseBody   :  자바객체-->  JSON 
	//@RequestBody  :   JSON --> 자바객체
	@PutMapping("/category")
	public ResponseEntity<Message> edit(@RequestBody Category category, HttpServletRequest request){
		logger.info("category is "+category);
		//3단계: 
		categoryService.update(category);
		
		//결과 메시지 처리
		Message message = new Message();		
		message.setMsg("수정성공");
		ResponseEntity<Message> entity=null;
		entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		
		return entity;
	}
	
	
	//삭제요청 처리
	//파라미터가 평소( ?변수명=값)와는 틀리게 디렉토리의 일부로 전달된다..
	//따라서 스프링 측에서 경로에 포함된 파라미터를 변수로 인식할 필요가 있다.
	//이 문제를 가능하게 해 주는 코드가 경로에 변수 부분을 {변수} 표현
	//@PathVariable
	@DeleteMapping("/category/{category_idx}")		
	public ResponseEntity<Message> del(@PathVariable int category_idx){
		
		logger.info("넘겨받은 카테고리_idx" + category_idx);
		
		categoryService.delete(category_idx);
		//결과 메시지 처리
		Message message = new Message();		
		message.setMsg("삭제 성공");
		ResponseEntity<Message> entity=null;
		entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		
		return entity;
		
	}
	
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<Message> handle(CategoryException e) {
		//HTTP 응답정보를 보다 세밀하게 구성하고싶다면
		//HTTP 응답 메세지를 구성할 수 있는 객체를 지원함
		Message message = new Message();
		message.setMsg(e.getMessage());

		ResponseEntity<Message> entity = null;
		entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
}
