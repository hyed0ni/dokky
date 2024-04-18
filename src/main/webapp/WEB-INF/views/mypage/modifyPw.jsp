<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		<!-- bootstrap css -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link rel="stylesheet" href="/css/common/common.css" type="text/css">
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
				<!-- 현재 비밀번호 -->
				<div class="col-12">
					<label for="origin-pw" class="form-label">현재 비밀번호</label>
					<input type="text" class="form-control" id="origin-pw">
				</div>
				
				<!-- 신규 비밀번호 -->
				<div class="col-12 pw-mg">
					<label for="new-pw" class="form-label">신규 비밀번호</label>
					<input type="text" class="form-control" id="new-pw" placeholder="최소 6자 이상(알파벳, 숫자 필수)">
				</div>
				
				<!-- 신규 비밀번호 확인 -->
				<div class="col-12 pw-mg">
					<label for="new-pw-chk" class="form-label">신규 비밀번호 확인</label>
					<input type="text" class="form-control" id="new-pw-chk">
				</div>
				<button class="w-100 btn btn-primary btn-lg" type="button">비밀번호 변경</button>
			</div>
		</div>
	</div>