package com.edu.springshop.model.member;

import com.edu.springshop.domain.Member;

public interface MemberService {
	public Member selectById(Member member);
	public void regist(Member member); //insert, 조회 , email 전송 등등...
}







