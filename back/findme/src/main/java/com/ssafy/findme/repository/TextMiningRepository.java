package com.ssafy.findme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.TextMining;

@Repository
public interface TextMiningRepository extends JpaRepository<TextMining, Long>{

}
