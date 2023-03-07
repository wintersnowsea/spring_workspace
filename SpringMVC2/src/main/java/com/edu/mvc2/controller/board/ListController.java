package com.edu.mvc2.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.edu.mvc2.model.board.BoardService;

import lombok.Setter;

//게시판의 목록요청 처리하는 하위 컨트롤러
//2. x 방식
@Setter
public class ListController implements Controller{
	Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	private BoardService boardService;
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("게시판 목록을 처리할게요");
		
		//3단계) 일시키기
		List boardList = boardService.selectAll();
		
		//4단계) 저장, 원래는 request에 저장해야 하지만 ModelAndView 에 저장하면
		//request 자동으로 저장하도록 처리되어 있다
		ModelAndView mav = new ModelAndView(); //변경가능성이 없는 아이들은 new해도 상관없음!
		//mav.setViewName("/WEB-INF/views/board/list.jsp"); //Resolver를 이용해 앞뒤를 날려버리자!
		mav.addObject("boardList", boardList); //저장
		mav.setViewName("board/list"); //forwarding
		
		
		return mav;
	}

}
