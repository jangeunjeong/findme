package com.ssafy.findme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.findme.dto.TextMiningDTO;
import com.ssafy.findme.mapper.EntityMapper;
import com.ssafy.findme.repository.TextMiningRepository;

@Service
public class TextMiningServiceImpl implements ITextMiningService {
	@Autowired
	private TextMiningRepository textminingrepo;
	
	@Autowired
	EntityMapper entityMapper;
	
	@Override
	public TextMiningDTO findById(Long id) {
		return entityMapper.convertToDomain(textminingrepo.findById(id).get(), TextMiningDTO.class);
	}
}
