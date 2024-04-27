<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../layout/header.jsp" %>
	<link href="/css/board/list.css" rel="stylesheet" type="text/css" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	</head>
<body>
	<%@ include file="../layout/navbar.jsp"%>
	<div class="main">
		<div class="box"></div>
		<div class="table-container">
			<div class="row mb-3">
				<div class="col">
					<div class="search-container">
						<a role="button" class="btn btn-primary me-2 " id="add" href="/dokky/add">작성하기</a>
						<form action="/dokky/main" method="GET" name="searchform" onsubmit="return searchformaction(this,1)">
							<input type="hidden" name="page" value="1" />
							<input type="text" placeholder="검색어를 입력해주세요." name="search" <%-- value="${param.search}"> --%>>
							<button type="submit" class="search-btn">
								<i class="fa fa-search"></i>
							</button>
						</form>
					</div>
				</div>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">작성자</th>
						<th scope="col">제목</th>
						<th scope="col">조회수</th>
						<th scope="col">등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${boardList.size() > 0}">
							<c:forEach var="board" items="${boardList}">
								<tr>
									<td>${board.user.userName}</td>
									<td><a href="/dokky/detail?boardNo=${board.boardNo}">${board.boardTitle}</a></td>
									<td><i class="fa-regular fa-eye"></i>&nbsp;${board.boardHit}</td>
									<td><fmt:formatDate value="${board.boardCreateDt}" pattern="yyyy-MM-dd HH:mm" /></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5">검색된 게시물이 없습니다</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			
			<nav aria-label="Page navigation example">
				<ul class="pagination">
					<li class="page-item">
					<a class="page-link" href="javascript:searchformaction(document.searchform, ${prevPage})" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
					<c:forEach begin="${startPage}" end="${endPage}" var="p">
					<li class="page-item"><a class="page-link" href="javascript:searchformaction(document.searchform, ${p})">${p}</a></li>
					</c:forEach>
					<li class="page-item"><a class="page-link" href="javascript:searchformaction(document.searchform, ${nextPage})" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
				</ul>
			</nav>
		</div>
	</div>
	
	<%@ include file="../layout/copyright.jsp" %>
	<%@ include file="../layout/footer.jsp" %>
	<script type="text/javascript">
	
	// 전역 변수 
	var boardListSize = ${boardList.size()};
	
	// 페이징 검색 게시물 없을 시 hide 처리
	if(boardListSize != 0){
		let page = $('.pagination').show();	
	}else{
		let page = $('.pagination').hide();	
	}
	
	// 검색 기능 처리
	function searchformaction(f, p) {
		if (f.search == '') {
			alert("검색어를 입력하세요");
			f.search.focus();
			return false;
		}
			f.page.value = p;
			f.submit();
		}
	</script>