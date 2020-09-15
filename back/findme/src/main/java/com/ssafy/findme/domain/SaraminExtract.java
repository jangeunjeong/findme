package com.ssafy.findme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "saramin_extract")
@Getter
@Setter
public class SaraminExtract {

	@Id
	@Column(name = "language")
	private String language;

	private long adopted;
	@Column(name = "adopted_rank")
	private long adoptedRank;
	private long salary;
	@Column(name = "salary_rank")
	private long salaryRank;

}
