package com.mcp.semi.mypage.service;
	
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.mypage.mapper.MyPageMapper;
import com.mcp.semi.user.dto.UserDto;
import com.mcp.semi.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class MyPageService {
	
	private final MyPageMapper myPageMapper;
	private final UserMapper userMapper;
	
	// 사용자 프로필 조회
	@Transactional(readOnly = true)
    public UserDto getUserProfile(int userNo) {
        return userMapper.findUserById(userNo);
    }
	
	/** 
     * 비밀번호 변경
     * 
	 * @param pwMap
	 * @return result
	 */
    @Transactional
	public int modifyPw(Map<String, Object> pwMap) {
		return myPageMapper.modifyPw(pwMap);
	}

    /**
     * 계정 삭제: 탈퇴일 수정
     * 
     * @param removeUserMap
     * @return result
     */
    @Transactional
    public int removeUser(Map<String, Object> removeUserMap) {
    	return myPageMapper.removeUser(removeUserMap);
    }
    
    /**
     * 계정 삭제: 데이터 삭제
     * 
	 * @return result
	 */
    @Transactional
	public int deleteUserData() {
		return myPageMapper.deleteUserData();
	}
    
    // 내가 작성한 글 조회
    @Transactional(readOnly = true)
    public List<BoardDto> getUserBoards(int userNo) {
        return myPageMapper.getBoardsByUserNo(userNo);
    }
    
    // 내가 작성한 댓글 조회
    @Transactional(readOnly = true)
    public List<BoardDto> getUserBoardsWithComments(int userNo) {
    	return myPageMapper.getBoardsWithCommentsByUserNo(userNo);
    }

}
