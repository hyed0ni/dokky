<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
	<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>
<%@ include file="../layout/header.jsp" %>
	<link href="/css/board/add.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@ include file="../layout/navbar.jsp" %>

<div class="main">
    <form id="frm-board-add" action="/dokky/add-form" method="POST" class="my-3">
        <!-- 제목 -->
        <h4 class="border-bottom py-2">게시물 작성하기</h4>
        <label for="title" class="form-label">제목</label>
        <input type="text" name="boardTitle" id="title" class="form-control"  style="white-space: pre-line;" placeholder="제목을 입력해주세요."/>
        <!-- 내용 -->              
        <div class="mb-3">
            <label for="content" class="form-label mt-2">내용</label>
            <textarea type="text" name="boardContent" id="content" class="form-control" placeholder="내용을 입력해주세요."></textarea>
        </div>
        <div class="d-flex justify-content-end" id="btn">
        	<input type="hidden" name="userNo" value="${sessionScope.user.userNo}">
            <a type="submit" id="cancelBtn" class="btn btn btn-light mr-2">취소</a>
            	<button type="submit" id="addBtn" class="btn btn-primary">등록</button>
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
	
	const fnRegisterBoard = (evt) => {
		if(document.getElementById('title').value === ''){
			alert('제목을 입력해주세요.');
			evt.preventDefault();
			return;
		}
	}
	
	document.getElementById('frm-board-add').addEventListener()
</script>