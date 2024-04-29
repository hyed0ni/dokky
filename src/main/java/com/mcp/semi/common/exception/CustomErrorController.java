package com.mcp.semi.common.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

	 private static final String PATH = "/error";

	    @GetMapping(PATH)
	    public String error(HttpServletRequest request) {
	        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

	        if (status != null) {
	            int statusCode = Integer.parseInt(status.toString());
	            
	            if(statusCode == HttpStatus.NOT_FOUND.value()) {
	                return "error-page/404";
	            }
	            // 다른 HTTP 에러 코드에 대한 페이지도 처리 가능
	        }
	        return "error";
	    }

}


