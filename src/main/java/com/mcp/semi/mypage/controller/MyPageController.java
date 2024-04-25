package com.mcp.semi.mypage.controller;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mcp.semi.mypage.service.MyPageService;
import com.mcp.semi.user.dto.UserDto;

import jakarta.servlet.http.HttpSession;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.common.page.PageResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("dokky")
@RequiredArgsConstructor
public class MyPageController {

	private final MyPageService myPageService;

	/**
	 * 회원 정보 조회
	 * 
	 * @param user
	 * @param model
	 * @return forward (myProfile.jsp)
	 */
	@GetMapping("mypage")
	public String myProfile(@SessionAttribute("user") UserDto user, Model model) {
		UserDto userProfile = myPageService.getUserProfile(user.getUserNo());
		model.addAttribute("user", userProfile);
		return "mypage/myProfile";
	}

	/**
	 * 회원 정보 수정
	 * 
	 * @param user
	 * @param userMap
	 * @param ra
	 * @return redirect (myProfile())
	 */
	@PostMapping("mypage")
	public String modifyUser(@SessionAttribute("user") UserDto user, 
							@RequestParam Map<String, Object> userMap,
							RedirectAttributes ra) {

		userMap.put("userNo", user.getUserNo());
		int result = myPageService.modifyUser(userMap);
		
		if (result == 1) ra.addFlashAttribute("resultMsg", "회원 정보가 변경되었습니다. 🥰");
		return "redirect:/dokky/mypage";

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
	 * @param user
	 * @param pwMap
	 * @param ra
	 * @param session
	 * @return redirect (signinPage() or modifyPw())
	 */
	@PostMapping("modify-password")
	public String modifyPw(@SessionAttribute("user") UserDto user, 
							@RequestParam Map<String, Object> pwMap,
							RedirectAttributes ra, 
							HttpSession session) {

		pwMap.put("userNo", user.getUserNo());
		int result = myPageService.modifyPw(pwMap);

		if (result == 1) {
			ra.addFlashAttribute("resultMsg", "비밀번호가 성공적으로 변경되었습니다. 🥰");
			session.invalidate();
			return "redirect:/dokky/signin";
			
		} else {
			ra.addFlashAttribute("resultMsg", "현재 비밀번호가 일치하지 않습니다. 😭");
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
	 * @param user
	 * @param originPw
	 * @param ra
	 * @param session
	 * @return forward (goodBye.jsp) or redirect (removeUser())
	 */
	@PostMapping("remove-user")
	public String removeUser(@SessionAttribute("user") UserDto user, 
							@RequestParam("originPw") String originPw,
							RedirectAttributes ra, 
							HttpSession session) {

		Map<String, Object> removeUserMap = new HashMap<String, Object>();
		removeUserMap.put("userNo", user.getUserNo());
		removeUserMap.put("originPw", originPw);

		int result = myPageService.removeUser(removeUserMap);

		if (result == 1) {
			log.info("result : {}", result);
			session.invalidate();
			return "mypage/goodBye";
			
		} else {
			ra.addFlashAttribute("resultMsg", "비밀번호가 일치하지 않습니다. 😭");
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