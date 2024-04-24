package com.mcp.semi.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcp.semi.user.dto.UserDto;
import com.mcp.semi.user.mapper.UserMapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	
	private final HttpSession session;
	private final UserMapper userMapper;
	
	private final static String USER = "user";
	
	// 현재 세션에 유저가 있는지 없는지 확인하여 인증 여부 반환
	public boolean isAuthenticated() {
		return session.getAttribute(USER) != null;
	}
	
	// 해당 userNo를 통해 사용자가 있는지 없는지 확인
	@Transactional(readOnly = true)
	public boolean userExist(int userNo) {
		UserDto user = userMapper.findByUserNo(userNo);
		return user != null;
	}
	
	// 현재 세션의 사용자의 userNo와 URL에 입력한 uesrNo가 같은지 확인
	public boolean inOwnprofile(int userNo) {
		UserDto currentUser = (UserDto) session.getAttribute(USER) ;
		return currentUser != null && currentUser.getUserNo() == userNo;
	}
}
