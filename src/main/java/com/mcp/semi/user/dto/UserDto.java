package com.mcp.semi.user.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
	
	private int userNo;
	private String userEmail;
	private String userPw;
	private String userName;
	private String userGender;
	private String userMobile;
	private Date loginDt;
	private Date logoutDt;
	private String userImg;
	private String userUploadPath;
	private Date withdrawalDt;
	
}
