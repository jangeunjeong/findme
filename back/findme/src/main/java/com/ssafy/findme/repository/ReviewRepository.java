package com.ssafy.findme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	Optional<Review> findById(Long review_id);
	List<Review> findByUserId(Long user_id);
	List<Review> findByLanguageId(Long language_id);
	List<Review> findAllByLanguageId(Long langeuage_id);
	List<Review> findReviewByUserIdAndLanguageId(Long user_id, Long language_id);
}
