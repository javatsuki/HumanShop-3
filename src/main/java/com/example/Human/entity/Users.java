package com.example.Human.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Users")

@Setter
@Getter
public class Users {

  @Id
  protected Integer user_id;
  @Column
  protected String user_name;
  @Column
  protected String password;
  @Column
  protected Integer address_1;
  @Column
  protected Integer address_2;
  @Column
  protected Integer address_3;
  @Column
  protected Integer make_time;
  @Column
  protected Date update_time;

}
