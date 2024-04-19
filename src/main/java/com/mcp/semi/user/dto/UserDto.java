package com.mcp.semi.user.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
	private Date loginDt;
	private Date logoutDt;
}
