package com.mcp.semi.mypage.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.mypage.mapper.MyPageMapper;
import com.mcp.semi.user.dto.UserDto;
import com.mcp.semi.user.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MyPageServiceTest {

    @Autowired
    private MyPageService myPageService;
    
    @Autowired
    private MyPageMapper myPageMapper;
    
    @Autowired
    private UserMapper userMapper;

    @Test
    void 사용자_조회() {
    	int userNo = 1;
    	String userName = "tester";
//    	UserDto userById = userMapper.findUserById(userNo);
    	UserDto userProfile = myPageService.getUserProfile(userNo);
    	log.info("userNo: {}, userName: {}", userProfile.getUserNo(), userProfile.getUserName());
    	assertThat(userProfile.getUserNo()).isEqualTo(1);
    	assertThat(userProfile.getUserName()).isEqualTo(userName);
    	
    }
//    
//    @Test
//    void 작성한_글_조회() {
//    	int userNo = 2;
//    	List<BoardDto> result = myPageService.getUserBoards(userNo);
//    	log.info("result {}", result);
//    	
//    	
//    }
//    
//    @Test
//    void 댓글단_글_조회() {
//    	int userNo = 1;
//    	List<BoardDto> result = myPageService.getUserBoardsWithComments(userNo);
//    	log.info("result: {}", result);
//    }

}


