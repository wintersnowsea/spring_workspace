package com.edu.springshop.model.member;

import java.util.List;

import com.edu.springshop.admin.domain.Member;

public interface MemberService {
	public Member selectById(Member member);
	public void regist(Member member); //insert, 조회, 메일전송 등
}
