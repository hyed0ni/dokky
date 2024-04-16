package com.mcp.semi.user.service;

import org.springframework.stereotype.Service;

import com.mcp.semi.user.dto.UserDto;
import com.mcp.semi.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper userMapper;

	public void setSignin(UserDto user) {
		
		userMapper.setSignin(user);
	}

	public void setSignup(UserDto user) {
		userMapper.setSignup(user);
	}
	
	

}
