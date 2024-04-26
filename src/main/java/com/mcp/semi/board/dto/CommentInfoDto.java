package com.mcp.semi.board.dto;

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
