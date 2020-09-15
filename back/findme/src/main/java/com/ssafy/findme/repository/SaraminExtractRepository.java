package com.ssafy.findme.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.SaraminExtract;

@Repository
public interface SaraminExtractRepository extends JpaRepository<SaraminExtract, String> {

	@Transactional
	@Modifying
	@Query(value = "UPDATE Saramin_extract s set s.salary = :salary, s.salary_rank = :salary_rank where s.language = :language", nativeQuery = true)
	void update(@Param("salary") Long salary, @Param("salary_rank") Long salary_rank,
			@Param("language") String language);

}
