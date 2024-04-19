package com.mcp.semi.mypage.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mcp.semi.mypage.mapper.MyPageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageService {
	
	private final MyPageMapper myPageMapper;
		
	/** 비밀번호 변경
	 * @param pwMap
	 * @return result
	 */
	public int modifyPw(Map<String, Object> pwMap) {
		return myPageMapper.modifyPw(pwMap);
	}
	
}
