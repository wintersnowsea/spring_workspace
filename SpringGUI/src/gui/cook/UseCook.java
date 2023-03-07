package gui.cook;

//실행을 위한 클래스
public class UseCook {
	
	public static void main(String[] args) {
		Cook cook = new Cook();
		cook.cook();
	}
	
}

/*
 	(의존성 탈피를 위한 주입) : 의존성 있는 객체는 주입을 받아 개발하자
 	Dependency Injection
 	개발시 유지보수성을 높이려면 의존성 잇는 코드는 지양해야한다
 	해결책? 직접 new 하지 않고 외부에서 주입받아야 한다
 * */