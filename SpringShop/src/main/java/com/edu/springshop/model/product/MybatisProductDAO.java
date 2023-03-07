package com.edu.springshop.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.springshop.admin.domain.Product;
import com.edu.springshop.exception.ProductException;

@Repository
public class MybatisProductDAO implements ProductDAO{

	@Autowired
	private SqlSessionTemplate sqlsessionTemplate;

	@Override
	public List selectAll() {
			List list = sqlsessionTemplate.selectList("Product.selectAll");
		return list;
	}

	@Override
	public Product select(int product_idx) {
		return sqlsessionTemplate.selectOne("Product.select", product_idx);
	}

	@Override
	public void insert(Product product) throws ProductException{ 
		int result = sqlsessionTemplate.insert("Product.insert", product);
		if(result <1) {
			throw new ProductException(" 상품 등록 실패");
		}
	}

	@Override
	public void update(Product product) throws ProductException{
		int result = sqlsessionTemplate.update("Product.update", product);
		if(result <1) {
			throw new ProductException(" 상품 수정 실패");
		}
	}

	@Override
	public void delete(int product_idx) throws ProductException {
		int result = sqlsessionTemplate.delete("Product.delete", product_idx);
		if(result <1) {
			throw new ProductException(" 상품 삭제 실패");
		}
	}
	
	
	
}
