package com.edu.mvc2.model.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.edu.mvc2.domain.Board;
import com.edu.mvc2.mybatis.MybatisConfig;

import lombok.Setter;

@Setter
public class BoardServiceImpl implements BoardService{
	//DI 주입받자
	private MybatisConfig config; //싱글턴도 주입가능
	private BoardDAO boardDAO;
	
	@Override
	public List selectAll() {
		SqlSession sqlSession = config.getSqlSession();
		MybatisBoardDAO dao = (MybatisBoardDAO)boardDAO;
		dao.setSqlSession(sqlSession);
		List list = dao.selectAll();
		config.release(sqlSession);
		return list;
	}

	@Override
	public Board select(int board_idx) {
		SqlSession sqlSession = config.getSqlSession();
		MybatisBoardDAO dao = (MybatisBoardDAO)boardDAO;
		dao.setSqlSession(sqlSession);
		Board board= dao.select(board_idx);
		config.release(sqlSession);
		return board;
	}

	@Override
	public void insert(Board board) {
		SqlSession sqlSession = config.getSqlSession();
		MybatisBoardDAO dao = (MybatisBoardDAO)boardDAO;
		dao.setSqlSession(sqlSession);
		dao.insert(board);
		sqlSession.commit();
		config.release(sqlSession);
	}

	@Override
	public void update(Board board) {
		SqlSession sqlSession = config.getSqlSession();
		MybatisBoardDAO dao = (MybatisBoardDAO)boardDAO;
		dao.setSqlSession(sqlSession);
		dao.update(board);
		sqlSession.commit();
		config.release(sqlSession);
	}

	@Override
	public void delete(int board_idx) {
		SqlSession sqlSession = config.getSqlSession();
		MybatisBoardDAO dao = (MybatisBoardDAO)boardDAO;
		dao.setSqlSession(sqlSession);
		dao.delete(board_idx);
		sqlSession.commit();
		config.release(sqlSession);
	}

}
