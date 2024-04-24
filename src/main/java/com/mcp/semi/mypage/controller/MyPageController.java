package com.mcp.semi.mypage.controller;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mcp.semi.mypage.service.MyPageService;
import com.mcp.semi.user.dto.UserDto;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.common.page.PageResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("dokky")
@RequiredArgsConstructor
public class MyPageController {

	private final MyPageService myPageService;

	/**
	 * 회원 정보 조회
	 * 
	 * @param userNo
	 * @param model
	 * @return forward (myProfile.jsp)
	 */
	@GetMapping("mypage/{userNo}")
	public String myProfile(@PathVariable("userNo") int userNo, Model model) {
		UserDto userProfile = myPageService.getUserProfile(userNo);
		model.addAttribute("user", userProfile);
		return "mypage/myProfile";
	}

	/**
	 * 회원 정보 수정
	 * 
	 * @param userNo
	 * @param userMap
	 * @param redirectAttributes
	 * @return redirect (myProfile())
	 */
	@PostMapping("mypage/{userNo}")
	public String modifyUser(@PathVariable("userNo") int userNo, 
							@RequestParam Map<String, Object> userMap,
							RedirectAttributes ra) {

		userMap.put("userNo", userNo);
		int result = myPageService.modifyUser(userMap);
		
		System.out.println("userNo" + userNo);
		System.out.println("userMap" + userMap);

		if (result == 1) ra.addFlashAttribute("resultMsg", "성공 👍");
		else ra.addFlashAttribute("resultMsg", "실패 😭");

		return "redirect:/dokky/mypage/" + userNo;

	}

	/**
	 * 비밀번호 변경
	 * 
	 * @return forward (modifyPw.jsp)
	 */
	@GetMapping("modify-password")
	public String modifyPw() {
		return "mypage/modifyPw";
	}

	/**
	 * 비밀번호 변경
	 * 
	 * @param userNo
	 * @param pwMap
	 * @param redirectAttributes
	 * @return redirect (myProfile() or modifyPw())
	 */
	@PostMapping("modify-password/{userNo}")
	public String modifyPw(@PathVariable("userNo") int userNo, 
							@RequestParam Map<String, Object> pwMap,
							RedirectAttributes redirectAttributes) {

		pwMap.put("userNo", userNo);
		int result = myPageService.modifyPw(pwMap);

		if (result == 1) return "redirect:/dokky/mypage/" + userNo;
		else {
			redirectAttributes.addFlashAttribute("errorMsg", "현재 비밀번호가 일치하지 않습니다.");
			return "redirect:/dokky/modify-password";
		}

	}

	/**
	 * 계정 삭제
	 * 
	 * @return forward (removeUser.jsp)
	 */
	@GetMapping("remove-user")
	public String removeUser() {
		return "mypage/removeUser";
	}

	/**
	 * 계정 삭제
	 * 
	 * @param userNo
	 * @param originPw
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("remove-user/{userNo}")
	public String removeUser(@PathVariable("userNo") int userNo, 
							@RequestParam("originPw") String originPw,
							RedirectAttributes ra) {

		Map<String, Object> removeUserMap = new HashMap<String, Object>();
		removeUserMap.put("userNo", userNo);
		removeUserMap.put("originPw", originPw);

		int result = myPageService.removeUser(removeUserMap);

		if (result == 1) return "redirect:/dokky/main";
		else {
			ra.addFlashAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
			return "redirect:/dokky/remove-user";
		}

	}

	// 내가 작성한 글 조회
	@GetMapping(value = "/api/my-board/{userNo}", produces = "application/json")
	public ResponseEntity<?> myBoard(@PathVariable("userNo") int userNo,
									 @RequestParam(value ="page", defaultValue = "1")int page) {
		PageResponse<BoardDto> boardList = myPageService.getUserBoards(userNo, page, 10);
		if (boardList.getItems().isEmpty()) {
			return ResponseEntity.ok(Map.of("message", "아직 작성한 게시글이 없습니다."));
		} else {
			return ResponseEntity.ok(boardList);
		}
	}

	// 내가 댓글 단 게시글 정보 + 댓글 내용 조회
	@GetMapping(value = "/api/my-comment/{userNo}", produces = "application/json")
	public ResponseEntity<?> myComment(@PathVariable("userNo") int userNo,
			 						  @RequestParam(value ="page", defaultValue = "1")int page) {
		 PageResponse<BoardDto> boardList = myPageService.getUserBoardsWithComments(userNo, page, 10);
		if (boardList.getItems().isEmpty()) {
			return ResponseEntity.ok(Map.of("message", "아직 작성한 댓글이 없습니다."));
		} else {
			return ResponseEntity.ok(boardList);
		}

	}

}
