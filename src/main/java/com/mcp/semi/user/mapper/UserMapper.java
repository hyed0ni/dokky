package com.mcp.semi.user.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mcp.semi.user.dto.UserDto;

@Mapper
public interface UserMapper {

	UserDto findByUserNo(int userNo);
	int insertUser(UserDto user);
  int deleteUser(int userNo);
  int insertAccessHistory(Map<String, Object> map);
  int updateAccessHistory(String sessionId);
	UserDto getUserByMap(Map<String, Object> map);
	Object getLeaveUserByMap(Map<String, Object> params);
	
}