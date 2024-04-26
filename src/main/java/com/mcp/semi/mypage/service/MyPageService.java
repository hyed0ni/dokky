package com.mcp.semi.mypage.service;
	
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.common.page.PageResponse;
import com.mcp.semi.common.util.FileUtils;
import com.mcp.semi.common.util.SecurityUtils;
import com.mcp.semi.mypage.mapper.MyPageMapper;
import com.mcp.semi.user.dto.UserDto;
import com.mcp.semi.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageService {
	
	private final UserService userService;
	private final MyPageMapper myPageMapper;
	private final FileUtils fileUtils;
	
	// 사용자 프로필 조회
	@Transactional(readOnly = true)
    public UserDto getUserProfile(int userNo) {
        return userService.findByUserNo(userNo);
    }
	
	/**
	 * 회원 정보 수정
	 * 
	 * @param userMap
	 * @return result
	 */
	@Transactional
	public int modifyUser(Map<String, Object> userMap) {
		
		MultipartFile profileImg = (MultipartFile)userMap.get("profileImg");
		
		String userUploadPath = "/dokky/user/";
		String userImg = null;
		
		if (!profileImg.isEmpty()) {
			userImg = fileUtils.getFileSystemName(profileImg.getOriginalFilename());
			userMap.put("userImg", userImg);
			userMap.put("userUploadPath", userUploadPath);
		}
		
		int result = myPageMapper.modifyUser(userMap);
		
		// 프로필 이미지 저장 경로
		String folderPath = "C:/GDJ77/mcp/user_img/";
		
		// 프로필 이미지 저장
		if (result == 1 && !profileImg.isEmpty()) {
			try {
				profileImg.transferTo(new File(folderPath + userImg));
				
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("프로필 이미지 저장 예외 발생");
			}
		}
		
		return result;
		
	}
	
	/** 
     * 비밀번호 변경
     * 
	 * @param pwMap
	 * @return result
	 */
    @Transactional
	public int modifyPw(Map<String, Object> pwMap) {
    	
    	// 현재 비밀번호 암호화
    	String originPw = (String)pwMap.get("originPw");
    	pwMap.put("originPw", SecurityUtils.getSha256(originPw));
    	
    	// 신규 비밀번호 암호화
    	String newPw = (String)pwMap.get("newPw");
    	pwMap.put("newPw", SecurityUtils.getSha256(newPw));
    	
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
    	
    	// 현재 비밀번호 암호화
    	String originPw = (String)removeUserMap.get("originPw");
    	removeUserMap.put("originPw", SecurityUtils.getSha256(originPw));
    	
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
