package com.mcp.semi.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.board.dto.CommentDto;
import com.mcp.semi.board.dto.CommentInfoDto;

@Mapper
public interface CommentMapper {
	List<CommentDto> getCommentList(int boardNo);	
	int insertComment(CommentDto commentDto);
	int deleteComment(int commentNo);
	int modifyComment(CommentInfoDto commentInfoDto);
}
