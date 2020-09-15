package com.ssafy.findme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.Pick;

@Repository
public interface PickRepository extends JpaRepository<Pick, Long> {
	public List<Pick> findByUserId(Long userId);

	Pick findByUserIdAndRecruitId(Long user_id, Long recruit_id);

	public long countByRecruitId(long l);
}
