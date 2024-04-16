<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>
<%@ include file="layout/header.jsp" %>
	<link href="/css/index.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
	</head>
<body>
<%@ include file="layout/navbar.jsp" %>

<div class="main">
    <div class="box"></div>
    <div class="table-container">
        <div class="row justify-content-center mb-3">
            <div class="col-auto">
                <button type="button" class="btn btn-primary" id="addBtn">작성하기</button>
            </div>
            <div class="col-auto">
                <div class="search-container">
                    <form action="/search" method="GET">
                        <input type="text" placeholder="검색어를 입력해주세요." name="q">
                        <button type="submit" class="search-btn"><i class="fa fa-search"></i></button>
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
                    <th scope="col">등록된 날짜</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row">별밤</th>
                    <td>안녕하세요</td>
                    <td>39</td>
                    <td>2024.10.30</td>
                </tr>
                <tr>
                    <th scope="row">도킹</th>
                    <td>개발자란..</td>
                    <td>102</td>
                    <td>2024.10.30</td>
                </tr>
                <tr>
                    <th scope="row">구디</th>
                    <td>개발자란..</td>
                    <td>102</td>
                    <td>2024.10.30</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="layout/copyright.jsp" %>
<%@ include file="layout/footer.jsp" %>
