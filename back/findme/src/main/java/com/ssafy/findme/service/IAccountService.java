package com.ssafy.findme.service;

import javax.mail.MessagingException;

import com.ssafy.findme.dto.UserDTO;

public interface IAccountService {
	UserDTO signUp(UserDTO user);

	void mailSendWithUserKey(String email, String name) throws MessagingException;

	void alterUserKey(String email, String key);

	boolean emailDuplicateCheck(String email);

	boolean accountDuplicateCheck(String email, String password);

	UserDTO login(UserDTO user);

	String getToken(UserDTO user);

	UserDTO info(String email);

	UserDTO changePassword(String email);

	void mailSend(String email, String password, String name) throws MessagingException;

	UserDTO updateProfile(UserDTO user);

	UserDTO findById(Long user_id);

	UserDTO findbyEmail(String email);

	void deleteUser(UserDTO user);
}
