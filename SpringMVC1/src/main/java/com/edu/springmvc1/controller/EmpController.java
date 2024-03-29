package com.edu.springmvc1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springmvc1.model.emp.EmpService;

import lombok.Setter;

/* '@' 어노테이션 :jdk 5 부터 지원되는 주석 중 하나로 우리가 알고 있는 일반적인 주석과는 달리
					프로그램에서 사용되는 주석 */
@Controller
@Setter
public class EmpController {
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	//상위 자료형을 보유해야(DI를 구현해야) 유지보수성이 높아지고 결합도가 낮아짐
	//@Autowired : 자동 엮음 기능에 의해 empService의 인스턴스가 자동 주입된다
	 //스프링이 자동으로 넣어줌
	@Autowired
	private EmpService empService;
	
	
	//CRUD를 이 클래스에서 모두 처리할 수 있다
	//ListController, DetailController... 등등 따로 만들 필요 없다
	
	//이 메서드가 어떤 uri를 처리할 지 uri 매핑
	@RequestMapping("/emps")
	public String getList(Model model) { //model : 데이터를 저장할 수 있는 객체	
		logger.info("게시판 목록을 처리할 예정");
		
		//3단계) 알맞는 객체에 일 시키기(서비스에게 시키면 됨)
		List empList = empService.selectAll();
		
		//4단계) 결과저장
		//1) 직접 request 객체를 이용하는 방법
		//2) 간접적으로 request 객체를 이용하는 방법
		model.addAttribute("empList", empList);
		
		
		//개발자가 redirect를 명시하지 않으면 디폴트가 forward이다
		
		return "emp/list";
	}
	
	
}
