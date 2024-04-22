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
				<p class="break-keep">회원 탈퇴일로부터 계정과 닉네임을 포함한 계정 정보(아이디/이메일/닉네임)는 <br>
					<a target="_blank" class="font-medium underline" href="/legal/20220101-privacy">개인정보 보호정책</a>에 따라 
					<span class="font-semibold">60일간 보관(잠김)</span>되며, <br>
					60일 경과된 후에는 모든 개인 정보는 완전히 삭제되며 더 이상 복구할 수 없게 됩니다.</p>
				<p class="break-keep bk2">작성된 게시물은 삭제되지 않으며, 익명처리 후 OKKY로 소유권이 귀속됩니다. <br>
				게시물 삭제가 필요한 경우에는 <a href="mailto:info@okky.kr" class="underline">관리자 메일</a>로 문의해 주시기 바랍니다.</p>
			</div>
			<form action="remove-user/1" method="post" id="remove-form">
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
	
	<!-- 회원 탈퇴 실패 -->
	<c:if test="${!empty errorMsg}">
		<script>
			alert("${errorMsg}");
			document.getElementById("origin-pw").focus();
		</script>
	</c:if>
	
<%@ include file="../layout/footer.jsp" %>