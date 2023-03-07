package com.edu.mvc3.model.util;

import java.io.File;
import java.io.IOException;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.edu.mvc3.domain.Gallery;
import com.edu.mvc3.exception.UploadException;

//context:component-scan에 의해 메모리에 자동 생성
@Component
public class FileManager {
	
	//파일명 생성하기
	public String createFileName(String filename) {
		
		long time = System.currentTimeMillis();
		String ext = filename.substring(filename.lastIndexOf(".")+1, filename.length());
		
		String finalName = time+"."+ext;
		
		return finalName;
	}
	
	public void save(Gallery gallery, String path) throws UploadException {
		//서버에 저장
		MultipartFile multi = gallery.getFile();
		try {
			multi.transferTo(new File(path));
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new UploadException("업로드 실패", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UploadException("업로드 실패", e);
		}

	}
}
