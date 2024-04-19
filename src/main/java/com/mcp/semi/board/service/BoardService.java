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

	public int deleteBoard(int boardNo) {
      return boardMapper.deleteBoard(boardNo);
	}
	
	public BoardDto getBoardByNo(int boardNo) {
		
		return boardMapper.getBoardByNo(boardNo);
	} 

	
	public int modifyBoard(BoardDto board) {
		System.out.println(board);
		return boardMapper.updateBoard(board);
	}
	
	public int updateHit(int boardNo) {
		return boardMapper.updateHit(boardNo);
	}
}
