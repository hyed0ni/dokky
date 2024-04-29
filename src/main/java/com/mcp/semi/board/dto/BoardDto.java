package com.mcp.semi.board.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

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
	private Timestamp boardCreateDt;
	private Timestamp boardModifyDt;
	private UserDto user;
	private int cmtNo;
	private List<CommentDto> comments;


}