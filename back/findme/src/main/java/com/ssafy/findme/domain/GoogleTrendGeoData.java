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
@Table(name = "gtrend_geo_data")
@Getter
@Setter

public class GoogleTrendGeoData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private String geoName;
	
	@Column(name = "`Java`")
	private Long javas;
	
	@Column(name = "`C`")
	private Long cs;
	
	@Column(name = "`Python`")
	private Long pythons;
	
	@Column(name = "`C++`")
	private Long cpps;
	
	@Column(name = "`C#`")
	private Long csharps;
	
	@Column(name = "`VB.NET`")
	private Long vbns;
	
	@Column(name = "`JavaScript`")
	private Long jss;
	
	@Column(name = "`PHP`")
	private Long phps;
	
	@Column(name = "`SQL`")
	private Long sqls;
	
	@Column(name = "`Go`")
	private Long gos;
	
	@Column(name = "`R`")
	private Long rs;
	
	@Column(name = "`Assembly`")
	private Long assemblys;
	
	@Column(name = "`Swift`")
	private Long swifts;
	
	@Column(name = "`Ruby`")
	private Long rubys;
	
	@Column(name = "`MATLAB`")
	private Long matlabs;
	
	@Column(name = "`PL/SQL`")
	private Long pls;
	
	@Column(name = "`Perl`")
	private Long perls;
	
	@Column(name = "`Visual Basic`")
	private Long vbs;
	
	@Column(name = "`Objective-C`")
	private Long ocs;
	
	@Column(name = "`Delphi`")
	private Long delphis;
}
