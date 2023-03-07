package com.edu.springboard.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.springboard.domain.Notice;
import com.edu.springboard.model.notice.NoticeService;

@RestController
@RequestMapping("/rest")
public class RestAPINoticeController {
	
	@Autowired
	private NoticeService noticeService;
	//REST를 준수하여 URL을 매핑해보자 (RESTful)
	
	//목록요청
	@GetMapping("/notices")
	public List<Notice> selectAll() {
		return noticeService.selectAll();
	}
	
	//상세보기요청
	@GetMapping("/notices/{notice_idx}")
	public Notice select(@PathVariable("notice_idx") int notice_idx) {
		return noticeService.select(notice_idx);
	}
	
	//삭제요청
	@DeleteMapping("/notices/{notice_idx}")
	public String del(@PathVariable("notice_idx") int notice_idx) {
		noticeService.delete(notice_idx);
		return "delete";
	}
	
	//수정요청
	@PutMapping("/notices")
	public String edit(Notice notice) {
		noticeService.update(notice);
		return "update";
	}
	
	//등록요청
	@PostMapping("/notices")
	public String regist(Notice notice) {
		noticeService.insert(notice);
		return "insert";
	}
	
	
	
}
