package com.mcp.semi.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.board.mapper.BoardMapper;
import com.mcp.semi.common.util.SecurityUtils;
import com.mcp.semi.user.dto.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardMapper boardMapper;

	@Transactional
	public int registerBoard(HttpServletRequest request) {
		
		String title = request.getParameter("boardTitle");
		String contents = request.getParameter("boardContent");
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		UserDto user = new UserDto();
		user.setUserNo(userNo);
		BoardDto board = BoardDto.builder()
				               .boardTitle(SecurityUtils.getPreventXss(title))
				               .boardContent(SecurityUtils.getPreventXss(contents))
				               .user(user)
				               .build();
		
       boardMapper.insertBoard(board);
       return board.getBoardNo();
		
	}
	
	@Transactional(readOnly = true)
	public List<BoardDto> getBoardList(Integer page,int cnt, String search) {
		int totalCount = getTotalCount(search); 
		int total = totalCount/cnt + ((totalCount%cnt>0) ? 1:0);
		int begin = (page - 1) * cnt + 1;
		int end = begin + cnt - 1;
		if (search == null) search = "";
		Map<String,Object> map = Map.of("begin",begin, "end",end, "total", total, "search", search);

		return boardMapper.getBoardList(map);
}
// 	@Transactional(readOnly = true)
// 	public PageResponse<BoardDto> getBoardList(int page, int cnt, String search) {
// 		int totalCount = getTotalCount(search); 
// 		int total = (int) Math.ceil((double) totalCount / cnt);
// 		int startPage = Math.max(((page - 1) / cnt) * cnt + 1, 1);
// 		int endPage = Math.min(startPage + cnt - 1, total);
// 		int nextPage = Math.min(page + 10, total);
//		int prevPage = Math.max(page - 10, 1); 
// 		
// 		int begin = (page - 1) * cnt + 1;
// 		int end = begin + cnt - 1;
// 		
// 		if (search == null) search = "";
// 		List<BoardDto> items = boardMapper.getBoardList(Map.of("begin", begin, "end", end, "total", total, "search", search));
// 		return new PageResponse<>(items, page ,total, startPage, endPage);
// 	}
	
	@Transactional
	public int deleteBoard(int boardNo) {
      return boardMapper.deleteBoard(boardNo);
	}
	
	@Transactional(readOnly = true)
	public BoardDto getBoardByNo(int boardNo) {
		return boardMapper.getBoardByNo(boardNo);
	} 
	
	@Transactional
	public int modifyBoard(HttpServletRequest request) {
	    
	    String title = request.getParameter("title");
	    String contents = request.getParameter("content");
	    int blogNo = Integer.parseInt(request.getParameter("blogNo"));
	    
	    
	    BoardDto board = BoardDto.builder()
	                    .boardTitle(title)
	                    .boardContent(contents)
	                    .boardNo(blogNo)
	                    .build();
	    
	    
	    int modifyResult = boardMapper.updateBoard(board);
	    return modifyResult;
	}
	
	@Transactional
	public int updateHit(int boardNo) {
		return boardMapper.updateHit(boardNo);
	}
	
	@Transactional(readOnly = true)
	public List<BoardDto> getHotBoardList(Map<String, Object> map) {
		return boardMapper.getHotBoardList(map);
	}
	
	@Transactional(readOnly = true)
	public BoardDto getBoardUpdateList(BoardDto boardDto) {
		return boardMapper.getBoardUpdateList(boardDto);
	}

	@Transactional
	public int getBoardUpdate(BoardDto boardDto) {
		return boardMapper.getBoardUpdate(boardDto);
	}
  
	@Transactional(readOnly = true)
	public int getTotalCount(String search) {
		return boardMapper.getTotalCount(search);
	}
	
	@Transactional(readOnly = true)
	public boolean isUserBoardOwner(int userNo, int boardNo) {
		return boardMapper.isUserBoardOwner(userNo, boardNo);
	}
}
