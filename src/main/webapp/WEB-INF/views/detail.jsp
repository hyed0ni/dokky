<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>상세보기페이지입니다</title>
 
 <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
 <link href="/css/detail.css" rel="stylesheet" type="text/css" />
 
</head>
<body>

  <form class="body"> 
    <div class="leftarea"></div> 
    
    <div class="centerarea">
    	<div class="contents-banner">대충 오키에는 배너가 들어가있는 공간</div>
    	
		<div class="detail-contentinfo">
			<div class="image-writer"><img src="/images/dokky.png" alt="DOKKY 로고" height="30"></div>
			<div class="contents-block">
				<a class="contents-writer">작성자이름</a>
				<div class="contents-detailinfo">
					<span><img src="/images/dokky.png" alt="DOKKY 로고" height="20">작성일</span>
					<span><img src="/images/dokky.png" alt="DOKKY 로고" height="20">조회수</span>
				</div>
			</div>
			<div class="contents-button">
				<button type="button" class="btn btn-primary">수정</button>
				<button type="button" class="btn btn-danger">삭제</button>
			</div>
		</div>
		
		<div class="detail-title" >제목이 들어갈 공간이지롱</div>
		<div class="detail-contents">
		안녕하세요 감사해요 잘 있어요 다시 만나요안녕하세요 감사해요 잘 있어요 다시 만나요안녕하세요 감사해요 잘 있어요 다시 만나요\
		안녕하세요 감사해요 잘 있어요 다시 만나요안녕하세요 감사해요 잘 있어요 다시 만나요안녕하세요 감사해요 잘 있어요 다시 만나요
		안녕하세요 감사해요 잘 있어요 다시 만나요안녕하세요 감사해요 잘 있어요 다시 만나요안녕하세요 감사해요 잘 있어요 다시 만나요
		안녕하세요 감사해요 잘 있어요 다시 만나요안녕하세요 감사해요 잘 있어요 다시 만나요안녕하세요 감사해요 잘 있어요 다시 만나요
		안녕하세요 감사해요 잘 있어요 다시 만나요안녕하세요 감사해요 잘 있어요 다시 만나요안녕하세요 감사해요 잘 있어요 다시 만나요
		안녕하세요 감사해요 잘 있어요 다시 만나요안녕하세요 감사해요 잘 있어요 다시 만나요안녕하세요 감사해요 잘 있어요 다시 만나요
		</div>
    </div>

    
    <div class="rightarea"></div>
  </form>
 
  <script>
 
  </script>
</body>
</html>