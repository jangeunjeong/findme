package com.ssafy.findme.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="review")
public class Review {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@NotNull
	private String content;
	
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	private Long sympCnt;
	private Long unsympCnt;
	private Long languageId;
	
	@OneToMany(mappedBy = "review")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Symp> sympList = new ArrayList<>();
	
	@OneToMany(mappedBy = "review")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Unsymp> unsympList = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
