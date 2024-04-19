package com.mcp.semi.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mcp.semi.board.dto.BoardDto;

@Mapper
public interface MyPageMapper {

	List<BoardDto> getPostsByUserId(int userNo);
}
