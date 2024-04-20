package com.mcp.semi.mypage.mapper;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mcp.semi.board.dto.BoardDto;

@Mapper
public interface MyPageMapper {
  	
	/** 비밀번호 변경
	 * @param pwMap
	 * @return result
	 */
	int modifyPw(Map<String, Object> pwMap);
  
	List<BoardDto> getPostsByUserId(int userNo);
  
}
