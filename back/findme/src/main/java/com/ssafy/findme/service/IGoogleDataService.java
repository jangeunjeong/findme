package com.ssafy.findme.service;

import java.util.List;

import com.ssafy.findme.dto.GoogleTrendDataDTO;
import com.ssafy.findme.dto.GoogleTrendGeoDataDTO;

public interface IGoogleDataService {
	List<GoogleTrendDataDTO> findAllByDate();
	List<GoogleTrendGeoDataDTO> findAllByGeo();
}
