package com.ssafy.findme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

@Entity
@Getter
@Setter
@ToString
@Table(name = "symp")
public class Symp {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@NotNull
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "review_id")
	@NotNull
	private Review review;
}
