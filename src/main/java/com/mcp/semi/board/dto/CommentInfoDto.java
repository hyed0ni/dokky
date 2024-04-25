package com.mcp.semi.board.dto;

import java.sql.Date;
import java.sql.Timestamp;

import com.mcp.semi.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentInfoDto {
	private String commentContent;
	private int userNo;
	private int boardNo;
}
