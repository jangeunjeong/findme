package com.ssafy.findme.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
	private Long id;
	private String email;
	private String password;
	private String name;
	private String techStack;
	private String wishHope;
	private String wishJob;
	private String authKey;
	private RoleType roleType;
	private Date createdAt;

	public enum RoleType {
		USER, ADMIN
	}
	private boolean check;
	private boolean checkSymp;
	private boolean checkUnsymp;
}
