package com.mcp.semi.board.dto;

import java.sql.Date;

import com.mcp.semi.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
	private int commentNo;
	private String commentContent;
	private Date cmtCreateDt;
	private Date cmtModifyDt;
	private UserDto user;
	private BoardDto board;
}
