package com.edu.springshop.model.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.springshop.admin.domain.SnS;

@Repository
public class MybatisSnSDAO implements SnSDAO {
	@ Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("SnS.selectAll");
	}

	@Override
	public SnS selectByIdx(int sns_idx) {
		return sqlSessionTemplate.selectOne("SnS.selectByIdx", sns_idx);
	}

	@Override
	public SnS selectByName(String sns_name) {
		return sqlSessionTemplate.selectOne("SnS.selectByName", sns_name);
	}
	
	
}
