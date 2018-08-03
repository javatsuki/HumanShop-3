package com.example.Human.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.Human.entity.Users;

@Mapper
public interface UsersMapper {

	List<Users> selectAll();
	
}
