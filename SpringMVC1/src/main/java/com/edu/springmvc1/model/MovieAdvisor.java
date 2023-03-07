package com.edu.springmvc1.model;

public class MovieAdvisor {

	public String getAdvisor(String movie) {
		String msg=null;
		
		if(movie.equals("아바타")) {
			msg="아바타 보고싶다";
		}else if(movie.equals("상견니")) {
			msg="상견니는 대만 로맨스인가";
		}else if(movie.equals("나의소녀시대")) {
			msg="나의소녀시대는 정말 재밌었지";
		}else if(movie.equals("범죄도시")) {
			msg="마블리";
		}
		
		return msg;
	}
}
