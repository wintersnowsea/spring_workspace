package com.edu.db.exception;

public class BoardException extends RuntimeException {
	
	public BoardException(String msg) {
		super(msg);
	}
	
	//Throwable : 에러의 가장 상위 자료형
	public BoardException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
