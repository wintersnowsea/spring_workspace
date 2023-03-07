package com.edu.springmvc1.model.emp;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.springmvc1.domain.Dept;
import com.edu.springmvc1.exception.DeptException;
import com.edu.springmvc1.exception.EmpException;

//이 어노테이션을 붙여놓으면 스캔에 의해 검색되어 자동으로 인스턴스 생성
//왜 xml에서 명시하지 않나?? xml에 너무나 많은 매핑이 걸려서 오히려 알아보기 힘들다
//따라서 설정파일인 xml이 간소화되는 추세이기 때문에 어노테이션이 적극적으로 사용
@Repository
public class DeptDAO {
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public void insert(Dept dept) throws DeptException{
		int result = sqlSession.insert("Dept.insert", dept);
		if(result<1){
			throw new DeptException("부서등록 실패");
		}
	}
	
	public void delete(int deptno) throws DeptException{
		int result = sqlSession.delete("Dept.delete", deptno);
		if(result<1) {
			throw new DeptException("부서삭제 실패");
		}
	}
	
}
