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

<div class="main" style="width:1090px">
    <div class="box"></div>
    <div class="table-container">
        <div class="row justify-content-center mb-3">
            <div class="col-auto">
                <a href="/dokky/add" type="button" class="btn btn-primary" id="addBtn">작성하기</a>
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
                    <th scope="row" id="writer1">별밤</th>
                    <td id="title1">안녕하세요</td>
                    <td id="board-hit1">39</td>
                    <td id="create-dt1">2024.10.30</td>
                </tr>
                <tr>
                    <th scope="row" id="writer2">도킹</th>
                    <td id="title2">개발자란..</td>
                    <td id="board-hit2">102</td>
                    <td id="create-dt2">2024.10.30</td>
                </tr>
                <tr>
                    <th scope="row" id="writer3">구디</th>
                    <td id="title3">개발자란..</td>
                    <td id="board-hit3">102</td>
                    <td id="create-dt3">2024.10.30</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<script>
const fngetBoard = ()=>{
	$.ajax({
		type:'GET',
		url: '/dokky/getBoard.do',
		dataType : 'json',
		success : function(data)
		{
			console.log(data.length);
			for(var i = 1; i < data.length + 1; i++)
			{
 				document.getElementById('writer' + i).innerHTML = data[i - 1].user.userName;
				document.getElementById('title' + i).innerHTML =  
					'<a href="/dokky/detail?boardNo=' + data[i-1].boardNo+ '" style="color:black">'+ data[i - 1].boardTitle + '</a>';
					
				document.getElementById('board-hit' + i).innerHTML = data[i - 1].boardHit;
				// data 배열에서 날짜를 가져와서 'yyyy.MM.dd.' 형식으로 포맷하고 출력
				document.getElementById('create-dt' + i).innerHTML = moment(data[i - 1].createDt).format('YYYY.MM.DD');
			}
		},
		error:function(jqXHR)
		{
			alert(jqXHR.statusText + '(' + jqXHR.status + ')');
		}
	})
}

fngetBoard();
</script>

<%@ include file="layout/copyright.jsp" %>
<%@ include file="layout/footer.jsp" %>
