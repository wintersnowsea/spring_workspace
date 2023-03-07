package com.edu.mvc2.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.edu.mvc2.domain.Board;
import com.edu.mvc2.model.board.BoardService;

import lombok.Setter;

//게시판의 상세보기 처리하는 하위 컨트롤러
//2. x 방식
@Setter
public class DetailController implements Controller{
	private BoardService boardService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String board_idx=request.getParameter("board_idx");
		
		//3단계) 일시키기
		Board board = boardService.select(Integer.parseInt(board_idx));
		
		//4단계) 결과저장 : jsp로 가져갈 결과
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("client/board/detail");
		
		return mav;
	}

}
