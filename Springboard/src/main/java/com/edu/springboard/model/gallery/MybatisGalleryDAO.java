package com.edu.springboard.model.gallery;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.springboard.domain.Gallery;
import com.edu.springboard.exception.GalleryException;

@Repository
public class MybatisGalleryDAO implements GalleryDAO {

	//mybatis- spring에서는 기존 마이바티스의 쿼리남당 객체였던 sql세션이
	//템플릿으로 바뀌었다
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Gallery.selectAll");
	}

	@Override
	public Gallery select(int gallery_idx) {
		return sqlSessionTemplate.selectOne("Gallery.select", gallery_idx);
	}

	@Override
	public void insert(Gallery gallery) throws GalleryException{
		int result = sqlSessionTemplate.insert("Gallery.insert", gallery);
		if(result<1) {
			throw new GalleryException("갤러리등록 실패");
		}
	}

	@Override
	public void update(Gallery gallery)throws GalleryException {
		int result = sqlSessionTemplate.update("Gallery.update", gallery);
		if(result<1) {
			throw new GalleryException("갤러리수정 실패");
		}
	}

	@Override
	public void delete(int gallery_idx) throws GalleryException{
		int result = sqlSessionTemplate.delete("Gallery.delete", gallery_idx);
		if(result<1) {
			throw new GalleryException("갤러리삭제 실패");
		}
	}

}
