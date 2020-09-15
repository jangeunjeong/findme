package com.ssafy.findme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.Unsymp;

@Repository
public interface UnsympRepository extends JpaRepository<Unsymp, Long> {
	Unsymp findByReviewIdAndUserId(Long review_id, Long user_id);
	List<Unsymp> findByUserId(Long user_id);
}
