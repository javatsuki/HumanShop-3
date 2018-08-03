package com.example.Human.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity(name="Users")
@Getter
@Setter
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer Id;
	
	@NotNull
	protected String Name;
	
	
//	@Column
//	protected String password;
//	@Column
//	protected String address1;
//	@Column
//	protected String address2;
//	@Column
//	protected String address3;
//	@Column
//	protected Date makeTime;
//	@Column
//	protected Date updateTime;
}
	