package com.edu.db.model.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.edu.db.domain.Board;
import com.edu.db.exception.BoardException;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	@Qualifier("mybatisBoardDAO")
	private BoardDAO boardDAO;
	
	@Override
	public List selectAll() {
		return boardDAO.selectAll();
	}

	@Override
	public Board select(int board_idx) {
		return boardDAO.select(board_idx);
	}

	@Override
	public void insert(Board board) throws BoardException {
		boardDAO.insert(board);
	}

	@Override
	public void update(Board board) throws BoardException {
		boardDAO.update(board);
	}

	@Override
	public void delete(int board_idx) throws BoardException {
		boardDAO.delete(board_idx);
	}

}
