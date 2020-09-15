package com.ssafy.findme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.dto.PickDTO;
import com.ssafy.findme.service.IPickService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"))
@RestController
@RequestMapping("/api")
public class PickController {
	@Autowired
	private IPickService pickservice;
	
	
	@PostMapping("/pick/save/{user_id}")
	@ApiOperation(value = "공고 찜하기")
	public void savePick(@PathVariable Long user_id, @RequestParam Long recruit_id) {
		pickservice.savePick(user_id, recruit_id);
	}
	
	@GetMapping("/pick/findAll/{user_id}")
	@ApiOperation(value = "사용자의 찜 목록 조회")
	@ResponseBody
	public List<PickDTO> findAllByUserId(@PathVariable Long user_id) {
		return pickservice.findAllByUserId(user_id);
	}
	
	@DeleteMapping("/pick/delete")
	@ApiOperation(value = "공고 찜 해제")
	public void deletePick(@RequestParam Long user_id, @RequestParam Long recruit_id) {
		pickservice.deletePick(user_id, recruit_id);
	}
}
