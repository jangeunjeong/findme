package com.ssafy.findme.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecruitDTO {
	private Long id;
	private String compName;
	private String title;
	private String jobCategory;
	private String techStack;
	private String url;
	private boolean utility;
	private long postDate;
	private long startDate;
	private long dueDate;
	private String imgUrl;
}
