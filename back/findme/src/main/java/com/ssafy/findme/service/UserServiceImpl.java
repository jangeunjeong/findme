package com.ssafy.findme.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.findme.domain.User;
import com.ssafy.findme.dto.UserDTO;
import com.ssafy.findme.repository.AccountRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private AccountRepository accountrepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO findById(Long id) {
		return modelMapper.map(accountrepo.findById(id).get(), UserDTO.class);
	}

	@Override
	public UserDTO updateProfile(UserDTO user) {
		return modelMapper.map(accountrepo.save(modelMapper.map(user, User.class)), UserDTO.class);

	}

}
