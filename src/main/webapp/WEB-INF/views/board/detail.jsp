<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>
<%@ include file="../layout/header.jsp" %>
	<link href="/css/board/detail.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
 </head>
<body>
<%@ include file="../layout/navbar.jsp" %>
	
<div class="main">
  <form class="body"> 
    <div class="leftarea">
    	<div class="px-2 hot-name" id="hot-name" style="font-size:1.7rem; text-align:left;">
    		<i class="fa-solid fa-fire"></i>&nbsp;
    		<span>오늘의</span>
    		<span style="color:red; font-weight:bold;">추천</span>
    		&nbsp;<i class="fa-solid fa-fire"></i>
   		</div>
   		<hr style="border: solid 1px; margin-top:1px; width:70%; margin-left:5px;">
    	<div id="hot-boardList"></div>
    </div> 
    
    <div class="centerarea">
    	<div class="contents-banner" style="border:none; margin-bottom:5px;"><img src="/images/detailbanner.jpg" height="145" width="660"></div>
    	
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
		
		<form id="frm-comment">
			<div class="comment-area" id="comment-area">
				<div class="comment-input" id="comment-input">	<!-- 댓글 입력창 -->
					<div class="image-commenter-writer" >
						<input type="hidden" name="userNo" id="userNo" value="${1}">
						<img src="/images/dokky.png" alt="DOKKY 로고" height="50">
						<textarea rows="5" cols="70" id="comment-box"></textarea>
					</div>
					<button type="button" class="btn btn-primary" id="btn-comment">등록</button>
				</div>
			</div>
		</form>
		
		
		<div class="comment-list" id="comment-list">	<!-- 댓글 목록 -->
		</div>
		
    </div>
    
    <div class="rightarea"></div>
  </form>
 </div>
 
  <script>
  function getBoardNoFromURL() {
	    var urlParams = new URLSearchParams(window.location.search);
	    return urlParams.get('boardNo');	// 요청 파라미터에서 boardNo 를 찾아서 값을 리턴해줌
	}
	var boardNo = getBoardNoFromURL();	// 이전 페이지에서 보드 넘버 받아온거 
	
	const fnAddBoardHit = () =>{
		$.ajax({
			type:'GET',
			url: '/dokky/putBoardHit',
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
			url: '/dokky/getBoardByNo',
			data:'boardNo=' + boardNo,
			dataType:'json',
			success: function(data){
				fnAddBoardHit();
				document.getElementById('contents-writer').innerHTML = data.user.userName;
				document.getElementById('detail-title').innerHTML = data.boardTitle;
				document.getElementById('detail-contents').innerHTML = data.boardContent;
				document.getElementById('create-dt').innerHTML = moment(data.boardCreateDt).format('YYYY.MM.DD HH:mm');;
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
			$.ajax({
				type:'DELETE',
				url: '/dokky/deleteBoard/' + boardNo,
				dataType:'json',
				success: function(data){
					alert(boardNo + '번 게시글은 삭제됐다!');
					location.href = "/dokky/main";
				},
				error:function(jqXHR){
					alert(jqXHR.statusText + '(' + jqXHR.status + ')');
				}
			})
		})
	}
	
	const fnClickModify = () => {
		
		document.getElementById('btn-modify').addEventListener('click', function(evt) {
			window.location.href = '/dokky/modify?boardNo=' + boardNo;
		})
	}
	
	const fngetHotBoard = ()=>{
		$.ajax({
			type:'GET',
			url: '/dokky/getBoard',
			dataType : 'json',
			success : function(data) 
			{
				console.log(data.length);
				for(var i = 1; i < 4; i++)
				{
					data.sort((a,b) => b.boardHit - a.boardHit);
	 		        let str = '<div style="text-align:left;">' 
	 		        + '<a class="text-gray-400 hover:text-blue-500 dark:hover:text-blue-200" href="/dokky/detail?boardNo=' 
	 		        		+ data[i-1].boardNo+ '" style="font-size:1.5rem;">'
	 		        + '-' + data[i - 1].boardTitle + '</a>' + '</div>';
	 		        $('#hot-boardList').append(str);
				}
			},
			error:function(jqXHR)
			{
				alert(jqXHR.statusText + '(' + jqXHR.status + ')');
			}
		})
	}
	
  	const fnRegistComment = ()=>{
  		document.getElementById('btn-comment').addEventListener('click', function(){
  			$.ajax({
  				type:'POST',
  				url: '/detail/registCmt',
  				contentType:'application/json',
  				data: JSON.stringify({
  					'commentContent' : $('#comment-box').val(),
  					'userNo' : $('#userNo').val(),
  					'boardNo' : boardNo
  				}),
  				dataType: 'json',
  				success:(data)=>{
  					if(data === 1)
					{
						$('#comment-box').val('');
						location.reload();
					}
  					else
  						alert('댓글 등록 실패');
  				},
  				error:(jqXHR)=>{
  					console.log($('#comment-box').val());
  					alert(jqXHR.statusText + '(' + jqXHR.status + ')');  					
  				}
  			})
  		})
  	}
  	
  	const fnShowCommentList = ()=>{
		$.ajax({
			type:'GET',
			url: '/detail/getCmt/' + boardNo,
			dataType : 'json',
			success : function(data) 
			{
				for(var i = 0; i < data.length; i++)
				{
	 		        let str = '<ol>';
	 		        str += '<div class="image-commenter" ><img src="/images/dokky.png" alt="DOKKY 로고" height="30">'; 
					str += '<a class="comment-writer" id="comment-writer">' + data[i].user.userName + '&nbsp;</a>';
					str += '<i class="fa-regular fa-clock"></i>&nbsp;<span id="create-dt">' + data[i].createDt + '</span>';
					str += '<input type="hidden" value="' + i + '">';
					str += '<div class="comment-buttons">';
					str += '<button type="button" class="btn-comment-modify" id="btn-comment-modify' + i +'" data-commentNo="'
							+ data[i].commentNo +'">수정</button>';
					str += '<button type="button" class="btn-comment-remove" id="btn-comment-remove' + i +'" data-commentNo="'
							+ data[i].commentNo +'">삭제</button>';
					str += '</div>';
					str += '</div>';
					str += '<div class="comment-text" id="comment-text' + i +'">' + data[i].commentContent + '</div>'; 
					str += '<hr style="border:solid 1px; margin-top:10px;"></ol>';	
	 		        $('#comment-list').append(str);
	 		       	fnRemoveComment(i);
	 		       	fnModifyComment(i, data[i].commentContent, data[i].commentNo);
				}
			},
			error:function(jqXHR)
			{
				alert(jqXHR.statusText + '(' + jqXHR.status + ')');
			}
		})  
  	}
  	
  	const fnRemoveComment = (evt)=>{
       	var removeButton = document.getElementById('btn-comment-remove' + evt + '');
	      	removeButton.addEventListener('click', function() {
	        var commentNo = $(this).data('commentno');
			$.ajax({
				type:'DELETE',
				url:'/detail/deleteCmt/' + commentNo,
				dataType:'json',
				success: function(data){
					alert('댓글은 삭제됐다!');
					location.reload();
				},
				error:function(jqXHR){
					alert(jqXHR.statusText + '(' + jqXHR.status + ')');
				}
			})	
      });  		
  	}
  	
  	const fnModifyComment = (evt, evt2, evt3)=>{
       	var modifyButton = document.getElementById('btn-comment-modify' + evt + '');
       		modifyButton.addEventListener('click', function() {
			var commentText = document.getElementById('comment-text' + evt + '');
			commentText.textContent = "";
			let str = '<textarea rows="5" cols="75" id="commentupdate-box">'+ evt2 +'</textarea>';
			str += '<div class="modifybycomment">';
			str += '<button type="button" class="btn btn-danger" id="btn-comment-updatecancle">취소</button>';
			str += '<button type="button" class="btn btn-primary" id="btn-comment-update">등록</button>';
			str += '</div>';
			$(commentText).append(str);
			
			document.getElementById('btn-comment-updatecancle').addEventListener('click', function(){
				commentText.textContent = "";
				$(commentText).append(evt2);
			})
			
			document.getElementById('btn-comment-update').addEventListener('click', function(){
				console.log(evt);
				$.ajax({
					type:'POST',
					url:'/detail/modifyCmt',
	 				contentType:'application/json',
	  				data: JSON.stringify({
	  					'commentContent' : $('#commentupdate-box').val(),
	  					'userNo' : evt3,
	  					'boardNo' : evt3
	  				}),
	  				dataType: 'json',
	  				success:(data)=>{
	  					if(data === 1)
						{
	  						alert('댓글 수정 성공');
							$('#commentupdate-box').val('');
							location.reload();
						}
	  					else
	  						alert('댓글 수정 실패');
	  				},
	  				error:(jqXHR)=>{
	  					console.log($('#comment-box').val());
	  					alert(jqXHR.statusText + '(' + jqXHR.status + ')');  					
	  				}
				})
			})
			
      });  		
  	}
  	
  	
  	fnShowCommentList();
  	fnRegistComment();
	fngetHotBoard();	
	fnShowDetailBoard();
	fnClickDelete();
	fnClickModify();
  </script>
</body>
</html>