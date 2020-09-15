package com.ssafy.findme.dto;

import lombok.Setter;
import lombok.ToString;

import java.util.Date;

import lombok.Getter;

@Getter
@Setter
@ToString
public class SurveyDTO {
	private Long id;
	private Long useReason;
	private Long advantage;
	private Long disadvantage;
	private String totalReview;
	private Date createdAt;
	private Long userId;
	private Long languageId;
}
