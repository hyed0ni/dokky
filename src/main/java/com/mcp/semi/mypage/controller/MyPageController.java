package com.mcp.semi.mypage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.mypage.service.MyPageService;
import com.mcp.semi.user.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("mypage")
@RequiredArgsConstructor
public class MyPageController {
	
	private final MyPageService myPageService;
	
	@GetMapping("{userNo}")
	public String myProfile(@PathVariable("userNo") int userNo, Model model) {
		UserDto userProfile = myPageService.getUserProfile(userNo);
		model.addAttribute("user",userProfile);
		return "mypage/myProfile";
	}
	
	@GetMapping("modify-password")
	public String modifyPw() {
		return "mypage/modifyPw";
	}
	
	@GetMapping("remove-confirm")
	public String removeConfirm() {
		return "mypage/removeConfirm";
	}
	
	@GetMapping(value="api/myBoard", produces = "application/json")
	public ResponseEntity<?> myBoard() {
		int userId = 1;
		List<BoardDto> boardList = myPageService.getUserPosts(userId);
		if(boardList.isEmpty()) {
			return ResponseEntity.ok(Map.of("message", "아직 작성한 게시글이 없습니다"));
		}
		return ResponseEntity.ok(boardList);
	}


}
