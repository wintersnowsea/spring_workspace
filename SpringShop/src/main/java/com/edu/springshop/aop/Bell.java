package com.edu.springshop.aop;


/*
 * 어플리케이션의 핵심 관심샇항(core concern)은 아니지만 모든 영역에 전반적으로 사용되는 기능을
 * 공통관심사항(cross cutting concern)이라하며, Bell을 공통관심사항으로 정의해보자
 * 
 * 공통관심 사항 코드를 Advice라 한다. 동시에 시점을 Advice 라고도 한다
 * */
public class Bell {
	
	public void ding() {
		System.out.println("딩동댕");
	}
}

/*[Object Oritented Programming 객체지향 프로그래밍]
 * 현실의 문제를 객체로 해결
 * 단점) 관계를 has a, is a 표현하다보니
 * 결합도때문에 유지보수에 문제가 생김
 * 
 * [Aspect Oritented Programming 관점지향 프로그래밍]
 * 현실의 문제를 관점으로 해결
 * 공통로직을 하나의 관점으로 보고 핵심로직인 (학생) 동작할 때, 공통로직을 적용
 * 
 * 관점(시점+지점)
 * Bell을 어느 시점에, 어느 지점에
 * 
 * Bell
 * "학생이 공부해요" 메서드 joinPoint 
 * before (전) : advice
 * "학생이 밥을 먹어요" 메서드 joinPoint - PointCut(joinPoint에서 선택한 것)
 * after (후) : advice
 * "학생이 잠을 자요" 메서드 joinPoint
 * 
 * */