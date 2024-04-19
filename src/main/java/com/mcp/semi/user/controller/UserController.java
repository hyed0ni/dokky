package com.mcp.semi.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@GetMapping("/signin")
	public String signinPage(Model model) {
		return "user/signin";
	}
	
	@PostMapping("/sigin")
	public void setSignin(UserDto user) {
		userService.setSignin(user);
	}
	
	@GetMapping("/signup")
	public String signupPage() {
		return "user/signup";
	}
	
  @PostMapping("/signup")
  public void setSignup(UserDto user) {
  	userService.setSignup(user);
  }

}
