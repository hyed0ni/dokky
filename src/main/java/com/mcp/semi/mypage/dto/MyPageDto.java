package com.mcp.semi.mypage.dto;

import java.util.List;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageDto {
	
	private UserDto userDto;
	private List<BoardDto> boardDto;
	
	
}
