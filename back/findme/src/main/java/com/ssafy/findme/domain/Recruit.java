package com.ssafy.findme.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "recruit")
@Getter
@Setter
public class Recruit {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "comp_name")
	private String compName;
	private String title;

	@Lob
	@Column(name = "job_category")
	private String jobCategory;

	@Lob
	@Column(name = "tech_stack")
	private String techStack;

	private String url;
	private boolean utility;

	@Column(name = "post_date")
	private long postDate;
	@Column(name = "start_date")
	private long startDate;
	@Column(name = "due_date")
	private long dueDate;

	@Column(name = "img_url")
	private String imgUrl;

	@OneToMany(mappedBy = "recruit")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private List<Message> messageList = new ArrayList<>();

	@OneToMany(mappedBy = "recruit")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private List<Pick> pickList = new ArrayList<>();

}
