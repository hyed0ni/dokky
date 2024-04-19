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
    <div class="leftarea">
    	<div class="px-2 hot-name" id="hot-name" style="font-size:1.7rem; text-align:left;">
    		<i class="fa-solid fa-fire"></i>&nbsp;
    		<span>오늘의</span>
    		<span style="color:red; font-weight:bold;">추천</span>
    		&nbsp;<i class="fa-solid fa-fire"></i>
   		</div>
   		<hr style="border: solid 1px; margin-top:1px; width:90%; margin-left:5px;">
    	<div id="hot-boardList">
    		
    	</div>
    </div> 
    
    <div class="centerarea">
    	<div class="contents-banner" style="border:none; margin-bottom:5px;"><img src="/images/detailbanner.jpg" height="145" width="600"></div>
    	
		<a class="px-2 text-gray-400 hover:text-blue-500 dark:hover:text-blue-200" href="/dokky/main" style="font-size:1.2rem;">목록으로</a> 
		<hr style="border: solid 1px; margin-top:1px;">
		
		<div class="detail-contentinfo">
			<div class="image-writer" ><img src="/images/dokky.png" alt="DOKKY 로고" height="30"></div>
			<div class="contents-block">
				<a class="contents-writer" id="contents-writer">작성자이름</a>
				<div class="contents-detailinfo">
					<i class="fa-regular fa-clock"></i>&nbsp;<span id="create-dt">작성일</span>
					&nbsp;&nbsp;<i class="fa-regular fa-eye"></i>&nbsp;<span id="board-hit">조회수</span>
				</div> 
			</div>
			
			<div class="contents-button">
				<button type="button" class="btn btn-primary" id="btn-modify">수정</button>
				<button type="button" class="btn btn-danger" id="btn-delete">삭제</button>
			</div>
		</div>
		<div class="detail-title" id="detail-title">제목이 들어갈 공간이지롱</div>
		<hr style="border:solid 1px;">
		
		<div class="detail-contents" id="detail-contents">안녕하세요 감사해요 잘 있어요 다시 만나요</div>
		<hr style="border:solid 1px;">
    </div>

    
    <div class="rightarea"></div>
  </form>
 
  <script>
  function getBoardNoFromURL() {
	    var urlParams = new URLSearchParams(window.location.search);
	    return urlParams.get('boardNo');	// 요청 파라미터에서 boardNo 를 찾아서 값을 리턴해줌
	}
	var boardNo = getBoardNoFromURL();	// 이전 페이지에서 보드 넘버 받아온거 
	
	const fnAddBoardHit = () =>{
		$.ajax({
			type:'GET',
			url: '/dokky/putBoardHit.do',
			data:'boardNo=' + boardNo,
			dataType:'json',
			success: function(data){
			},
			error:function(jqXHR){ 
				alert(jqXHR.statusText + '(' + jqXHR.status + ')');
			}			
		})
	}
	
	const fnShowDetailBoard = () =>{
		$.ajax({
			type:'GET',
			url: '/dokky/getBoardByNo.do',
			data:'boardNo=' + boardNo,
			dataType:'json',
			success: function(data){
				fnAddBoardHit();
				document.getElementById('contents-writer').innerHTML = data.user.userName;
				document.getElementById('detail-title').innerHTML = data.boardTitle;
				document.getElementById('detail-contents').innerHTML = data.boardContent;
				document.getElementById('create-dt').innerHTML = moment(data.createDt).format('YYYY.MM.DD HH:mm');;
				document.getElementById('board-hit').innerHTML = data.boardHit;
			},
			error:function(jqXHR){
				alert("디테일 에러");
				alert(jqXHR.statusText + '(' + jqXHR.status + ')');
			}
		})
	}
	
	
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
	
	const fngetHotBoard = ()=>{
		$.ajax({
			type:'GET',
			url: '/dokky/getBoard.do',
			dataType : 'json',
			success : function(data)
			{
				console.log(data.length);
				for(var i = 1; i < data.length + 1; i++)
				{
					data.sort((a,b) => b.boardHit - a.boardHit);
	 		        let str = '<div style="text-align:left;">' 
	 		        + '<a class="px-2 text-gray-400 hover:text-blue-500 dark:hover:text-blue-200" href="/dokky/detail?boardNo=' 
	 		        		+ data[i-1].boardNo+ '" style="font-size:1.5rem;">'
	 		        + data[i - 1].boardTitle + '</a>' + '</div>';
	 		        $('#hot-boardList').append(str);
				}
			},
			error:function(jqXHR)
			{
				alert(jqXHR.statusText + '(' + jqXHR.status + ')');
			}
		})
	}

	fngetHotBoard();	
	fnShowDetailBoard();
	fnClickDelete();
  </script>
</body>
</html>