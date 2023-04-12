package com.edu.springshop.model.member;

import java.util.List;

import com.edu.springshop.domain.Member;

public interface MemberDAO {
	public Member selectById(Member member);
	public void insert(Member member);
}





