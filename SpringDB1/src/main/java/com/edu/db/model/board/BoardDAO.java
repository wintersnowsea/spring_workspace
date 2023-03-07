package com.edu.db.model.board;

import java.util.List;

import com.edu.db.domain.Board;

//모든 board관련 DAO들이 반드시 구현해야할 메서드를 정의해놓은 최상위 인터페이스
public interface BoardDAO {
	public List selectAll();
	public Board select(int board_idx);
	public void insert(Board board);
	public void update(Board board);
	public void delete(int board_idx);
}
