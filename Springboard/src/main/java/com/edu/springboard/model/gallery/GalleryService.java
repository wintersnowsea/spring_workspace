package com.edu.springboard.model.gallery;

import java.util.List;

import com.edu.springboard.domain.Gallery;

public interface GalleryService {

	public List selectAll(); 	//dao 동일
	public Gallery select(int gallery_idx); 
	
	//insert+ 파일 업로드 일 두가지 시키고 (파일매니저 때매, 업로드 경로도 필요하다)
	public void regist(Gallery gallery, String path);	
	public void update(Gallery gallery); 	//dao와는 다르다.
	public void delete(Gallery gallery); 	//db + file 도 지우기때매 다르다.
	
}
