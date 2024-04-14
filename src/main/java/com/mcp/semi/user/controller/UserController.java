package com.mcp.semi.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	
	@GetMapping("/user/signin")
	public String signin() {
		return "signin";
	}

}
