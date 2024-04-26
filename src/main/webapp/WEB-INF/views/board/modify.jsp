<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../layout/header.jsp" %>
	<link href="/css/board/modify.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@ include file="../layout/navbar.jsp" %>


<div class="main">
<form id="frm-board-modify" action="/dokky/modify-form" method="POST" class="my-3">
    <input type="hidden" name="boardNo" value="${board.boardNo}" />

    <h4 class="border-bottom py-2">게시물 수정하기</h4>
    <label for="content" class="form-label">제목</label>
    <input type="text" name="boardTitle" id="title" class="form-control" value="${board.boardTitle}" style="white-space: pre-line;" />
    <div class="mb-3">
        <label for="content" class="form-label mt-2">내용</label>
        <textarea name="boardContent" id="content" class="form-control" >${board.boardContent}</textarea>
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

 
	   $("#cancelBtn").click(function() {
	      window.history.back();
	    });
	   
	   $("#modifyBtn").click(function() {
		      window.location.href = '/dokky/detail';
		    });
	   
	   

   
   
   
   
   
</script>




