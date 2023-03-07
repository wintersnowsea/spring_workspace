package com.edu.springshop.shop.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.springshop.school.Student;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//학생을 주입받자
	@Autowired
	private Student student;
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	@RequestMapping(value = "/aop", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		//학생을 동작시키자
		student.study();
		student.haveLunch();
		student.sleep();
		
		return null;
	}
	
}
  