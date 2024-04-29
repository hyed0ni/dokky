package com.mcp.semi.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcp.semi.board.dto.CommentDto;
import com.mcp.semi.board.dto.CommentInfoDto;
import com.mcp.semi.board.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/detail")
public class CommentController {
	private final CommentService commentService;

	@PostMapping(value="/registCmt", produces = "application/json")
	public ResponseEntity<Integer> insertComment(@RequestBody CommentInfoDto commentInfoDto)
	{	
		int insertCount = commentService.insertComment(commentInfoDto);
		return ResponseEntity.ok(insertCount);
	}
	
	@ResponseBody
	@GetMapping(value = "/getCmt/{boardNo}", produces = "application/json") 	// 전체 댓글 가져오기
	public List<CommentDto> getCommentList(@PathVariable(value = "boardNo") Optional<String> opt, Model model) {
		int boardNo = Integer.parseInt(opt.orElse("0"));
		return commentService.getCommentList(model, boardNo);
	}
	
	@ResponseBody
	@DeleteMapping(value = "/deleteCmt/{commentNo}", produces = "application/json") // 삭제할때 쓰는거
	public int deleteComment(@PathVariable(value = "commentNo") Optional<String> opt) {
		int commentNo = Integer.parseInt(opt.orElse("0"));
		return commentService.deleteComment(commentNo);
	}
	
	@PostMapping(value="/modifyCmt", produces = "application/json")
	public ResponseEntity<Integer> modifyComment(@RequestBody CommentInfoDto commentInfoDto)
	{	
		int modifyCount = commentService.modifyComment(commentInfoDto);
		return ResponseEntity.ok(modifyCount);
	}
}
