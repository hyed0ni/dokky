package com.mcp.semi.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mcp.semi.board.dto.BoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> getBoardList(Map<String, Object> map);
	int deleteBoard(int boardNo);
	BoardDto getBoardByNo(int boardNo);
}
