package com.ssafy.findme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.findme.domain.GoogleTrendGeoData;

public interface GoogleTrendGeoRepository extends JpaRepository<GoogleTrendGeoData, Long>{

}
