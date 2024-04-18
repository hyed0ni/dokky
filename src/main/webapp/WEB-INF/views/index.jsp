<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>
<%@ include file="layout/header.jsp" %>
	<link href="/css/index.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	</head>
<body>
<%@ include file="layout/navbar.jsp" %>

<div class="main">
    <div class="box"></div>
		<div class="table-container">
			<div class="row mb-3">
				<div class="col">
					<div class="search-container">
						<form action="/search" method="GET">
							<input type="text" placeholder="검색어를 입력해주세요." name="q">
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
					<c:forEach var="board" items="${boardList}">
						<tr>
							<td>${board.user.userName}</td>
							<td><a href="/dokky/detail?boardNo=${board.boardNo}">${board.boardTitle}</a></td>
							<td><i class="fa-regular fa-eye"></i>&nbsp;${board.boardHit}</td>
							<td>${board.createDt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<nav aria-label="Page navigation example">
				<ul class="pagination">
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>
		</div>
	</div>

<%@ include file="layout/copyright.jsp" %>
<%@ include file="layout/footer.jsp" %>
