package com.ssafy.findme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.Symp;

@Repository
public interface SympRepository extends JpaRepository<Symp, Long>{
	Symp findByReviewIdAndUserId(Long review_id, Long user_id);
	List<Symp> findByUserId(Long user_id);
}
