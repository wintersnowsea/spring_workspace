package com.edu.db.client.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.db.domain.Board;
import com.edu.db.exception.BoardException;
import com.edu.db.model.board.BoardService;

//컴포넌트 스캔의 대상이 될때는 어노테이션을 붙이면 된다
@Controller
public class BoardController {
	Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private BoardService boardService;
	
	//글쓰기 폼 요청처리
	@RequestMapping(value="/board/registform", method=RequestMethod.GET)
	public String registForm() {
		return "board/regist";
	}
	
	//글쓰기 요청 처리
	@RequestMapping(value="/board/regist", method=RequestMethod.POST)
	public ModelAndView regist(Board board) {
		logger.info("글쓰기 요청받음_logger");
		
		boardService.insert(board); //BoardException 이 발생될 수 있음
		ModelAndView mav = new ModelAndView("redirect:/board/list");
		return mav;
	}
	
	//목록 요청처리
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public String getList(Model model) {
		
		//3단계 일시키기
		List boardList = boardService.selectAll();
		
		//4단계 저장하기
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
	//상세보기 요청 처리
	@RequestMapping(value="/board/detail", method=RequestMethod.GET)
	public ModelAndView getDetail(int board_idx) {
		
		//3단계 : 일시키기
		Board board = boardService.select(board_idx);
		
		//4단계 : 저장하기
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board); //결과 저장 : model 처리
		mav.setViewName("board/detail"); //view 처리
		
		return mav;
	}
	
	//수정 요청 처리
	@RequestMapping(value="/board/edit", method=RequestMethod.POST)
	public ModelAndView edit(Board board) {
		
		//3단계
		boardService.update(board);
		
		//4단계 없음
		//상세보기 재접속하면 됨 
		ModelAndView mav = new ModelAndView("redirect:/board/detail?board_idx="+board.getBoard_idx());
		
		return mav;
	}
	
	//삭제요청 처리
	@RequestMapping(value="/board/delete", method=RequestMethod.POST)
	public String del(int board_idx) {
		//3단계
		boardService.delete(board_idx);
		
		//4단계 없음
		
		return "redirect:/board/list";
	}
	
	//현재 컨트롤러 클래스 내에서 발생외는 예외 중,
	//@ExceptionHandler 에 명시된 몌외의 자료형이 발견되면 아래의 지정된 메서드가 수행됨
	@ExceptionHandler(BoardException.class)
	public ModelAndView handle(BoardException e) {
		//에러정보를 저장하고
		//에러전용페이지 보여주기
		ModelAndView mav = new ModelAndView();
		mav.addObject("e", e); //request 객체에 저장하게 됨
		mav.setViewName("error/result");
		return mav;
	}
	
}
