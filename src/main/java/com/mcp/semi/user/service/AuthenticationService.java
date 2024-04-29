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
	private final static String USER = "user";
	
	// 현재 세션에 유저가 있는지 없는지 확인하여 인증 여부 반환
	public boolean isAuthenticated() {
		return session.getAttribute(USER) != null;
	}
	
	public UserDto getCurrentUser(HttpSession session) {
		return (UserDto) session.getAttribute(USER);
	}
}
