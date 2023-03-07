package com.edu.springboard.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Gallery {

	private int gallery_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	private List<Photo> photoList ; 		// mybatis의 컬렉션을 위한 변수
	
	private MultipartFile[] photo;	 	//내부적으로 아파치 업로드를 구현 --변수명은 클라이언트와 일치해야한다 //apache file upload 구현
	
}
