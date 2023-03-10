package com.edu.springshop.sns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;

import lombok.Data;

//컨트롤러에서 제어해야하지만
//컨트롤러가 너무 비대해지기 때문에 따로 클래스를 만들어서 제어
@Data
public class GoogleLogin {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//스프링 빈 설정파일에 등록한 구글 전용 빈을 가져와서 개발하자
	@Autowired
	private GoogleConnectionFactory googleeConnectionFactory;
	@Autowired
	private OAuth2Parameters oAuth2Parameters;
	
	public String handle() {
		//인증화면과 관련된 URL 만들어내기
		OAuth2Operations operation= googleeConnectionFactory.getOAuthOperations();
		String url = operation.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
		logger.info("인증에 사용할 url 은 "+url);
		return url;
	}
	
	
}
