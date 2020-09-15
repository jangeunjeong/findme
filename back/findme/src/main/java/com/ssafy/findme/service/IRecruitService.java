package com.ssafy.findme.service;

import java.util.List;

import com.ssafy.findme.dto.RecruitDTO;

public interface IRecruitService {
	public void itemBased();

	public List<RecruitDTO> getMatchRecruit(String userId);

	public List<RecruitDTO> getPickRecruit(String userId);

	public List<RecruitDTO> getRecommendRecruit(String userId);

	public List<String> getRecommendLanguage(String userId, List<RecruitDTO> matchRecruitList);
	
	public List<RecruitDTO> getCarouselRecruit();

}
