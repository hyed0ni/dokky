package com.mcp.semi.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcp.semi.board.dto.CommentDto;
import com.mcp.semi.board.service.CommentService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/detail")
public class CommentController {

	private final CommentService commentService;

	@PostMapping(value="/registCmt", produces = "application/json")
	public int registComment(HttpServletRequest request)
	{
		String content = request.getParameter("comment");
		//int userNo = Integer.parseInt(request.getParameter("userNo"));
		int userNo = 1;
		int boardNo = 4;
		//int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@" + content);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@" + boardNo);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@" + userNo);
		
		return commentService.insertComment(request);
	}
	
//	
//	@ResponseBody
//	@DeleteMapping(value = "/deleteComment/{commentNo}", produces = "application/json") // 삭제할때 쓰는거
//	public int deleteComment(@PathVariable(value = "commentNo") Optional<String> opt) {
//		int commentNo = Integer.parseInt(opt.orElse("0"));
//		return commentService.deleteComment(commentNo);
//	}
//	
//	@ResponseBody
//	@DeleteMapping(value = "/modifyComment/{commentNo}", produces = "application/json") // 삭제할때 쓰는거
//	public int modifyComment(@PathVariable(value = "commentNo") Optional<String> opt) {
//		int commentNo = Integer.parseInt(opt.orElse("0"));
//		return commentService.modifyComment(commentNo);
//	}
//
//	@ResponseBody
//	@GetMapping(value = "/getCommentByNo.do", produces = "application/json") // 특정 번호의 게시글만 가져오기
//	public CommentDto getCommentByNo(@RequestParam("commentNo") int commentNo) {
//		return commentService.getCommentByNo(commentNo);
//	}
}
