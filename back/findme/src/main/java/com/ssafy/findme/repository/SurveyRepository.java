package com.ssafy.findme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long>{
	List<Survey> findByLanguageId(Long language_id);
	Survey findByUserIdAndLanguageId(Long user_id, Long language_id);
}
