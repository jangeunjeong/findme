package com.ssafy.findme.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.ssafy.findme.dto.UserDTO;
import com.ssafy.findme.dto.UserDTO.RoleType;
import com.ssafy.findme.service.IAccountService;
import com.ssafy.findme.service.IKakaoAPI;
import com.ssafy.findme.service.IReviewService;
import com.ssafy.findme.service.KakaoAPI;
import com.ssafy.findme.service.UserSha256;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = ("*"), maxAge = 6000) // 혹시나 에러나면 maxAge 빼기
@RequestMapping("/api") // url과 컨트롤러의 메서드 매핑할때 사용하는 스프링 프레임워크 어노테이션
@RestController // 메서드마다 @ResponseBody 안붙여도 전송가능
public class AccountController {

	@Autowired
	private IAccountService accountservice;
	@Autowired
	private IReviewService reviewservice;
	@Autowired
	private IKakaoAPI kakao;

	@GetMapping("/user/{email}/email-duplicate")
	@ApiOperation(value = "이메일 중복체크")
	public boolean emailduplicate(@PathVariable String email) {
		return !accountservice.emailDuplicateCheck(email);
	}

	@PostMapping("/user/signup")
    @ApiOperation(value = "가입하기")
    public void signup(@RequestBody UserDTO user) throws MessagingException {
        // 비밀번호 암호화
        String encryPassword = UserSha256.encrypt(user.getPassword());
        user.setPassword(encryPassword);
        user.setRoleType(RoleType.USER);
        user.setCreatedAt(new Date());
        String[] tech = user.getTechStack().split(",");
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
        // 회원가입
        accountservice.signUp(user);
        // 인증메일
        accountservice.mailSendWithUserKey(user.getEmail(), user.getName());
    }

	@PutMapping("/user/{user_id}/secession")
	@ApiOperation(value = "회원 탈퇴")
	public ResponseEntity<Map<String, Object>> secession(@PathVariable Long user_id, @RequestParam String tmp,
			HttpServletRequest req) throws MessagingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			UserDTO user = accountservice.findById(user_id);
			if (user.getPassword().equals(UserSha256.encrypt("kakao"))) {// 카카오 계정 탈퇴인 경우는 카카오 api에서 한번 더
				kakao.secession(tmp);
			}
			accountservice.deleteUser(user);
			reviewservice.recountSympAndUnsymp(user_id);

			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/user/kakaologout")
	@ApiOperation(value = "로그아웃")
	public ResponseEntity<Map<String, Object>> kakaologout(@RequestParam String tmp, HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			kakao.kakaoLogout(tmp);

			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/user/key_alter")
	@ApiOperation(value = "이메일 인증하기")
	public void authentication(@RequestParam("email") String email, @RequestParam("key") String key) {
		accountservice.alterUserKey(email, key);
	}

	@PutMapping("/user/sendpassword")
	@ApiOperation(value = "임시 비밀번호 전송")
	public void sendpassword(@RequestParam String email) throws MessagingException {
		UserDTO user = accountservice.changePassword(email);
		accountservice.mailSend(user.getEmail(), user.getPassword(), user.getName());
		user.setPassword(UserSha256.encrypt(user.getPassword()));
		accountservice.updateProfile(user);
	}

	@PostMapping("/user/login")
	@ApiOperation(value = "일반계정 로그인")
	public ResponseEntity<Map<String, Object>> login(@RequestParam String email, @RequestParam String password,
			HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			UserDTO user = new UserDTO();
			String encryPassword = UserSha256.encrypt(password);
			user.setEmail(email);
			user.setPassword(encryPassword);
			user = accountservice.login(user);
			String token = accountservice.getToken(user);
			res.setHeader("jwt-auth-token", token);
			resultMap.put("info", user);
			resultMap.put("token", token);
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		System.out.println(status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/user/find/{user_id}")
	@ApiOperation(value = "사용자 정보 조회")
	public UserDTO findById(@PathVariable Long user_id) {
		return accountservice.findById(user_id);
	}

	@GetMapping("/user/kakao_oauth")
	@ApiOperation(value = "카카오 계정으로 시작하기 및 로그인")
	public ResponseEntity<Map<String, Object>> kakaologin(@RequestParam("code") String code, HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		String access_Token = kakao.getAccessToken(code);
		System.out.println(access_Token);
		HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
		String name = userInfo.get("nickname").toString();
		String email = userInfo.get("email").toString();
		String password = UserSha256.encrypt("kakao");

		System.out.println(name);
		System.out.println(email);
		System.out.println(password);

		try {
			UserDTO user = new UserDTO();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			System.out.println(user.getName());
			System.out.println(user.getEmail());
			System.out.println(user.getPassword());
			// db에 이메일이 없으면 singup
//            res.setHeader("jwt-auth-token", access_Token);

			if (accountservice.emailDuplicateCheck(email)) {
				user = accountservice.info(email);
				resultMap.put("status", true);
				status = HttpStatus.ACCEPTED;
				System.out.println("login");
			} else { // 카카오 계정으로 회원가입
				resultMap.put("status", false);
				resultMap.put("log", "회원가입이 필요합니다.");
				status = HttpStatus.ACCEPTED;
				System.out.println("signup");
			}
			String token = accountservice.getToken(user);
			res.setHeader("jwt-auth-token", token);
			res.setHeader("access-token", access_Token);

			resultMap.put("info", user);
			resultMap.put("jwt-auth-token", token);
			resultMap.put("access-token", access_Token);
			System.out.println("ㅇㅅㅇ");
		} catch (

		RuntimeException e) {
			System.out.println("8ㅅ8");
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/user/kakao_signup")
	@ApiOperation(value = "카카오 계정으로 가입하기 및 로그인")
	public ResponseEntity<Map<String, Object>> kakaosignup(@RequestBody UserDTO user, HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		String token = req.getHeader("jwt-auth-token");
		String accessToken = req.getHeader("access-token");
		try {
			user.setAuthKey("Y");
			user.setPassword(UserSha256.encrypt("kakao"));
			user = accountservice.signUp(user);
			String[] tech = user.getTechStack().split(",");
			for (int i = 0; i < tech.length; i++) {
				if (tech.equals("Cpp"))
					tech[i] = "C++";
				else if (tech.equals("Csharp"))
					tech[i] = "C#";
			}

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