<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
	<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>
<%@ include file="../layout/header.jsp" %>
	<link href="/css/board/modify.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<%@ include file="../layout/navbar.jsp" %>

<div class="main">
    <form id="frm-board-add" action="/dokky/add-from" method="post" class="my-3">
        <!-- 제목 -->
        <h4 class="border-bottom py-2">게시물 작성하기</h4>
        <label for="content" class="form-label">제목</label>
        <input type="text" name="content" id="title" class="form-control"  style="white-space: pre-line;" />
        <!-- 내용 -->
        <div class="mb-3">
            <label for="content" class="form-label mt-2">내용</label>
            <textarea type="text" name="content" id="content" class="form-control"></textarea>
        </div>
        <div class="d-flex justify-content-end">
        		<input type="hidden" name="userNo" value="${sessionScope.user.userNo}">
            <a type="submit" id="cancelBtn" class="btn btn btn-light mr-2">취소</a>
            <button type="submit" id="modifyBtn" class="btn btn-primary">등록</button>
        </div>
    </form>
</div>

<%@ include file="../layout/copyright.jsp" %>
<%@ include file="../layout/footer.jsp" %>

<script>
	/* 취소버튼 클릭 시 */
	$('#cancelBtn').on('click', () => {
		window.location.href="/dokky/main";
	})
	
	
</script>