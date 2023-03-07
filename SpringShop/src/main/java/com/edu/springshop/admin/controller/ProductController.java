package com.edu.springshop.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.springshop.admin.domain.Product;
import com.edu.springshop.model.category.CategoryService;
import com.edu.springshop.model.product.ProductService;

//상품과 관련된 요청을 처리하는 하위 컨트롤러
@Controller
public class ProductController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	//글쓰기폼으로 요청
	@GetMapping("/product/registform")
	public ModelAndView getForm(HttpServletRequest request) {
		
		//3단계
		List categoryList = categoryService.selectAll();
		
		//4단계
		ModelAndView mav = new ModelAndView("admin/product/regist");
		mav.addObject("categoryList", categoryList);
		
		return mav;
	}
	
	//목록으로 가는
	@GetMapping("/product/list")
	public ModelAndView getList(HttpServletRequest request) {
	
		//3단계 일시키기 
		List productList= productService.selectAll();
		
		//4단계 : jsp로 가져가야 하니까 결과 저장
		ModelAndView mav = new ModelAndView("admin/product/list");
		mav.addObject("productList", productList);
		
		return mav;
	}
	
	//상세보기 요청
	@GetMapping("/product/detail")
	public ModelAndView getDetail(HttpServletRequest request, int product_idx) {
		//3단계 -카테고리 목록 가져오기
		List categoryList = categoryService.selectAll();
		//프로덕트도 가져오기 
		Product product =  productService.select(product_idx);
		
		ModelAndView mav = new ModelAndView("admin/product/detail");
		mav.addObject("categoryList", categoryList);
		mav.addObject("product", product);

		return mav;
	}
	
	
}
