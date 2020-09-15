package com.ssafy.findme.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.Recruit;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Recruit r WHERE r.due_date < :due_date", nativeQuery = true)
	public void deleteByDueDate(@Param("due_date") Long due_date);

	public Optional<Recruit> findById(Long parseLong);

	@Transactional
	@Modifying
	public List<Recruit> deleteByDueDateBefore(Long now);

	@Query(value = "SELECT MAX(ID) FROM RECRUIT", nativeQuery = true)
	public long findMaxId();

}
