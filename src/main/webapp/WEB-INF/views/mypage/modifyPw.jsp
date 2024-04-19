<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
				<form action="modify-password" method="post">
					<!-- 현재 비밀번호 -->
					<div class="col-12">
						<label for="origin-pw" class="form-label">현재 비밀번호</label>
						<input type="password" class="form-control" id="origin-pw" name="originPw">
					</div>
					
					<!-- 신규 비밀번호 -->
					<div class="col-12 pw-mg">
						<label for="new-pw" class="form-label">신규 비밀번호</label>
						<input type="password" class="form-control" id="new-pw" placeholder="최소 6자 이상(알파벳, 숫자 필수)" name="newPw">
					</div>
					
					<!-- 신규 비밀번호 확인 -->
					<div class="col-12 pw-mg">
						<label for="new-pw-chk" class="form-label">신규 비밀번호 확인</label>
						<input type="password" class="form-control" id="new-pw-chk" name="newPwChk">
					</div>
					<button class="w-100 btn btn-primary btn-lg">비밀번호 변경</button>
				</form>
			</div>
		</div>
	</div>
	
	<script>
		
	</script>
	
<%@ include file="../layout/footer.jsp" %>