package com.edu.springboard.model.reboard;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.springboard.domain.ReBoard;
import com.edu.springboard.exception.ReBoardException;

@Repository
public class MybatisReBoardDAO implements ReBoardDAO {
	
	/*Autowired의 역할
	 xml로 표현했다면 아래와 같이 했어야 함
	 <bean class="MybatisReBoardDAO">
	 	<property name="sqlSessionTeamplate" ref="sqlSessionTeamplate" />
	 </bean>
	 */
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("ReBoard.selectAll");
	}

	@Override
	public ReBoard select(int reboard_idx) {
		return sqlSessionTemplate.selectOne("ReBoard.select", reboard_idx);
	}

	@Override
	public void insert(ReBoard reboard) throws ReBoardException {
		int result = sqlSessionTemplate.insert("ReBoard.insert", reboard);
		if(result < 1) {
			throw new ReBoardException("등록실패");
		}
	}

	@Override
	public void update(ReBoard reboard) throws ReBoardException {
		int result = sqlSessionTemplate.update("ReBoard.update", reboard);
		//result=0; //에러페이지 확인
		if(result < 1) {
			throw new ReBoardException("수정실패");
		}
	}

	@Override
	public void delete(int reboard_idx) throws ReBoardException {
		int result = sqlSessionTemplate.delete("ReBoard.delete", reboard_idx);
		if(result<1) {
			throw new ReBoardException("삭제실패");
		}
	}

	@Override
	public void updateStep(ReBoard reboard) {
		sqlSessionTemplate.update("ReBoard.updateStep", reboard);
	}

	@Override
	public void reply(ReBoard reboard) throws ReBoardException {
		int result = sqlSessionTemplate.insert("ReBoard.reply", reboard);
		if(result < 1) {
			throw new ReBoardException("답글 등록실패");
		}
	}
	
}
