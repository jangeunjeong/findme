package com.ssafy.findme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.dto.GoogleTrendDataDTO;
import com.ssafy.findme.dto.GoogleTrendGeoDataDTO;
import com.ssafy.findme.service.IGoogleDataService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"))
@RestController
@RequestMapping("/api")
public class GoogleDataController {
	@Autowired
	private IGoogleDataService googledataservice;

	@GetMapping("/google/findAll/date")
	@ApiOperation(value = "구글 트렌드 시간별 데이터")
	@ResponseBody
	public Map<String, Map<String, Long>> findAllByDate() {
		List<GoogleTrendDataDTO> gdataList = googledataservice.findAllByDate();
		Map<String, Map<String, Long>> resultMap = new HashMap<>();
		Map<String, Long> dateMap = new HashMap<>();
		String date;
		Long count;

		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getJavas();
			dateMap.put(date, count);
		}
		resultMap.put("Java", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getCs();
			dateMap.put(date, count);
		}
		resultMap.put("C", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getPythons();
			dateMap.put(date, count);
		}
		resultMap.put("Python", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getCpps();
			dateMap.put(date, count);
		}
		resultMap.put("C++", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getCsharps();
			dateMap.put(date, count);
		}
		resultMap.put("C#", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getVbns();
			dateMap.put(date, count);
		}
		resultMap.put("VB.NET", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getJss();
			dateMap.put(date, count);
		}
		resultMap.put("JavaScript", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getPhps();
			dateMap.put(date, count);
		}
		resultMap.put("PHP", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getSqls();
			dateMap.put(date, count);
		}
		resultMap.put("SQL", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getGos();
			dateMap.put(date, count);
		}
		resultMap.put("Go", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getRs();
			dateMap.put(date, count);
		}
		resultMap.put("R", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getAssemblys();
			dateMap.put(date, count);
		}
		resultMap.put("Assembly", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getSwifts();
			dateMap.put(date, count);
		}
		resultMap.put("Swift", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getRubys();
			dateMap.put(date, count);
		}
		resultMap.put("Ruby", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getMatlabs();
			dateMap.put(date, count);
		}
		resultMap.put("MATLAB", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getPls();
			dateMap.put(date, count);
		}
		resultMap.put("PL/SQL", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getPerls();
			dateMap.put(date, count);
		}
		resultMap.put("Perl", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getVbs();
			dateMap.put(date, count);
		}
		resultMap.put("Visual Basic", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getOcs();
			dateMap.put(date, count);
		}
		resultMap.put("Objective-C", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getInsertDate();
			count = gdataDTO.getDelphis();
			dateMap.put(date, count);
		}
		resultMap.put("Delphi", dateMap);

//		System.out.println(resultMap.size());
		return resultMap;
	}

	@GetMapping("/google/findAll/geo")
	@ApiOperation(value = "구글 트렌드 지역별 데이터")
	@ResponseBody
	public Map<String, Map<String, Long>> findAllByGeo() {
		List<GoogleTrendGeoDataDTO> gdataList = googledataservice.findAllByGeo();
		Map<String, Map<String, Long>> resultMap = new HashMap<>();
		Map<String, Long> dateMap = new HashMap<>();
		String date;
		Long count;

		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getJavas();
			dateMap.put(date, count);
		}
		resultMap.put("Java", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getCs();
			dateMap.put(date, count);
		}
		resultMap.put("C", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getPythons();
			dateMap.put(date, count);
		}
		resultMap.put("Python", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getCpps();
			dateMap.put(date, count);
		}
		resultMap.put("C++", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getCsharps();
			dateMap.put(date, count);
		}
		resultMap.put("C#", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getVbns();
			dateMap.put(date, count);
		}
		resultMap.put("VB.NET", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getJss();
			dateMap.put(date, count);
		}
		resultMap.put("JavaScript", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getPhps();
			dateMap.put(date, count);
		}
		resultMap.put("PHP", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getSqls();
			dateMap.put(date, count);
		}
		resultMap.put("SQL", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getGos();
			dateMap.put(date, count);
		}
		resultMap.put("Go", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getRs();
			dateMap.put(date, count);
		}
		resultMap.put("R", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getAssemblys();
			dateMap.put(date, count);
		}
		resultMap.put("Assembly", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getSwifts();
			dateMap.put(date, count);
		}
		resultMap.put("Swift", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getRubys();
			dateMap.put(date, count);
		}
		resultMap.put("Ruby", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getMatlabs();
			dateMap.put(date, count);
		}
		resultMap.put("MATLAB", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getPls();
			dateMap.put(date, count);
		}
		resultMap.put("PL/SQL", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getPerls();
			dateMap.put(date, count);
		}
		resultMap.put("Perl", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getVbs();
			dateMap.put(date, count);
		}
		resultMap.put("Visual Basic", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getOcs();
			dateMap.put(date, count);
		}
		resultMap.put("Objective-C", dateMap);

		dateMap = new HashMap<>();
		for (int i = 0; i < gdataList.size(); i++) {
			GoogleTrendGeoDataDTO gdataDTO = gdataList.get(i);
			date = gdataDTO.getGeoName();
			count = gdataDTO.getDelphis();
			dateMap.put(date, count);
		}
		resultMap.put("Delphi", dateMap);

		System.out.println(resultMap.size());
		return resultMap;
	}
}
