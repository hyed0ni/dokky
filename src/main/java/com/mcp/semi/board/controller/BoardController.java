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

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/dokky")
public class BoardController {
	

	@GetMapping("/main")
	public String Board() {
		return "index";
	}
	
	@GetMapping("/detail")
	public String boardDetail() {
		return "detail";
	}
	
	@GetMapping("/modify")
	public String BoardModify() {
		return "board/modify";
	}
	
	@GetMapping("/add")
	public String BoardAdd() {
		return "board/add";
	}
 
	@ResponseBody
	@GetMapping(value="/getBoard.do", produces = "application/json")
	public List<BoardDto> getBoardList(Model model)
	{
		return boardService.getBoardList(model);
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
