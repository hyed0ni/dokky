package com.mcp.semi.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.board.service.BoardService;
import com.mcp.semi.common.page.PageResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dokky")
public class BoardController {

	private final BoardService boardService;
	
//	@GetMapping("/main")
//	public String board(@RequestParam(value="page", defaultValue="1") Integer page, Model model) {
//		
//		// 전체 게시물 리스트
//	    List<BoardDto> boardList = boardService.getBoardList(page,10);
//	    model.addAttribute("boardList", boardList);
//	    
//	    // 해당 게시물 전체 조회	    
//	    for(BoardDto b : boardList)
//	    System.out.println(b);
//	    
//	    // 전체 게시물 수 조회
//	    int totalCount = boardService.getTotalCount(); 
//	    model.addAttribute("totalCount", totalCount);
//	    
//	    // 전체 게시물 / 10	    
//		int maxPage = (int)Math.ceil((double)totalCount/10);
//		int startPage = page - 2;
//		int endPage = page + 2;
//		
//		// 다음 페이지, 전 페이지
//		int perPage = maxPage - 1;
//		model.addAttribute("perPage", perPage);
//		
//		// 시작번호, 끝번호 계산 후 표출		
//		endPage = Math.min(endPage, maxPage);
//		startPage = Math.max(startPage, 1);
//		model.addAttribute("startPage",startPage);
//		model.addAttribute("endPage",endPage);
//		
//	    return "board/list";
//	}
	
	@GetMapping("/main")
	public String board(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		final int cnt = 10;
		PageResponse<BoardDto> pageResponse = boardService.getBoardList(page, cnt);
		model.addAttribute("pageResponse", pageResponse);
		return "board/list";
	}

	@GetMapping("/detail")
	public String boardDetail() {
		return "board/detail";
	}

	@GetMapping("/modify")
	public String boardModify(BoardDto boardDto, Model model) {
		BoardDto board = boardService.getBoardUpdateList(boardDto);
		model.addAttribute("board", board);
		return "board/modify";
	}
	

	@PostMapping("/modify-form")
	public String boardModifyForm(BoardDto boardDto, Model model) {
	    int board = boardService.getBoardUpdate(boardDto);
	    model.addAttribute("board", boardDto);
	    return "redirect:/dokky/detail?boardNo=" + boardDto.getBoardNo();
	}
	
	@GetMapping("/add")
	public String boardAdd() {
		return "board/add";
	}

	@ResponseBody
	@GetMapping(value = "/getBoard.do", produces = "application/json") 	// 전체 게시글 가져오기
	public List<BoardDto> getHotBoardList(Model model) {
		return boardService.getHotBoardList(model);
	}
	
	@PostMapping("/add-form")
	public String boardAddForm(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		int insertCount = boardService.registerBoard(request);
		redirectAttributes.addFlashAttribute("insertResult",insertCount == 1 ? "등록되었습니다." : "등록되지 않았습니다.");
		return "redirect:/dokky/main";
	}
	

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

	  
	@ResponseBody
	@GetMapping(value = "/putBoardHit.do", produces = "application/json") // 조회수 늘릴때 쓰는거
	public int updateHit(@RequestParam("boardNo") int boardNo) {
		return boardService.updateHit(boardNo);
	}

}
