package com.ssafy.findme.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.findme.domain.Survey;
import com.ssafy.findme.domain.User;
import com.ssafy.findme.dto.SurveyDTO;
import com.ssafy.findme.dto.UserDTO;
import com.ssafy.findme.mapper.EntityMapper;
import com.ssafy.findme.repository.AccountRepository;
import com.ssafy.findme.repository.SurveyRepository;

@Service
public class SurveyServiceImpl implements ISurveyService {
	@Autowired
	private SurveyRepository surveyrepo;

	@Autowired
	private AccountRepository accountrepo;

	@Autowired
	private EntityMapper entityMapper;

	@Override
	public void save(SurveyDTO surveydto) {
		try {
			Survey survey = new Survey();
			User user = accountrepo.findById(surveydto.getUserId())
					.orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));
			;
			user.setId(surveydto.getUserId());
			survey.setCreatedAt(new Date());
			survey.setUser(user);
			survey.setAdvantage(surveydto.getAdvantage());
			survey.setDisadvantage(surveydto.getDisadvantage());
			survey.setTotalReview(surveydto.getTotalReview());
			survey.setUseReason(surveydto.getUseReason());
			survey.setLanguageId(surveydto.getLanguageId());
			surveyrepo.save(survey);
		} catch (Exception e) {
			System.out.println("SurveyService save error");
		}
	}

	@Override
	public String findByConfirm(Long user_id, Long language_id) {
		User user = accountrepo.findById(user_id).orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));
		;
		String[] tech_stacks = user.getTechStack().split(","); // Java,C
		String[] language = { "Java", "C", "Python", "C++", "C#", "VB.NET", "JavaScript", "PHP", "SQL", "Go", "R",
				"Assembly", "Swift", "Ruby", "MATLAB", "PL/SQL", "Perl", "Visual Basic", "Objective-C", "Delphi" };
		int index = (int) (long) language_id - 1;
		String languagePage = language[index];

		Survey survey = null;
		for (int i = 0; i < tech_stacks.length; i++) {
			if (tech_stacks[i].equals(languagePage)) {
				survey = surveyrepo.findByUserIdAndLanguageId(user_id, language_id);
				if (survey != null) {
					return "yes";
				} else
					return "no";
			}
		}
		return "not";
	}
}
