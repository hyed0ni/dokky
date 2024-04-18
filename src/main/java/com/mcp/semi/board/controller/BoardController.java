package com.mcp.semi.board.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.board.service.BoardService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/dokky")
public class BoardController {
	
	private final BoardService boardService;

	@GetMapping("/main")
	public String board(BoardDto boardDto, Model model) {
		List<BoardDto> boardList = boardService.getBoardList(boardDto);
		model.addAttribute("boardList", boardList);
		return "index";
	}
	
	@GetMapping("/detail")
	public String boardDetail() {
		return "detail";
	}
	
	@GetMapping("/modify")
	public String boardModify() {
		return "board/modify";
	}
	
	@GetMapping("/add")
	public String boardAdd() {
		return "board/add";
	}
 
	@ResponseBody
	@GetMapping(value="/getBoardByNo.do", produces = "application/json")
	public BoardDto getBoardByNo(@RequestParam("boardNo") int boardNo)
	{
		return boardService.getBoardByNo(boardNo);
	}
	
	@DeleteMapping(value="/deleteBoard/{boardNo}", produces = "application/json")
	public ResponseEntity<Map<String,Object>> deleteBoard(@PathVariable(value="boardNo") Optional<String> opt)
	{
		int boardNo = Integer.parseInt(opt.orElse("0"));
		return boardService.deleteBoard(boardNo);
	}
	
	
}
