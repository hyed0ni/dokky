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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

//	@ResponseBody
//	@GetMapping(value = "/getBoard.do", produces = "application/json") 	// 전체 게시글 가져오기
//	public List<BoardDto> getBoardList(Model model) {
//		return boardService.getBoardList(model);
//	}

	@ResponseBody
	@GetMapping(value = "/getBoardByNo.do", produces = "application/json") // 특정 번호의 게시글만 가져오기
	public BoardDto getBoardByNo(@RequestParam("boardNo") int boardNo) {
		return boardService.getBoardByNo(boardNo);
	}

	@ResponseBody
	@DeleteMapping(value = "/deleteBoard/{boardNo}", produces = "application/json") // 삭제할때 쓰는거
	public int deleteBoard(@PathVariable(value = "boardNo") Optional<String> opt) {
		int boardNo = Integer.parseInt(opt.orElse("0"));
		return boardService.deleteBoard(boardNo);
	}
  
	@PostMapping("/edit.do")
	public String editBoard(@RequestParam("boardNo") int boardNo, Model model) {
		BoardDto board = boardService.getBoardByNo(boardNo);
		model.addAttribute("board", board);
		return "board/modify";
		
	}
	
	@GetMapping("/modify.do")
	public String modifyBoard(BoardDto board, RedirectAttributes redirectAttributes, int boardNo) {
		int updateCount = boardService.modifyBoard(board);
		redirectAttributes.addFlashAttribute("updateCount", updateCount);
	    return "redirect:/dokky/detail.do?boardNo=" + board.getBoardNo();
	}

	@ResponseBody
	@GetMapping(value = "/putBoardHit.do", produces = "application/json") // 조회수 늘릴때 쓰는거
	public int updateHit(@RequestParam("boardNo") int boardNo) {
		return boardService.updateHit(boardNo);
	}

}
