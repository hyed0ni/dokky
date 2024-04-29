package com.mcp.semi.mypage.mapper;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mcp.semi.board.dto.BoardDto;

@Mapper
public interface MyPageMapper {
	
	/**
	 * 닉네임 중복 검사
	 * 
	 * @param userName
	 * @return result
	 */
	int checkNickname(String userName);
	
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
  	
	List<BoardDto> getBoardsByUserNo(@Param("userNo") int userNo, @Param("begin") int begin, @Param("end") int end);
	
	List<BoardDto> getBoardsWithCommentsByUserNo(@Param("userNo") int userNo, @Param("begin") int begin, @Param("end") int end);
	
	int countBoardsByUserNo(int userNo);
	
	int countCommentsByUserNo(int userNo);

}
