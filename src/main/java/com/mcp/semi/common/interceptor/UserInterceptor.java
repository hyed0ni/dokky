package com.mcp.semi.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mcp.semi.user.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserInterceptor implements HandlerInterceptor {
	
	private final AuthenticationService authenticationService;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(!authenticationService.isAuthenticated()) {
			response.sendRedirect("/dokky/signin");
			return false;
		}
		
		if(!authenticationService.userExist(getUserNoFromPath(request))) {
			response.sendRedirect("/dokky/signin");
			return false;
		}
		
		if(!authenticationService.inOwnprofile(getUserNoFromPath(request))) {
			response.sendRedirect("/dokky/main");
			return false;
		}
		
		return true;
	}
	
	
	private int getUserNoFromPath(HttpServletRequest request) {
		String path = request.getRequestURI();
		String UserNoStr = path.substring(path.lastIndexOf("/") + 1);
		return Integer.parseInt(UserNoStr);
		
	}

}
