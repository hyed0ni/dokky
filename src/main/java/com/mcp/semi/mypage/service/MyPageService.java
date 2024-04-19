package com.mcp.semi.mypage.service;
	
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.mypage.mapper.MyPageMapper;
import com.mcp.semi.user.dto.UserDto;
import com.mcp.semi.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {

	
	private final MyPageMapper myPageMapper;
	private final UserMapper userMapper;
	
	 //사용자 프로필 조회
    public UserDto getUserProfile(int userNo) {
        return userMapper.findUserById(userNo);
    }
    
    //내가 작성한 글 조회
    public List<BoardDto> getUserPosts(int userNo) {
        return myPageMapper.getPostsByUserId(userNo);
    }
  
  /** 비밀번호 변경
	 * @param pwMap
	 * @return result
	 */
	public int modifyPw(Map<String, Object> pwMap) {
		return myPageMapper.modifyPw(pwMap);
  }
}
