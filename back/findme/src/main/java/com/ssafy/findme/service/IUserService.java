package com.ssafy.findme.service;

import com.ssafy.findme.dto.UserDTO;

public interface IUserService {

	UserDTO findById(Long id);

	UserDTO updateProfile(UserDTO user);

}
