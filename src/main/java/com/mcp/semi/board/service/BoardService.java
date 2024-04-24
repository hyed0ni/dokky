package com.mcp.semi.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

	
	
	
	public List<BoardDto> getBoardList(Integer page,int cnt) {
		int totalCount = getTotalCount(); 
		int total = totalCount/cnt + ((totalCount%cnt>0) ? 1:0);
		int begin = (page - 1) * cnt + 1;
		int end = begin + cnt - 1;
	
		Map<String,Object> map = Map.of("begin",begin, "end",end, "total", total);

		return boardMapper.getBoardList(map);
	}

	
	public int deleteBoard(int boardNo) {
      return boardMapper.deleteBoard(boardNo);
	}
	
	public BoardDto getBoardByNo(int boardNo) {
		
		return boardMapper.getBoardByNo(boardNo);
	} 
	
	
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
	
	public int updateHit(int boardNo) {
		return boardMapper.updateHit(boardNo);
	}
	
	public List<BoardDto> getHotBoardList(Model model) {
		
		Map<String, Object> map = Map.of("begin", 1, "end", 3);
		System.out.println(1);
		List<BoardDto> boardList = boardMapper.getHotBoardList(map);
		
		System.out.println(boardList);
		model.addAttribute(boardList);
		return boardMapper.getHotBoardList(map);
	}
	public BoardDto getBoardUpdateList(BoardDto boardDto) {
		return boardMapper.getBoardUpdateList(boardDto);
	}


	public int getBoardUpdate(BoardDto boardDto) {
		return boardMapper.getBoardUpdate(boardDto);
		
	}


	public int getTotalCount() {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCount();
	}

}
