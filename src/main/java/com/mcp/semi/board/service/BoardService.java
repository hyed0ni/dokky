package com.mcp.semi.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	
	@Transactional(readOnly = true)
	public List<BoardDto> getBoardList(Integer page,int cnt) {
		int totalCount = getTotalCount(); 
		int total = totalCount/cnt + ((totalCount%cnt>0) ? 1:0);
		int begin = (page - 1) * cnt + 1;
		int end = begin + cnt - 1;
	
		Map<String,Object> map = Map.of("begin",begin, "end",end, "total", total);

		return boardMapper.getBoardList(map);
	}

	
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
		System.out.println(1);
		List<BoardDto> boardList = boardMapper.getHotBoardList(map);
		
		System.out.println(boardList);
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
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCount();
	}

}
