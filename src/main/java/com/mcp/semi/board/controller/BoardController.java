package com.mcp.semi.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dokky")
public class BoardController {
	
	@GetMapping("/")
	public String Board() {
		return "index";
	}
	
	@GetMapping("/detail")
	public String BoardDetail() {
		return "detail";
	}
	
	@GetMapping("/edit")
	public String BoardEdit() {
		return "edit";
	}

}
