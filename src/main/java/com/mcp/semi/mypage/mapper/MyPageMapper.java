package com.mcp.semi.mypage.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {
	
	/** 비밀번호 변경
	 * @param pwMap
	 * @return result
	 */
	int modifyPw(Map<String, Object> pwMap);
	
}
