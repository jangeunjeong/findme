package com.ssafy.findme.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.findme.domain.Pick;
import com.ssafy.findme.domain.Recruit;
import com.ssafy.findme.domain.User;
import com.ssafy.findme.dto.PickDTO;
import com.ssafy.findme.mapper.EntityMapper;
import com.ssafy.findme.repository.AccountRepository;
import com.ssafy.findme.repository.PickRepository;
import com.ssafy.findme.repository.RecruitRepository;

@Service
public class PickServiceImpl implements IPickService {
	
	@Autowired
	private PickRepository pickrepo;
	@Autowired
	private AccountRepository accountrepo;
	@Autowired
	private RecruitRepository recruitrepo;
	@Autowired
	private EntityMapper entityMapper;

	@Override
	public void savePick(Long user_id, Long recruit_id) {
		Pick pick = new Pick();
		User user = accountrepo.findById(user_id).get();
		pick.setUser(user);
		Recruit recruit = recruitrepo.findById(recruit_id).get();
		pick.setRecruit(recruit);
		pickrepo.save(pick);
	}

	@Override
	public List<PickDTO> findAllByUserId(Long user_id) {
		List<PickDTO> pickList = pickrepo.findByUserId(user_id).stream().map(e->entityMapper.convertToDomain(e, PickDTO.class))
				.collect(Collectors.toList());
		for (int i = 0; i < pickList.size(); i++) {
			PickDTO pick = pickList.get(i);
			Pick findPick = pickrepo.findByUserIdAndRecruitId(pick.getUserId(), pick.getRecruit().getId());
			if(findPick != null)
				pick.setChekcPick(true);
			else
				pick.setChekcPick(false);
		}
		return pickList;
	}

	@Override
	public void deletePick(Long user_id, Long recruit_id) {
		Pick pick = pickrepo.findByUserIdAndRecruitId(user_id, recruit_id);
		try {
			if(pick != null)
				pickrepo.delete(pick);
			else
				System.out.println("찜 해제 실패");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
