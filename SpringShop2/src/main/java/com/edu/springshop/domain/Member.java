package com.edu.springshop.domain;

import lombok.Data;

@Data
public class Member {
	private int member_idx;
	private String uid; //sns공통
	private String nickname; //sns 공통
	private String regdate; // 가입일
	
	private SnS sns; // 부모를 has  a 로 보유 (association 대상) 
}







