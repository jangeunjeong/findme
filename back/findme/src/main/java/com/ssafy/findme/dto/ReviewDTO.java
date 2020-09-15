package com.ssafy.findme.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewDTO {
	private Long id;
	private String content;
	private Date createdAt;
	private Date updatedAt;
	private Long sympCnt;
	private Long unsympCnt;
	private UserDTO user;
	private Long languageId;
	private String name;
	
	private String trans_createdAt;
	private String trans_updatedAt;
}
