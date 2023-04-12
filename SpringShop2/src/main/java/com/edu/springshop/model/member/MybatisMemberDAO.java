package com.edu.springshop.model.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.springshop.domain.Member;
import com.edu.springshop.exception.MemberException;

@Repository
public class MybatisMemberDAO implements MemberDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Member selectById(Member member) {
		return sqlSessionTemplate.selectOne("Member.selectById", member);
	}

	@Override
	public void insert(Member member) throws MemberException{
		int result=sqlSessionTemplate.insert("Member.insert", member);
		
		if(result<1) {
			throw new MemberException("회원등록 실패");
		}
	}

	
}
