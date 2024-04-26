<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../layout/header.jsp" %>
		<link rel="stylesheet" href="/css/mypage/removeUser.css" type="text/css">
	</head>
<body>

	<div class="main">
		<div class="remove-user">
			<div>
				<a href="/"><img src=".././images/dokky.png" alt="DOKKY 로고" height="50"></a>
				<h4 class="mb-3">회원 탈퇴 시 아래와 같이 처리됩니다.</h4>
			</div>
			<div class="space-y-3 text-center text-sm">
				<p class="break-keep">회원 탈퇴 일로부터 모든 계정 정보(이메일, 닉네임, 휴대전화 등)는<br>
					<a target="_blank" class="font-medium underline" href="#">개인정보 보호 정책</a>에 따라 
					<span class="font-semibold">60일간 보관(잠김)</span>되며, <br>
					60일 경과된 후에는 완전히 삭제되어 더 이상 복구할 수 없게 됩니다.<br>
					작성한 게시물 및 댓글 또한 모두 삭제됩니다.</p>
			</div>
			<form action="remove-user" method="post" id="remove-form">
				<div>
					<div class="col-12">
						<label for="origin-pw" class="form-label">비밀번호를 입력해 주세요.</label>
						<input type="password" class="form-control" id="origin-pw" name="originPw">
					</div>
				</div>
				<div>
					<button class="w-100 btn btn-primary btn-lg cancel" id="cancel-btn" type="button">취소</button>
					<button class="w-100 btn btn-primary btn-lg warning disabled" id="remove-user-btn">예, 탈퇴하겠습니다.</button>
				</div>
			</form>
		</div>
	</div>

	<script src="/js/mypage/removeUser.js"></script>
	
	<!-- 계정 삭제 실패 alert -->
	<c:if test="${!empty resultMsg}">
		<script>
			alert("${resultMsg}");
			document.getElementById("origin-pw").focus();
		</script>
	</c:if>
	
<%@ include file="../layout/footer.jsp" %>