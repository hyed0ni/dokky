package com.mcp.semi.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mcp.semi.board.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	int insertBoard(BoardDto board);
	List<BoardDto> getBoardList(BoardDto boardDto);
	int deleteBoard(int boardNo);
	BoardDto getBoardByNo(int boardNo);
	int updateBoard(BoardDto board);
	int updateHit(int boardNo);
	BoardDto getBoardUpdateList(BoardDto boardDto);
	int getBoardUpdate(BoardDto boardDto);
}
