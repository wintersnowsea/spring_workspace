package com.edu.springmvc1.model;

//이 클래스는 웹이건, 독립실행형이건 둘다 사용가능한 공통로직이다
//즉 재사용성을 위해 기존의 jsp에서 이 코드를 분리시킴
//MVC 개발 방법론에 의해 이 객체의 역할은 모델이다(model)
public class BloodAdvisor {
	
	//혈액형에 대한 판단 결과를 반환해주는 메서드
	public String getAdvice(String blood) {

		String msg=null; //결과 메세지를 담을 변수
		if(blood.equals("A")){
			msg="꼼꼼하다";
		}else if(blood.equals("B")){
			msg="자기 주관이 뚜렷";
		}else if(blood.equals("O")){
			msg="친구가 많다";
		}else if(blood.equals("AB")){
			msg="선택지를 많이 둔다";
		}
		
		return msg;
	}
	
}
