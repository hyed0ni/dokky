package com.mcp.semi.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import com.mcp.semi.board.dto.BoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> getBoardList(BoardDto boardDto);
	int deleteBoard(int boardNo);
	BoardDto getBoardByNo(int boardNo);
	int updateBoard(BoardDto board);
	int updateHit(int boardNo);
	List<BoardDto> getHotBoardList(Map<String, Object> map);
}
