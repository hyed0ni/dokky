<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% request.setAttribute("title", "DOKKY - 비밀번호 변경"); %>
<%@ include file="../layout/header.jsp" %>
		<link rel="stylesheet" href="/css/mypage/modifyPw.css" type="text/css">
	</head>
<body>

	<div class="main">
		<div class="modify-pw">
			<div>
				<a href="/"><img src=".././images/dokky.png" alt="DOKKY 로고" height="50"></a>
				<h4 class="mb-3">변경하실 비밀번호를 입력해 주세요.</h4>
			</div>
			<div>
				<form action="modify-password" method="post" id="modify-form">
					<!-- 현재 비밀번호 -->
					<div class="col-12">
						<label for="origin-pw" class="form-label">현재 비밀번호</label>
						<input type="password" class="form-control" id="origin-pw" name="originPw">
						<div class="none" id="origin-pw-msg">현재 비밀번호를 입력해 주세요.</div>
					</div>
					
					<!-- 신규 비밀번호 -->
					<div class="col-12 pw-mg">
						<label for="new-pw" class="form-label">신규 비밀번호</label>
						<input type="password" class="form-control" id="new-pw" placeholder="비밀번호 4~12자, 영문/숫자/특수문자 중 2개 이상 포함" name="newPw">
						<div class="none" id="new-pw-msg">비밀번호는 4~12자로, 영문/숫자/특수문자 중 2개 이상을 포함해야 합니다.</div>
					</div>
					
					<!-- 신규 비밀번호 확인 -->
					<div class="col-12 pw-mg">
						<label for="new-pw-chk" class="form-label">신규 비밀번호 확인</label>
						<input type="password" class="form-control" id="new-pw-chk" name="newPwChk">
						<div class="none" id="new-pw-chk-msg">비밀번호가 서로 일치하지 않습니다.</div>
					</div>
					<button class="w-100 btn btn-primary btn-lg">비밀번호 변경</button>
				</form>
			</div>
		</div>
	</div>

	<script src="/js/mypage/modifyPw.js"></script>
	
	<!-- 비밀번호 변경 실패 alert -->
	<c:if test="${!empty resultMsg}">
		<script>
			alert("${resultMsg}");
			document.getElementById("origin-pw").focus();
		</script>
	</c:if>
	
<%@ include file="../layout/footer.jsp" %>