package com.mcp.semi.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.common.page.PageResponse;

@Mapper
public interface BoardMapper {
	
	int insertBoard(BoardDto board);
	List<BoardDto> getBoardList(BoardDto boardDto);
	List<BoardDto> getBoardList(Map<String, Object> map);
	int deleteBoard(int boardNo);
	BoardDto getBoardByNo(int boardNo);
	int updateBoard(BoardDto board);
	int updateHit(int boardNo);
	List<BoardDto> getHotBoardList(Map<String, Object> map);
	BoardDto getBoardUpdateList(BoardDto boardDto);
	int getBoardUpdate(BoardDto boardDto);
	int getTotalCount();
}
	
