package com.edu.hibernateapp.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.edu.hibernateapp.domain.Notice;
import com.edu.hibernateapp.hibernate.HibernateConfig;

public class NoticeDAO {
	HibernateConfig config = HibernateConfig.getInstance();
	
	public void insert() {
		//팩토리 얻기
		SessionFactory factory = config.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction(); //트랜잭션 시작
		
		//하이버네이트는 쿼리문 작성에 의한 업무가 아닌 DTO 제어를 통해 테이블과의 CRUD수행
		Notice notice = new Notice();
		notice.setTitle("하이버네이트 수업");
		notice.setWriter("하이버네이트 작성자");
		notice.setContent("하이버네이트 내용");
		
		session.save(notice); //insert 동작
		tx.commit();
		config.release(factory);
	}
	
}
