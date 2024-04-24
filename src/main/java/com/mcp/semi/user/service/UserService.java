package com.mcp.semi.user.service;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mcp.semi.common.exception.UserNotFoundException;
import com.mcp.semi.common.util.MailUtils;
import com.mcp.semi.common.util.SecurityUtils;
import com.mcp.semi.user.dto.UserDto;
import com.mcp.semi.user.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper userMapper;
	private final MailUtils mailUtils;

	
	// 회원가입하기
	public void signup(HttpServletRequest request, HttpServletResponse response) {

		String userEmail = request.getParameter("userEmail");
		String userPw = SecurityUtils.getSha256(request.getParameter("userPw"));
		String userName = request.getParameter("userName");
		String userGender = request.getParameterValues("userGender")[0]; 
		String userMobile = request.getParameter("userMoblie");		
		
		UserDto user = UserDto.builder()
													.userEmail(userEmail)
													.userPw(userPw)
													.userName(userName)
													.userGender(userGender)
													.userMobile(userMobile)
													.build();
		
		int insertCount = userMapper.insertUser(user);
		
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			
			if(insertCount == 1) {   // 가입성공
				
				// 접속기록 남기는 Map
				Map<String, Object> params = Map.of("userEmail", userEmail
																					, "userPw", userPw
																					, "accessIp", request.getRemoteAddr()
																					, "userAgent", request.getHeader("User-Agent")
																					, "sessionId", request.getSession().getId());
				
				request.getSession().setAttribute("user", userMapper.getUserByMap(params));
				
				userMapper.insertAccessHistory(params);
				
				out.println("alert('환영합니다! :)');");
				out.println("location.href='" + request.getContextPath() + "/dokky/main';");

			} else {   // 가입실패
				out.println("alert('회원 가입이 실패했습니다. 다시 가입해주세요.');");
				out.println("history.back();");
			}
			
			out.println("</script>");
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	// 인증코드 보내기
	public ResponseEntity<Map<String, Object>> sendCode(Map<String, Object> params) {
		
		String code = SecurityUtils.getRandomString(6, true, true);
		
		System.out.println("인증코드 : " + code);
		
		mailUtils.sendMail((String)params.get("email")
							        , "DOKKY 인증요청"
							        , "<div>인증코드는 <strong>" + code + "</strong>입니다.");
    
    // 인증코드 입력화면으로 보내주는 값
    return new ResponseEntity<>(Map.of("code", code)
        , HttpStatus.OK);
	}

	
	// 사용된 이메일인지 확인하기
	public ResponseEntity<Map<String, Object>> checkEmail(Map<String, Object> params) {
		
		boolean enableEmail = userMapper.getUserByMap(params) == null;
    return new ResponseEntity<>(Map.of("enableEmail", enableEmail)
        , HttpStatus.OK);	
	}
	
	
	// 로그인
	public void signin(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			String userEmail = request.getParameter("userEmail");
			String userPw = SecurityUtils.getSha256(request.getParameter("userPw"));
			String userIp = request.getRemoteAddr();
			String userAgent = request.getHeader("User-Agent");
			
			Map<String, Object> params = Map.of("userEmail", userEmail
																				, "userPw", userPw
																				, "accessIp", userIp
																				, "userAgent", userAgent
																				, "sessionId", request.getSession().getId());

			UserDto user = userMapper.getUserByMap(params);
			
			if(user != null) {   // 성공
				
        userMapper.insertAccessHistory(params);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(1800);
        
        Optional<String> optUrl = Optional.ofNullable(request.getParameter("url"));
        String redirectUrl = optUrl.orElse(request.getContextPath() + "/dokky/main");
        
        response.sendRedirect(redirectUrl);        
				
			} else {   // 실패
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('일치하는 회원 정보가 없습니다.')");
				out.println("location.href='" + request.getContextPath() + "/dokky/signin';");
				out.println("</script>");
				out.flush();
				out.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	// 로그아웃
	public void signout(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			// 로그아웃 기록
			HttpSession session = request.getSession(false);
      if (session != null && session.getAttribute("user") != null) {
        String sessionId = session.getId();
        userMapper.updateAccessHistory(sessionId);

        // 세션 무효화
        session.invalidate();
      }  
			
			// 메인화면 이동
			response.sendRedirect(request.getContextPath() + "/dokky/main");  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	
	// 로그인 후 리다이렉트
	public String getRedirectURLAfterSignin(HttpServletRequest request) {
    
    String redirectURL = "";
    redirectURL = (String)request.getHeader("REFERER");

    // 기본적으로 돌아갈 URL
    if (redirectURL == null || redirectURL.isEmpty()) {
    	redirectURL = request.getContextPath() + "/dokky/main";
    }
    return redirectURL;
}

	
	public UserDto findByUserNo(int userNo) {
		UserDto user = userMapper.findByUserNo(userNo);
		
		return Optional.ofNullable(user)
				.orElseThrow(() -> new UserNotFoundException("사용자가 존재하지 않습니다."));
	}
	
}
