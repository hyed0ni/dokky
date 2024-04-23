package com.mcp.semi.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mcp.semi.common.exception.UserNotFoundException;
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
	
	public UserDto findByUserNo(int userNo) {
		UserDto user = userMapper.findByUserNo(userNo);
		
		return Optional.ofNullable(user)
				.orElseThrow(() -> new UserNotFoundException("사용자가 존재하지 않습니다."));
	}
	
}
