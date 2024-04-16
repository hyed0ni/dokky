package com.mcp.semi.user.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccessHistoryDto {
	
	private int accessHistoryNo;
	private String userEmail;
	private String accessIp;
	private String userAgent;
	private String sessionId;
	private Date loginDt;
	private Date logoutDt;

}
