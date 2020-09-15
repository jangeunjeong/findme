package com.ssafy.findme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.dto.SaraminExtractDTO;
import com.ssafy.findme.dto.UserDTO;
import com.ssafy.findme.repository.RecruitRepository;
import com.ssafy.findme.service.ISaraminExtractService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"), maxAge = 6000) // 혹시나 에러나면 maxAge 빼기
@RequestMapping("/api") // url과 컨트롤러의 메서드 매핑할때 사용하는 스프링 프레임워크 어노테이션
@RestController // 메서드마다 @ResponseBody 안붙여도 전송가능
public class SaraminController {

	@Autowired
	private ISaraminExtractService saraminservice;

	@GetMapping("/user/ranking")
	@ApiOperation(value = "기업이 선호하는 기술스택 + 연봉이 높은 기술스택")
	public ResponseEntity<Map<String, Object>> adoptedranking() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			//
			List<SaraminExtractDTO> user = saraminservice.ranking();

			resultMap.put("status", true);
			resultMap.put("info", user);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
