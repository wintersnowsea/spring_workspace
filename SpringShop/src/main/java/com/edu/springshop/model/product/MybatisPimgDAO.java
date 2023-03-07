package com.edu.springshop.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.springshop.admin.domain.Pimg;
import com.edu.springshop.exception.PimgException;

@Repository
public class MybatisPimgDAO implements PimgDAO {

	
	@Autowired
	private SqlSessionTemplate sqlsessionTemplate;
	
	
	@Override
	public List selectAll() {
		List list = sqlsessionTemplate.selectList("Pimg.selectAll");
		return list;
	}

	@Override
	public List selectByProduct(int product_idx) {
		return sqlsessionTemplate.selectList("Pimg.selectByProduct", product_idx);
	}

	@Override
	public void insert(Pimg pimg) throws PimgException{
		int result = sqlsessionTemplate.insert("Pimg.insert", pimg);
		//result=0;
		if(result<1) {
			throw new PimgException("이미지 등록 실패");
			
		}
	}

	@Override
	public void delete(int product_idx) throws PimgException{
		int result = sqlsessionTemplate.delete("Pimg.delete", product_idx);
		if(result<1) {
			throw new PimgException("이미지 삭제 실패");
			
		}
	}
	
	

}
