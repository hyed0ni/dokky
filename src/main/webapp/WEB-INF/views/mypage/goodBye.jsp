<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		<link href="/css/mypage/goodBye.css" rel="stylesheet" type="text/css" />
	</head>
<body>
<%@ include file="../layout/header.jsp" %>

	<div class="main good-bye">
		<div class="centered-container">
		<img src="/images/goodbye.gif" alt="안녕히가세요">
			그동안 DOKKY와 함께 해주셔서 감사드립니다. 🥰
		</div>
	</div>

	<script>
		window.onload = setTimeout(() => location.href = "/", 3000);
	</script>

<%@ include file="../layout/footer.jsp" %>