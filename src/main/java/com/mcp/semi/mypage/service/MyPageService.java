package com.mcp.semi.mypage.service;
	
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.common.page.PageResponse;
import com.mcp.semi.mypage.mapper.MyPageMapper;
import com.mcp.semi.user.dto.UserDto;
import com.mcp.semi.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageService {
	
	private final UserService userService;
	private final MyPageMapper myPageMapper;
	
	// 사용자 프로필 조회
	@Transactional(readOnly = true)
    public UserDto getUserProfile(int userNo) {
        return userService.findByUserNo(userNo);
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
    public PageResponse<BoardDto> getUserBoards(int userNo, int page, int cnt) {
    	int totalCount = myPageMapper.countBoardsByUserNo(userNo);
    	int total = totalCount / cnt + ((totalCount % cnt > 0) ? 1 : 0);
    	int startPage = Math.max(page - 2, 1);
		int endPage = Math.min(page + 2,  total);
		int begin = (page - 1) * cnt + 1;
		int end = begin + cnt - 1;
		
		List<BoardDto> items = myPageMapper.getBoardsByUserNo(userNo, begin, end);
		return new PageResponse<>(items, page ,total, startPage, endPage);
    }
    
    // 내가 작성한 댓글 조회
    @Transactional(readOnly = true)
    public PageResponse<BoardDto> getUserBoardsWithComments(int userNo, int page, int cnt) {
    	int totalCount = myPageMapper.countCommentsByUserNo(userNo);
    	int total = totalCount / cnt + ((totalCount % cnt > 0) ? 1 : 0);
    	int startPage = Math.max(page - 2, 1);
		int endPage = Math.min(page + 2,  total);
		int begin = (page - 1) * cnt + 1;
		int end = begin + cnt - 1;
		
		List<BoardDto> items = myPageMapper.getBoardsWithCommentsByUserNo(userNo, begin, end);
		return new PageResponse<>(items, page ,total, startPage, endPage);
    }

}
