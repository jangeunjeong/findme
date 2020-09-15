package com.ssafy.findme.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.dto.TextMiningDTO;
import com.ssafy.findme.service.ITextMiningService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"))
@RestController
@RequestMapping("/api")

public class TextMiningController {
	@Autowired
	private ITextMiningService textminingservice;
	
	@GetMapping("/language/detail/{id}")
	@ApiOperation(value = "프로그래밍 언어 상세페이지")
	@ResponseBody
	public Map<String, Long> findLanguagePageById(@PathVariable Long id) {
		Map<String, Long> resultMap = new HashMap<>();
		TextMiningDTO textmining = textminingservice.findById(id);
		String[] results = textmining.getResult().split(",");
		
		try {
			for (int i = 0; i < results.length; i+=2) {
				resultMap.put(results[i], Long.parseLong(results[i+1]));
			}
		} catch (Exception e) {
			resultMap.put("message", (long)-1);
		}
		return resultMap;
	}
}
