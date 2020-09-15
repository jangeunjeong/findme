package com.ssafy.findme.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SaraminExtractDTO {

	private String language;
	private long adopted;
	private long adoptedRank;
	private long salary;
	private long salaryRank;
}
