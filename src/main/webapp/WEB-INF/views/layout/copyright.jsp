<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>

    <style>
        /* 컨테이너에 Flexbox 클래스 추가 */
        .container-flex {
            display: flex;
            flex-direction: column;
            min-height: 100vh; /* 전체 뷰포트 높이를 커버하도록 설정 */
        }

        /* 본문 컨텐츠를 감싸는 div */
        .content {
            flex: 1; /* 남은 공간을 모두 차지하도록 설정 */
        }
    </style>
    
		<footer>
			<div class="container">
				<div>
					© 2024 MCP-NCSW, Inc. All rights reserved.
				</div>
			</div>
		</footer>
       