package com.mcp.semi.mypage.mapper;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mcp.semi.board.dto.BoardDto;

@Mapper
public interface MyPageMapper {
	
	/**
	 * 회원 정보 수정
	 * 
	 * @param userMap
	 * @return result
	 */
	int modifyUser(Map<String, Object> userMap);
	
	/** 
	 * 비밀번호 변경
	 * 
	 * @param pwMap
	 * @return result
	 */
	int modifyPw(Map<String, Object> pwMap);

	/**
	 * 계정 삭제: 탈퇴일 수정
	 * 
	 * @param removeUserMap
	 * @return result
	 */
	int removeUser(Map<String, Object> removeUserMap);
	
	/**
	 * 계정 삭제: 데이터 삭제
	 * 
	 * @return result
	 */
	int deleteUserData();
  	
	List<BoardDto> getBoardsByUserNo(int userNo);
	
	List<BoardDto> getBoardsWithCommentsByUserNo(int userNo);

}
