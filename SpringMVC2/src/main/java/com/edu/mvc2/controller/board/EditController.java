package com.edu.mvc2.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.edu.mvc2.domain.Board;
import com.edu.mvc2.model.board.BoardService;

import lombok.Setter;

//게시판의 글수정 처리하는 하위 컨트롤러
//2. x 방식
@Setter
public class EditController implements Controller{
	private BoardService boardService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String board_idx = request.getParameter("board_idx");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		Board board = new Board();
		board.setBoard_idx(Integer.parseInt(board_idx));
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		//3단계) 일시키기
		boardService.update(board);
				
		//저장해도되고 재접속해도되고
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:board/detail?board_idx="+board_idx);
		
		return mav;
	}

}
