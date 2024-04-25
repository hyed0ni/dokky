package com.mcp.semi.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.board.mapper.BoardMapper;
import com.mcp.semi.common.page.PageResponse;
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
		
       int insertCount = boardMapper.insertBoard(board);
       return insertCount;
		
	}
	
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
// 	public PageResponse<BoardDto> getBoardList(int page, int cnt) {
// 		int totalCount = getTotalCount(); 
// 		int total = totalCount / cnt + ((totalCount % cnt > 0) ? 1 : 0);
// 		int startPage = Math.max(page - 2, 1);
// 		int endPage = Math.min(page + 2,  total);
// 		int begin = (page - 1) * cnt + 1;
// 		int end = begin + cnt - 1;
		
// 		List<BoardDto> items = boardMapper.getBoardList(Map.of("begin",begin, "end",end, "total", total));
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
	public List<BoardDto> getHotBoardList(Model model) {
		
		Map<String, Object> map = Map.of("begin", 1, "end", 3);
		List<BoardDto> boardList = boardMapper.getHotBoardList(map);
		
		model.addAttribute(boardList);
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

}
