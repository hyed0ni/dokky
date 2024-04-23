package com.mcp.semi.user.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mcp.semi.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequestMapping("/dokky")
@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/signin")
	public String signinPage(HttpServletRequest request, Model model) {
		model.addAttribute("url",  userService.getRedirectURLAfterSignin(request));
		return "user/signin";
	}
	
	// 로그인하기
	@PostMapping("/signin")
	public void signin(HttpServletRequest request, HttpServletResponse response) {
		userService.signin(request, response);
	}
	
	@GetMapping("/signup")
	public String signupPage() {
		return "user/signup";
	}
	
	// 회원가입하기
  @PostMapping("/signup")
  public void setSignup(HttpServletRequest request, HttpServletResponse response) {
  	userService.signup(request, response);
  }
  
  // 인증코드 보내기
  @PostMapping(value = "user/sendCode", produces = "application/json")
  public ResponseEntity<Map<String, Object>> sendCode(@RequestBody Map<String, Object> params){
  	return userService.sendCode(params);
  }
  
  // 이용한 이력이 있는 이메일인지 확인하기
  @PostMapping(value = "user/checkEmail", produces = "application/json")
  public ResponseEntity<Map<String, Object>> checkEmail(@RequestBody Map<String, Object> params){
  	return userService.checkEmail(params);
  }
  
  // 로그아웃하기
  @GetMapping("/signout")
  public void signout(HttpServletRequest request, HttpServletResponse response) {
  	userService.signout(request, response);
  }

}
