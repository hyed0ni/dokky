package com.mcp.semi.comment.dto;

import java.sql.Timestamp;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	
	private int cmtNo;
	private String cmtContent;
	private Timestamp createDt;
	private Timestamp modifyDt;
	private UserDto userDto;

}
