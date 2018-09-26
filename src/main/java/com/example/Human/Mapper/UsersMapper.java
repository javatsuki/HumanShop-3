package com.example.Human.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.Human.entity.Users;
import com.example.Human.service.LoginUser;

@Mapper
public interface UsersMapper {

	List<Users> selectAll();
	
	void insertUserInfo(Users users);
	
	
	Users selectLoginUser(LoginUser form);

	List<Users> selectLoginUser();
} 

