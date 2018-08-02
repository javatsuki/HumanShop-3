package com.example.Human.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name="Users")

@Setter
@Getter
public class Users {
	
	@Id
	protected String userId;
	@Column
	protected String userName;
	@Column
	protected String password;
	@Column
	protected String address1;
	@Column
	protected String address2;
	@Column
	protected String address3;
	@Column
	protected Date makeTime;
	@Column
	protected Date updateTime;
}
	