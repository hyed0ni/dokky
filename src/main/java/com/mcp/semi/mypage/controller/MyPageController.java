package com.mcp.semi.mypage.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mcp.semi.mypage.service.MyPageService;
import com.mcp.semi.user.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"loginUser"}) 
@RequestMapping("mypage")
public class MyPageController {
	
	private final MyPageService myPageService;
	
	/** 회원 정보 조회
	 * @param model
	 * @return forward (myProfile.jsp)
	 */
	@GetMapping("")
	public String myProfile(Model model) {
		
		// 테스트 로그인
		UserDto loginUser = UserDto
							.builder()
							.userNo(2)
							.userEmail("test2@naver.com")
							.userName("테스트2")
							.build();
		
		model.addAttribute("loginUser", loginUser);
		return "mypage/myProfile";
		
	}
	
	/** 비밀번호 변경
	 * @return forward (modifyPw.jsp)
	 */
	@GetMapping("modify-password")
	public String modifyPw() {
		return "mypage/modifyPw";
	}
	
	/** 비밀번호 변경
	 * @param pwMap
	 * @param loginUser
	 * @return redirect (myProfile() or modifyPw())
	 */
	@PostMapping("modify-password")
	public String modifyPw(@RequestParam Map<String, Object> pwMap, @SessionAttribute("loginUser") UserDto loginUser) {
		
		pwMap.put("userNo", loginUser.getUserNo());
		int result = myPageService.modifyPw(pwMap);
		
		if (result == 1) return "redirect:/mypage";
		else return "redirect:modify-password";
		
	}
	
	/** 계정 삭제
	 * @return forward (removeConfirm.jsp)
	 */
	@GetMapping("remove-confirm")
	public String removeConfirm() {
		return "mypage/removeConfirm";
	}

}
