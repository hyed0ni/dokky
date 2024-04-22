package com.mcp.semi.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mcp.semi.board.dto.BoardDto;
import com.mcp.semi.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardMapper boardMapper;

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
	
	public int modifyBoard(BoardDto board) {
		System.out.println(board);
		return boardMapper.updateBoard(board);
	}
	
	public int updateHit(int boardNo) {
		return boardMapper.updateHit(boardNo);
	}

	public int getTotalCount() {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCount();
	}

}