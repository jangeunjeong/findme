package com.ssafy.findme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.dto.ReviewDTO;
import com.ssafy.findme.service.IReviewService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"))
@RequestMapping("/api")
@RestController
public class ReviewController {
	@Autowired
	private IReviewService reviewservice;

	// 작성, 업데이트 시간이 이상함
	@PostMapping("/review/write")
	@ApiOperation(value = "리뷰 작성")
	public void save(@RequestParam Long user_id, @RequestParam String content, @RequestParam Long language_id) {
		reviewservice.save(user_id, content, language_id);
	}

	@PutMapping("/review/update/{review_id}")
	@ApiOperation(value = "리뷰 업데이트")
	@ResponseBody
	public void update(@PathVariable Long review_id, @RequestParam String content) {
		reviewservice.update(review_id, content);
	}

	@GetMapping("/review/findAll/{language_id}")
	@ApiOperation(value = "프로그래밍 언어별 전체 리뷰 조회")
	@ResponseBody
	public List<ReviewDTO> findAllByLanguageId(@PathVariable Long language_id, @RequestParam Long user_id) {
		return reviewservice.findAllByLanauageId(language_id,user_id);
	}

	@DeleteMapping("/review/delete/{review_id}")
	@ApiOperation(value = "리뷰 삭제")
	public void delete(@PathVariable Long review_id) {
		reviewservice.delete(review_id);
	}

	
	@PostMapping("/review/symp/save/{review_id}")
	@ApiOperation(value = "하나의 리뷰 공감 누름")
	public boolean saveSymp(@PathVariable Long review_id, @RequestParam Long user_id) {
		return reviewservice.saveSymp(review_id, user_id);
	}
	
	
	@DeleteMapping("/review/symp/delete")
	@ApiOperation(value = "리뷰 공감 해제")
	public void deleteSymp(@RequestParam Long review_id, @RequestParam Long user_id) {
		reviewservice.deleteSymp(review_id, user_id);
	}
	
	
	@PostMapping("/review/unsymp/save/{review_id}")
	@ApiOperation(value = "하나의 리뷰 비공감 누름")
	public boolean saveUnsymp(@PathVariable Long review_id, @RequestParam Long user_id) {
		return reviewservice.saveUnsymp(review_id, user_id);
	}
	
	
	@DeleteMapping("/review/unsymp/delete")
	@ApiOperation(value = "리뷰 비공감 해제")
	public void deleteUnsymp(@RequestParam Long review_id, @RequestParam Long user_id) {
		reviewservice.deleteUnsymp(review_id, user_id);
	}
}
