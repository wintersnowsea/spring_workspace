package com.edu.springshop.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.springshop.admin.domain.Category;

//카테고리관련 컨트롤러
@Controller
public class CategoryController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//카테고리관리 메인 요청
	@GetMapping("/category/main")
	public ModelAndView getMain(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/category/category_main");
		return mav;
	}
	
	//수정요청 처리
	@PutMapping("/category/edit")
	public ModelAndView edit(HttpServletRequest request, Category category) {

		return null;
	}
}
