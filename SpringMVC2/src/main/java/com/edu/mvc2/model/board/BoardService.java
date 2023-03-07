package com.edu.mvc2.model.board;

import java.util.List;

import com.edu.mvc2.domain.Board;

//BoardDAO와 BoardService가 차이가 없어보이는데 굳이 써야되나요?
//업무가 복잡해지면 확연히 차이가 난다!
public interface BoardService {

	public List selectAll();
	public Board select(int board_idx);
	public void insert(Board board);
	public void update(Board board);
	public void delete(int board_idx);
	
}
