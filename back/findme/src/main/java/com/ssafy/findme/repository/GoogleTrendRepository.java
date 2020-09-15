package com.ssafy.findme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.GoogleTrendData;

@Repository
public interface GoogleTrendRepository extends JpaRepository<GoogleTrendData, Long>{

}
