<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>
<%@ include file="layout/header.jsp" %>
	<link href="/css/detail.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
 </head>
<body>
<%@ include file="layout/navbar.jsp" %>

  <form class="body"> 
    <div class="leftarea"></div> 
    
    <div class="centerarea">
    	<div class="contents-banner"><img src="/images/detailbanner.jpg" alt="디테일배너지롱" height="145" width="800"></div>
    	
		<div class="detail-contentinfo">
			<div class="image-writer" ><img src="/images/dokky.png" alt="DOKKY 로고" height="30"></div>
			<div class="contents-block">
				<a class="contents-writer" id="contents-writer">작성자이름</a>
				<div class="contents-detailinfo">
					<span id="create-dt"><img src="/images/dokky.png" alt="DOKKY 로고" height="20">작성일</span>
					<span id="board-hit"><img src="/images/dokky.png" alt="DOKKY 로고" height="20">조회수</span>
				</div>
			</div>
			<div class="contents-button">
				<button type="button" class="btn btn-primary">수정</button>
				<button type="button" class="btn btn-danger" id="btn-delete">삭제</button>
			</div>
		</div>
		
		<div class="detail-title" id="detail-title">제목이 들어갈 공간이지롱</div>
		<div class="detail-contents" id="detail-contents">
		안녕하세요 감사해요 잘 있어요 다시 만나요
		</div>
    </div>

    
    <div class="rightarea"></div>
  </form>
 
  <script>
  function getBoardNoFromURL() {
	    var urlParams = new URLSearchParams(window.location.search);
	    return urlParams.get('boardNo');	// 요청 파라미터에서 boardNo 를 찾아서 값을 리턴해줌
	}
	var boardNo = getBoardNoFromURL();	// 이전 페이지에서 보드 넘버 받아온거 
	
	const fnShowDetailBoard = () =>{
		$.ajax({
			type:'GET',
			url: '/dokky/getBoardByNo.do',
			data:'boardNo=' + boardNo,
			dataType:'json',
			success: function(data){
				document.getElementById('contents-writer').innerHTML = data.user.userName;
				document.getElementById('detail-title').innerHTML = data.boardTitle;
				document.getElementById('detail-contents').innerHTML = data.boardContent;
				document.getElementById('create-dt').innerHTML = moment(data.createDt).format('YYYY.MM.DD HH:mm');;
				document.getElementById('board-hit').innerHTML = ' 조회 ' + data.boardHit + '';
			},
			error:function(jqXHR){
				alert(jqXHR.statusText + '(' + jqXHR.status + ')');
			}
		})
	}
	fnShowDetailBoard();
	
	
	const fnClickDelete = ()=> {
		document.getElementById('btn-delete').addEventListener('click', function(evt){
			console.log('삭제 눌렀다!');
			console.log(boardNo);
			$.ajax({
				type:'DELETE',
				url: '/dokky/deleteBoard/' + boardNo,
				dataType:'json',
				success: function(data){
					alert(boardNo + '번 게시글은 삭제됐다!');
				},
				error:function(jqXHR){
					alert(jqXHR.statusText + '(' + jqXHR.status + ')');
				}
			})
		})
	}
	
	fnClickDelete();
  </script>
</body>
</html>