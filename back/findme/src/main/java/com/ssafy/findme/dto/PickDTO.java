package com.ssafy.findme.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PickDTO {
	private Long id;
	private Long userId;
	private RecruitDTO recruit;
	private boolean chekcPick;
}
