package com.edu.springshop.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.springshop.admin.domain.Member;
import com.edu.springshop.admin.domain.SnS;
import com.edu.springshop.exception.EmailException;
import com.edu.springshop.exception.HashException;
import com.edu.springshop.exception.MemberException;
import com.edu.springshop.util.EmailManager;
import com.edu.springshop.util.PassConverter;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private PassConverter passConverter;
	
	@Autowired
	private EmailManager emailManager;

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private SnSDAO snsDAO; 

	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Member member) throws HashException, EmailException, MemberException {
		
		/*
		 홈페이지 회원가입시 사용
		//암호화
		String hash = passConverter.convertHash(member.getPass());
		member.setPass(hash); //DTO의 패스워드를 hash값으로 대체
		
		//이메일
		//emailManager.send(member);
		 */
		
		//현재 가입하려고 하는 회원이 어떤 sns유형으로 가입하는 지 조사
		SnS sns = snsDAO.selectByName(member.getSns().getSns_name()); //google, kakao, naver...
		
		member.setSns(sns); //회원가입을 위한 sns 정보 대입, 이 시점부터는 member는 sns를 가진 dto임
		
		//DB insert
		memberDAO.insert(member);
	}



	@Override
	public Member selectById(Member member) {
		return memberDAO.selectById(member);
	}



}
