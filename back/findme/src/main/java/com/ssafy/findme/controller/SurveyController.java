package com.ssafy.findme.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.dto.SurveyDTO;
import com.ssafy.findme.service.ISurveyService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"))
@RequestMapping("/api")
@RestController
public class SurveyController {
	@Autowired
	private ISurveyService surveyserivce;
	
	@PostMapping("/survey/write")
	@ApiOperation(value = "설문조사 작성")
	public void write(@RequestBody SurveyDTO surveydto) {
		surveyserivce.save(surveydto);
	}
	
	@GetMapping("/survey/findByConfirm")
	@ApiOperation(value = "설문조사 유/무 확인")
	@ResponseBody
	public Map<String, Boolean> findByConfirm(@RequestParam Long user_id, @RequestParam Long language_id) {
		Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
		String result = surveyserivce.findByConfirm(user_id, language_id);
		if(result.equals("yes"))
			resultMap.put("yes", false);
		else if(result.equals("no"))
			resultMap.put("no", true);
		else
			resultMap.put("not", false);
		return resultMap;
	}
}
