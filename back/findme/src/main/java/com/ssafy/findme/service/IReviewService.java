package com.ssafy.findme.service;

import java.util.List;

import com.ssafy.findme.dto.ReviewDTO;

public interface IReviewService {
	void save(Long user_id, String content, Long language_id);

	void update(Long review_id, String content);

	void delete(Long review_id);

	List<ReviewDTO> findAllByLanauageId(Long language_id, Long user_id);

	void recountSympAndUnsymp(Long user_id);

	boolean saveSymp(Long review_id, Long user_id);

	void deleteSymp(Long review_id, Long user_id);

	boolean saveUnsymp(Long review_id, Long user_id);

	void deleteUnsymp(Long review_id, Long user_id);
}
