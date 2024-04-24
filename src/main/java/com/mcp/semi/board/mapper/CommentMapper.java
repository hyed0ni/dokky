package com.mcp.semi.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.board.dto.CommentDto;

@Mapper
public interface CommentMapper {
	List<CommentDto> getCommentList(CommentDto commentDto);	
	int insertComment(CommentDto commentDto);
	int deleteComment(int boardNo);
	int modifyComment(int boardNo);
	CommentDto getCommentByNo(int boardNo);
	int getTotalCount();
}
