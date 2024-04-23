package com.mcp.semi.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mcp.semi.mypage.service.MyPageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RemoveUserScheduler {
	
	@Autowired
	private MyPageService myPageService;

	// 탈퇴 회원 데이터 삭제 스케줄러
	@Scheduled(cron = "*/10 * * * * *") // 매 10초마다 실행
	public void deleteUserData() {
		log.info("탈퇴 회원 데이터 삭제 스케줄러 실행");
		int result = myPageService.deleteUserData();
		log.info("탈퇴 회원 데이터 삭제 완료: {}명", result);
	}
	
}
