package com.mcp.semi.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardMapper boardMapper;

	public List<BoardDto> getBoardList(BoardDto boardDto) {
		return boardMapper.getBoardList(boardDto);
	}

	public ResponseEntity<Map<String,Object>> deleteBoard(int boardNo) {
      int deleteCount = boardMapper.deleteBoard(boardNo);
      return new ResponseEntity<Map<String,Object>>(Map.of("deleteCount", deleteCount), HttpStatus.OK);
	}
	
	public BoardDto getBoardByNo(int boardNo)
	{
		return boardMapper.getBoardByNo(boardNo);
	}
}
