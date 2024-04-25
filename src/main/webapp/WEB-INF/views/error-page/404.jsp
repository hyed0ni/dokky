<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<link href="/css/error.css" rel="stylesheet" type="text/css" />

	</head>
<body>
<%@ include file="../layout/header.jsp" %>


<div class="main error-div">
	<div class="centered-container">
	<img class="error-image" src="/images/jenkins.png" alt="죄송합니다. 이 페이지는 존재하지 않습니다.">
		죄송합니다. 이 페이지는 이제 제껍니다.
		<a href="/">홈으로 다시 가기</a>
	</div>
</div>

<%@ include file="../layout/footer.jsp" %>
