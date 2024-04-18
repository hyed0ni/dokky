package com.mcp.semi.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("mypage")
public class MyPageController {
	
	@GetMapping("")
	public String myProfile() {
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

}
