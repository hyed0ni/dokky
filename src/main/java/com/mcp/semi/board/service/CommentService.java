package com.mcp.semi.board.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.board.dto.CommentDto;
import com.mcp.semi.board.mapper.BoardMapper;
import com.mcp.semi.board.mapper.CommentMapper;
import com.mcp.semi.user.dto.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentMapper commentMapper;

	public List<CommentDto> getCommentList(CommentDto commentDto) {
		return commentMapper.getCommentList(commentDto);
	}

	public int insertComment(HttpServletRequest request)
	{
		String comment = request.getParameter("comment");
		int userNo = 1;
		int boardNo = 4;
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@" + comment);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@" + boardNo);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@" + userNo);
		
		
		UserDto user = new UserDto();
		user.setUserNo(userNo);
		
		BoardDto board = new BoardDto();
		board.setBoardNo(boardNo);
		
		Date date = new Date(System.currentTimeMillis());
		
		CommentDto commentDto = CommentDto.builder()
							.commentContent(comment)
							.cmtCreateDt(date)
							.cmtModifyDt(date)
							.user(user)
							.board(board)
							.build();
		
		return commentMapper.insertComment(commentDto);
	}
	
	public int deleteComment(int boardNo) {
      return commentMapper.deleteComment(boardNo);
	}
	
	public int modifyComment(int board) {
		return commentMapper.modifyComment(board);
	}
	
	public CommentDto getCommentByNo(int boardNo) {
		return commentMapper.getCommentByNo(boardNo);
	} 
}
