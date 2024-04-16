package com.mcp.semi.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mcp.semi.user.dto.UserDto;

@Mapper
public interface UserMapper {

	void setSignin(UserDto user);
	void setSignup(UserDto user);
	void setSignout(UserDto user);
	
}