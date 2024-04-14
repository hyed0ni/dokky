package com.mcp.semi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private int userNo;
	private String userEmail;
	private String userPw;
	private String userName;
	private String userGender;
	private String userMobile;

}
