package com.mcp.semi.board.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.board.dto.CommentDto;
import com.mcp.semi.board.dto.CommentInfoDto;
import com.mcp.semi.board.mapper.BoardMapper;
import com.mcp.semi.board.mapper.CommentMapper;
import com.mcp.semi.user.dto.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentMapper commentMapper;

	@Transactional(readOnly=true)
	public List<CommentDto> getCommentList(Model model, int boardNo) 
	{
		List<CommentDto> cmtList = commentMapper.getCommentList(boardNo);
		model.addAttribute("commentList", cmtList);
		return commentMapper.getCommentList(boardNo);
	}

	@Transactional
	public int insertComment(CommentInfoDto commentInfoDto)
	{
		String comment = commentInfoDto.getCommentContent();
		int userNo = commentInfoDto.getUserNo();
		int boardNo = commentInfoDto.getBoardNo();
		UserDto user = new UserDto();
		user.setUserNo(userNo);
		
		BoardDto board = new BoardDto();
		board.setBoardNo(boardNo);
		
		CommentDto commentdto = CommentDto.builder()
							.commentContent(comment)
							.user(user)
							.board(board)
							.build();
		
		return commentMapper.insertComment(commentdto);
	}
	
	@Transactional
	public int deleteComment(int commentNo) 
	{
		return commentMapper.deleteComment(commentNo);
	}

	@Transactional
	public int modifyComment(CommentInfoDto commentInfoDto) 
	{
		return commentMapper.modifyComment(commentInfoDto);
	}
}
