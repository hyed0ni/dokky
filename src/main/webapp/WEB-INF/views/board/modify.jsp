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
    <form id="frm-board-modify" action="/dokky/modify.do" method="GET" class="my-3">
        <input type="hidden" name="boardNo" value="${updateCount.boardNo}" />
        <!-- 제목 -->
        <h4 class="border-bottom py-2">게시물 수정하기</h4>
        <label for="content" class="form-label">제목</label>
        <input type="text" name="content" id="title" class="form-control" value="${updateCount.boardTitle}" style="white-space: pre-line;" />
        <!-- 내용 -->
        <div class="mb-3">
            <label for="content" class="form-label mt-2">내용</label>
            <textarea name="content" id="content" class="form-control">${updateCount.boardContent}</textarea>
        </div>
        <div class="d-flex justify-content-end">
            <button type="button" id="cancelBtn" class="btn btn btn-light mr-2" style="color: white;">취소</button>
            <button type="submit" id="modifyBtn" class="btn btn-primary">등록</button>
        </div>
    </form>
   
</div>

<%@ include file="../layout/copyright.jsp" %>
<%@ include file="../layout/footer.jsp" %>

<script>

	function getBoardNoFromURL() {
		
    	var urlParams = new URLSearchParams(window.location.search);
    	return urlParams.get('boardNo');
    	
    	
	}
	
	
	var boardNo = getBoardNoFromURL();	

	const redirectToModifyPage = () => {
	    window.location.href = '/dokky/modify?boardNo=' + boardNo + 
	        '&boardTitle=' + encodeURIComponent(document.getElementById('detail-title').innerText) +
	        '&boardContent=' + encodeURIComponent(document.getElementById('detail-contents').innerText);
	}

	document.getElementById('btn-modify').addEventListener('click', redirectToModifyPage);

	

    document.getElementById('cancelBtn').onclick = () => {
    window.location.href = '/dokky/detail';
}
</script>




