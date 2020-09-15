package com.ssafy.findme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "saramin_data")
@Getter
@Setter

public class SaraminData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "comp_name")
	private String compName;
	private boolean utility;
	@Column(name = "post_date")
	private long postData;
	@Column(name = "due_date")
	private long dueDate;

	private Long salary;

	@Column(name = "`Java`")
	private boolean javas;

	@Column(name = "`C`")
	private boolean cs;

	@Column(name = "`Python`")
	private boolean pythons;

	@Column(name = "`C++`")
	private boolean cpps;

	@Column(name = "`C#`")
	private boolean csharps;

	@Column(name = "`VB.NET`")
	private boolean vbns;

	@Column(name = "`JavaScript`")
	private boolean jss;

	@Column(name = "`PHP`")
	private boolean phps;

	@Column(name = "`SQL`")
	private boolean sqls;

	@Column(name = "`Go`")
	private boolean gos;

	@Column(name = "`R`")
	private boolean rs;

	@Column(name = "`Assembly`")
	private boolean assemblys;

	@Column(name = "`Swift`")
	private boolean swifts;

	@Column(name = "`Ruby`")
	private boolean rubys;

	@Column(name = "`MATLAB`")
	private boolean matlabs;

	@Column(name = "`PL/SQL`")
	private boolean pls;

	@Column(name = "`Perl`")
	private boolean perls;

	@Column(name = "`Visual Basic`")
	private boolean vbs;

	@Column(name = "`Objective-C`")
	private boolean ocs;

	@Column(name = "`Delphi`")
	private boolean delphis;
}
