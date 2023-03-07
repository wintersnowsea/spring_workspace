package com.edu.springboard.client.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edu.springboard.domain.Notice;
import com.edu.springboard.exception.NoticeException;
import com.edu.springboard.model.notice.NoticeService;

@RestController
@RequestMapping("/rest")
public class RestNoticeController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NoticeService noticeService;
	
	//글쓰기 요청받기
	@PostMapping("/notice/regist")
	public String regist(Notice notice) {
		/*안드로이드로 확인 전에 cmb창에서 확인하는 방법
		 * C:\>curl -X POST localhost:7777/rest/notice/regist -d "title=aa&writer=bb&content=abcd"*/
		logger.info("title = "+notice.getTitle());
		logger.info("writer = "+notice.getWriter());
		logger.info("content = "+notice.getContent());
		
		//3단계
		noticeService.insert(notice);
		
		return "퇴근하세요";
	}
	
	//글 목록 요청처리
	@GetMapping("/notice/list")
	public List<Notice> getList(){
		//3단계
		List<Notice> noticeList = noticeService.selectAll();
		
		//4단계: jsp 뷰로 가져갈 일이 없으므로 필요없음
		
		return noticeList;
	}
	
	//상세보기 요청처리
	@RequestMapping(value="/notice/detail", method = RequestMethod.GET)
	public Notice getDetail(int notice_idx) {
		//3단계 : 서비스에 일시키기
		Notice notice= noticeService.select(notice_idx);
		//@ResponseBody 가 적용되지 않으면 이 메서드의 반환값을 이용하여
		//InternalResourceViewResolver가 다음과 같은 정보를 구성하여
		//View로 반환해버릴 것이다
		//예상정보 : WEB-INF/views/Notice.jsp (우리가 의도한 바가 아님)
		return notice;
	}
	
	//삭제요청처리
	@RequestMapping(value="/notice/del", method = RequestMethod.GET)
	public String del(int notice_idx) {
		//3단계 : 일시키기
		noticeService.delete(notice_idx);
		
		//반환값을 보다 체계적인 정보를 구성하려면 ResponseEntity라는 객체 이용가능
		return "delete";
	}
	
	//수정요청처리
	@RequestMapping(value="/notice/edit", method = RequestMethod.POST)
	public String edit(Notice notice) {
		//3단계
		noticeService.update(notice);
		
		return "update";
	}
	
	@ExceptionHandler(NoticeException.class)
	public String handle(NoticeException e) {
		return e.getMessage();
	}
	
}
