package com.ssafy.findme.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.dto.RecruitDTO;
import com.ssafy.findme.dto.UserDTO;
import com.ssafy.findme.service.IRecruitService;
import com.ssafy.findme.service.IUserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"), maxAge = 6000) // 혹시나 에러나면 maxAge 빼기
@RequestMapping("/api") // url과 컨트롤러의 메서드 매핑할때 사용하는 스프링 프레임워크 어노테이션
@RestController // 메서드마다 @ResponseBody 안붙여도 전송가능
public class RecruitController {

	@Autowired
	private IRecruitService recruitService;

	@Autowired
	private IUserService userService;

	@GetMapping("user/{userId}/recommend")
	@ApiOperation(value = "공고 만들기")
	public ResponseEntity<Map<String, Object>> makeRecruit(@PathVariable String userId, HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
//			System.out.println(userId);
			List<RecruitDTO> matchRecruitList = recruitService.getMatchRecruit(userId);
//			System.out.println("matchRecruitList");
//			System.out.println(matchRecruitList);
//			System.out.println("---------------------------------------------------------");
			List<RecruitDTO> pickRecruitList = recruitService.getPickRecruit(userId);
//			System.out.println("pickRecruitList");
//			System.out.println(pickRecruitList);
//			System.out.println("---------------------------------------------------------");
			List<RecruitDTO> recommendRecruitList = new ArrayList<>();
			List<String> recommendLanguageList = recruitService.getRecommendLanguage(userId, matchRecruitList);
//			System.out.println("recommendLanguageList");
//			System.out.println(recommendLanguageList);
//			System.out.println("---------------------------------------------------------");

			if (pickRecruitList.isEmpty() || pickRecruitList.size() == 0 || pickRecruitList == null) {
				recommendRecruitList = matchRecruitList.subList(6, 59);
			} else {
				recommendRecruitList = recruitService.getRecommendRecruit(userId);

				if (recommendRecruitList.size() < 10) {
					recommendRecruitList.addAll(matchRecruitList.subList(6, 59));
				}
			}
			System.out.println("recommendRecruitList");
			System.out.println(recommendRecruitList);
			resultMap.put("matchRecruitList", matchRecruitList);
			resultMap.put("pickRecruitList", pickRecruitList);
			resultMap.put("recommendRecruitList", recommendRecruitList);
			resultMap.put("recommendLanguageList", recommendLanguageList);
			status = HttpStatus.OK;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("user/carousel")
	@ApiOperation(value = "메인 캐루젤 만들기")
	public ResponseEntity<Map<String, Object>> makeCarousel(HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
//			UserDTO myInfo = userService.findById(Long.parseLong(userId));
			List<RecruitDTO> recruitList = recruitService.getCarouselRecruit();

//			myTechStackList = Arrays.asList(myInfo.getTechStack().split(","));

			List<RecruitDTO> tmpCarouselList = new ArrayList<>();

			for (int i = 0; i < recruitList.size(); i++) {
				List<String> recruitTechStackList = new ArrayList<>();
				recruitTechStackList = Arrays.asList(recruitList.get(i).getTechStack().replace("·", ",").split(","));

				tmpCarouselList.add(recruitList.get(i));
			}
			List<Integer> idxList = new ArrayList<>();

			Random random = new Random();
			random.setSeed(System.currentTimeMillis());

			for (int i = 0; i < 100; i++) {
				if (idxList.size() == 5) {
					break;
				}
				int index = random.nextInt(tmpCarouselList.size() + 1);

				if (!idxList.contains(index)) {
					idxList.add(index);
				}
			}
			List<RecruitDTO> carouselList = new ArrayList<>();

			for (int i = 0; i < idxList.size(); i++) {
				carouselList.add(tmpCarouselList.get(idxList.get(i)));
			}
			resultMap.put("carouselList", carouselList);
			status = HttpStatus.OK;
		} catch (

		Exception e) {
			System.out.println(e.getMessage());
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}