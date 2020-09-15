package com.ssafy.findme.service;

import java.util.List;

import com.ssafy.findme.dto.PickDTO;

public interface IPickService {
	void savePick(Long user_id, Long recruit_id);
	List<PickDTO> findAllByUserId(Long user_id);
	void deletePick(Long user_id, Long recruit_id);
}
