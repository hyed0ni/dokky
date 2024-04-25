package com.mcp.semi.mypage.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageDto {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private Timestamp boardCreateDt;
	private Timestamp boardModifyDt;
	private int commentNo;
	private int userNo;
	private String userName;
}
