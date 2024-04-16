package com.mcp.semi.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mcp.semi.user.dto.UserDto;
import com.mcp.semi.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/signin.page")
	public String signinPage() {
		return "user/signin";
	}
	
	@PostMapping("/sigin.do")
	public void setSignin(UserDto user) {
		userService.setSignin(user);
	}
	
	@GetMapping("/signup.page")
	public String signupPage() {
		return "user/signup";
	}
	
  @PostMapping("/signup.do")
  public void setSignup(UserDto user) {
  	userService.setSignup(user);
  }

}
