package com.ssafy.findme.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.findme.dto.FriendDTO;
import com.ssafy.findme.dto.UserDTO;
import com.ssafy.findme.repository.RecruitRepository;
import com.ssafy.findme.service.IUserService;
import com.ssafy.findme.service.KakaoAPI;
import com.ssafy.findme.service.UserSha256;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"), maxAge = 6000) // 혹시나 에러나면 maxAge 빼기
@RequestMapping("/api") // url과 컨트롤러의 메서드 매핑할때 사용하는 스프링 프레임워크 어노테이션
@RestController // 메서드마다 @ResponseBody 안붙여도 전송가능
public class UserController {

	@Autowired
	private RecruitRepository recruitrepo;

	@Autowired
	private IUserService userservice;
	@Autowired
	private KakaoAPI kakao;

	@GetMapping("/user/{id}/profile")
	@ApiOperation(value = "프로필")
	public UserDTO profile(@PathVariable Long id, HttpServletRequest req) {
		UserDTO user = userservice.findById(id);
		return user;
	}

	@PutMapping("/user/{id}/updateprofile")
	@ApiOperation(value = "프로필 수정")
	public ResponseEntity<Map<String, Object>> updateprofile(@PathVariable Long id, @RequestParam String techStack,
			@RequestParam String wishHope, @RequestParam String wishJob, HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			UserDTO user = userservice.findById(id);

			String[] tech = techStack.split(",");
			for (int i = 0; i < tech.length; i++) {
				if (tech[i].contains("Cpp"))
					tech[i] = "C++";
				else if (tech[i].contains("Csharp"))
					tech[i] = "C#";
			}
			String tech_stack = "";
			for (int i = 0; i < tech.length; i++) {
				tech_stack += tech[i] + ",";
			}
			tech_stack = tech_stack.substring(0, tech_stack.length() - 1);
			System.out.println(tech_stack);
			user.setTechStack(tech_stack);

			user.setWishHope(wishHope);
			user.setWishJob(wishJob);
			UserDTO member = userservice.updateProfile(user);

			resultMap.put("status", true);
			resultMap.put("info", member);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PutMapping("/user/{id}/updatepassword")
	@ApiOperation(value = "비밀번호 변경")
	public ResponseEntity<Map<String, Object>> updatepassword(@PathVariable Long id, @RequestParam String pre_password,
			@RequestParam String new_password, HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		System.out.println("id: " + id);
		System.out.println("pre_password: " + pre_password);
		System.out.println("new_password: " + new_password);

		try {
			UserDTO user = userservice.findById(id);

			if (user.getPassword().equals(UserSha256.encrypt("kakao"))) {
				resultMap.put("status", false);
				resultMap.put("log", "카카오 계정이므로 비밀번호 변경이 불가능합니다.");
				status = HttpStatus.ACCEPTED;
				return new ResponseEntity<Map<String, Object>>(resultMap, status);
			}
//			String encryPrePassword = UserSha256.encrypt(pre_password);
//			System.out.println("user.getPassword(): " + user.getPassword());
//			System.out.println(encryPrePassword);

			if (user.getPassword().equals(pre_password)) {
				String encryNewPassword = UserSha256.encrypt(new_password);
				user.setPassword(encryNewPassword);
				UserDTO member = userservice.updateProfile(user);
				// logout해야할 것 같은데

				resultMap.put("status", true);
				resultMap.put("info", member);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("status", false);
				status = HttpStatus.ACCEPTED;
			}

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/user/{user_id}/{recruit_id}/kakao_sendToMe")
	@ApiOperation(value = "카카오 메세지 나에게 보내기")
	public ResponseEntity<Map<String, Object>> sendToMe(@PathVariable Long user_id, @PathVariable Long recruit_id,
			@RequestParam String tmp, HttpServletRequest req) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			String token = req.getHeader("access-token");
//			String token = req.getHeader("jwt-auth-token");
			kakao.sendToMe(token, recruit_id);

			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/user/{user_id}/{recruit_id}/kakao_sendToFriends")
	@ApiOperation(value = "카카오 메세지 친구에게 보내기")
	public ResponseEntity<Map<String, Object>> sendToFriend(@PathVariable Long user_id, @PathVariable String recruit_id,
			@RequestParam String tmp, @RequestBody FriendDTO friends, HttpServletRequest req) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			String token = tmp;
//			String token = req.getHeader("jwt-auth-token");
			System.out.println(friends.getUuids().size());
			kakao.sendToFriends(token, recruit_id, friends.getUuids());

			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/user/{id}/kakaofriends")
	@ApiOperation(value = "내 카톡 친구들")
	public ResponseEntity<Map<String, Object>> kakaofriends(@PathVariable Long id, @RequestParam String tmp,
			HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			String token = tmp;
//			String token = req.getHeader("jwt-auth-token");
			List<FriendDTO> friendsInfo = kakao.friends(token);
			resultMap.put("status", true);
			resultMap.put("info", friendsInfo);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
