package com.mcp.semi.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mcp.semi.board.service.BoardService;
import com.mcp.semi.user.dto.UserDto;
import com.mcp.semi.user.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserInterceptor implements HandlerInterceptor {
	
	private final AuthenticationService authenticationService;
	private final BoardService boardService;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String path = request.getRequestURI();
		HttpSession session = request.getSession(false);

		if(!authenticationService.isAuthenticated()) {
			log.warn("인증되지 않은 사용자가 {} 경로에 접근 시도함.", path);
			response.sendRedirect("/dokky/signin");
			return false;
		}
		
		if(path.startsWith("/dokky/modify")) { // 경로 확인 조건을 수정합니다.
			UserDto currentUser = authenticationService.getCurrentUser(session);
			if(currentUser == null) {
				log.warn("세션이 없는 상태에서 {} 경로에 접근 시도함.", path);
				response.sendRedirect("/dokky/signin");
				return false;
			}
		
		String boardNo = request.getParameter("boardNo");
		if(boardNo != null && !boardService.isUserBoardOwner(currentUser.getUserNo(), Integer.parseInt(boardNo))) {
			log.warn("사용자 {}가 자신의 게시물이 아닌 {} 경로에 접근 시도함.", currentUser.getUserNo(), path);
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}
	}
		if(path.equals("/dokky/mypage")) {
			UserDto currentUser = authenticationService.getCurrentUser(request.getSession());
			if(currentUser == null) {
				log.warn("{} 경로에 접근 시도하였으나, 존재하지 않는 사용자 세션", path);
				response.sendRedirect("/dokky/signin");
				return false;
			}
		}
		
		return true;
	}

}
