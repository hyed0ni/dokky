<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../layout/header.jsp" %>
	<link href="/css/board/detail.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
 </head>
<body>
<% request.setAttribute("title", "DOKKY | 게시물 상세"); %>
<%@ include file="../layout/navbar.jsp" %>
	
<div class="main">
  <form class="body"> 
    <div class="leftarea">
    	<div class="px-2 hot-name" id="hot-name" style="font-size:1.7rem; text-align:left;">
    		<i class="fa-solid fa-fire"></i>&nbsp;
    		<span>오늘의</span>
    		<span style="color:red; font-weight:bold;">추천 게시글</span>
    		&nbsp;<i class="fa-solid fa-fire"></i>
   		</div>
   		<hr style="border: solid 1px; margin-top:1px; width:95%; margin-left:5px;">
    	<div id="hot-boardList"></div>
    </div> 
    
    <div class="centerarea">
    	<div class="contents-banner" style="border:none; margin-bottom:60px;"><img src="/images/detailbanner.jpg" width="660"></div>
    	
		<a class="px-2 text-gray-400 hover:text-blue-500 dark:hover:text-blue-200" href="/dokky/main" style="font-size:1.6rem;">목록으로</a> 
		<hr style="border: solid 1px; margin-top:1px;">
		
		<div class="detail-contentinfo" id="detail-contentinfo">
			<input type="hidden" id="hidden-userInfo" value="${sessionScope.user.userName}">
			<div class="image-writer"></div>
			<div class="contents-block">
				<a class="contents-writer" id="contents-writer"></a>
				<div class="contents-detailinfo">
					<i class="fa-regular fa-clock"></i>&nbsp;<span id="create-dt"></span>
					&nbsp;&nbsp;<i class="fa-regular fa-eye"></i>&nbsp;<span id="board-hit"></span>
				</div> 
			</div>
			
		</div>
		<div class="detail-title" id="detail-title"></div>
		<hr style="border:solid 1px;">
		
		<div class="detail-contents" id="detail-contents"></div>
		<hr style="border:solid 1px;">
		
		<form id="frm-comment">
			<div class="comment-area" id="comment-area">
				<div class="comment-input" id="comment-input">	<!-- 댓글 입력창 -->
					<div class="image-commenter-writer" >
						<input type="hidden" name="userNo" id="userNo" value="${sessionScope.user.userNo}">
						<c:choose>
							<c:when test="${!empty sessionScope.user and !empty sessionScope.user.userUploadPath and !empty sessionScope.user.userImg}">
								<img src="${sessionScope.user.userUploadPath}${sessionScope.user.userImg}"  height="50px" width="50px" id="img-radius-cmt">
							</c:when>
							<c:otherwise>
								<img src="/images/dokky_profile.png" height="50px" width="50px" id="img-radius-cmt">
							</c:otherwise>
						</c:choose>
						<textarea rows="5" cols="70" id="comment-box" onkeydown="fncheckByte(this);" wrap="hard"></textarea>
					</div>
					<div id="commentByte" class="commentByte">
						<span id="messagebyte">0</span><span> / 1000byte</span>
						<button type="button" class="btn btn-primary" id="btn-comment">등록</button>
					</div>
				</div>
			</div>
		</form>
		<div class="comment-list" id="comment-list">	<!-- 댓글 목록 -->
		</div>
    </div>
    
    <div class="rightarea"></div>
  <script src="/js/board/detail.js"></script>
</body>
</html>