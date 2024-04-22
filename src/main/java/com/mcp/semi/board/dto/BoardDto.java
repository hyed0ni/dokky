package com.mcp.semi.board.dto;

import java.sql.Timestamp;
import java.util.List;

import com.mcp.semi.comment.dto.CommentDto;
import com.mcp.semi.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private int boardHit;
	private Timestamp createDt;
	private Timestamp modifyDt;
	private UserDto user;
	private List<CommentDto> comments;
	
	public BoardDto(int boardNo, String boardTitle, int boardHit, Timestamp createDt, UserDto user) {
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardHit = boardHit;
		this.createDt = createDt;
		this.user = user; 
		
	}
}