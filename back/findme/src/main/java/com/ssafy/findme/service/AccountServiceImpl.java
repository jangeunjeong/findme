package com.ssafy.findme.service;

import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.findme.domain.User;
import com.ssafy.findme.dto.UserDTO;
import com.ssafy.findme.mapper.EntityMapper;
import com.ssafy.findme.repository.AccountRepository;
import com.ssafy.findme.security.JwtService;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepository accountrepo;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EntityMapper entityMapper;

//	private String IP = "http://localhost:8081";
	private String IP = "https://i02b204.p.ssafy.io";

	@Override
	public boolean emailDuplicateCheck(String email) {
		int check = accountrepo.countByEmail(email);
		if (check == 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean accountDuplicateCheck(String email, String password) {
		int check = accountrepo.countByEmailAndPassword(email, password);
		if (check == 0)
			return false;
		else
			return true;
	}

	@Override
	public UserDTO signUp(UserDTO user) {
		User member = modelMapper.map(user, User.class);
		UserDTO memberDTO = modelMapper.map(accountrepo.save(member), UserDTO.class);
//		CommandLineExecutor.execute("C:\\Users\\multicampus\\Python\\Scripts\\python src/main/python/similarAnalysis.py " + memberDTO.getId() + "");
		CommandLineExecutor.execute("/usr/bin/python3 /home/ubuntu/similarAnalysis.py " + memberDTO.getId() + "");
		return memberDTO;
	}

	// 이메일 난수 만드는 메서드
	private String init() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;

		do {
			num = ran.nextInt(75) + 48;
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else {
				continue;
			}

		} while (sb.length() < size);
		if (lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}

	// 난수를 이용한 키 생성
	private boolean lowerCheck;
	private int size;

	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}

	@Override
	// 회원가입 발송 이메일(인증키 발송)
	public void mailSendWithUserKey(String email, String name) throws MessagingException {
		// 인증을 위한 user_key 발급
		String key = getKey(false, 20);
		User user = accountrepo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email입니다"));
		user.setAuthKey(key);
		accountrepo.save(user);
		// 인증 메일 전송
		MimeMessage mail = mailSender.createMimeMessage();
		String subject = "[본인인증] FindMe 인증메일입니다.";
		String text = "<h2>안녕하세요 FindMe 입니다!</h2><br><br>" + "<h3>" + name + "님</h3>"
				+ "<p>인증하기 버튼을 누르시면 로그인을 하실 수 있습니다 : " + "<a href= " + IP + "/" + email + "/" + key + ">인증하기</a></p>"
				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
		try {
			mail.setSubject(subject, "utf-8");
			mail.setText(text, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@Override
	// 인증 메일 확인
	public void alterUserKey(String email, String key) {
		System.out.println(email);
		System.out.println(key);
		User user = accountrepo.findByEmailAndAuthKey(email, key)
				.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 인증메일입니다."));
		user.setAuthKey("Y");
		accountrepo.save(user);
	}

	@Override
	public UserDTO login(UserDTO trial) {
		User member = accountrepo.findByEmail(trial.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email입니다."));
		if (!member.getAuthKey().equals("Y"))
			throw new IllegalArgumentException("인증되지 않은 계정입니다.");
		if (!trial.getPassword().equals(member.getPassword()))
			throw new IllegalArgumentException("잘못된 비밀번호입니다. 또는 카카오계정으로 시도해보세요.");
		return modelMapper.map(member, UserDTO.class);

	}

	@Override
	public String getToken(UserDTO user) {
		String token = jwtService.create(modelMapper.map(user, User.class));
		return token;
	}

	@Override
	public UserDTO info(String email) {
		return modelMapper.map(accountrepo.findByEmail(email).get(), UserDTO.class);
	}

	@Override
	public UserDTO changePassword(String email) {
		User user = accountrepo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email입니다"));
		String randomPassword = getRamdomPassword(10);
		user.setPassword(randomPassword);
		return modelMapper.map(user, UserDTO.class);
	}

	private static String getRamdomPassword(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를 Int로 추출 (소숫점제거)
			sb.append(charSet[idx]);
		}
		return sb.toString();
	}

	// 임시비밀번호 발송
	@Override
	public void mailSend(String email, String password, String name) throws MessagingException {
		MimeMessage mail = mailSender.createMimeMessage();
		String subject = "[임시비밀번호 발급] FindMe 임시 비밀번호 발급 메일입니다.";
		String text = "<h2>안녕하세요 FindMe 입니다!</h2><br><br>" + "<h3>" + name + "님</h3>" + "<p>임시비밀번호는 </p><br><h4>"
				+ password + "</h4><br><p>입니다.</p>" + "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
		try {
			mail.setSubject(subject, "utf-8");
			mail.setText(text, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public UserDTO updateProfile(UserDTO user) {
		return modelMapper.map(accountrepo.save(modelMapper.map(user, User.class)), UserDTO.class);
	}

	@Override
	public UserDTO findById(Long user_id) {
		return modelMapper.map(accountrepo.findById(user_id).get(), UserDTO.class);
	}

	@Override
	public UserDTO findbyEmail(String email) {
		User user = accountrepo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("가입된 계정이 아닙니다."));
		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public void deleteUser(UserDTO user) {
		User userEntity = modelMapper.map(user, User.class);
		accountrepo.delete(userEntity);
		
	}
}
