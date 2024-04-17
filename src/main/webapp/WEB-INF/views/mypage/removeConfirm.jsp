<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		<!-- bootstrap css -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link rel="stylesheet" href="/css/common/common.css" type="text/css">
		<link rel="stylesheet" href="/css/mypage/removeConfirm.css" type="text/css">
	</head>
<body>

	<div class="main">
		<div class="remove-confirm">
			<div>
				<a href="/"><img src=".././images/dokky.png" alt="DOKKY 로고" height="50"></a>
				<h4 class="mb-3">회원 탈퇴 시 아래와 같이 처리됩니다.</h4>
			</div>
			<div class="space-y-3 text-center text-sm">
				<p class="break-keep">회원 탈퇴일로부터 계정과 닉네임을 포함한 계정 정보(아이디/이메일/닉네임)는 
					<a target="_blank" class="font-medium underline" href="/legal/20220101-privacy">개인정보 보호정책</a>에 따라 
					<span class="font-semibold">60일간 보관(잠김)</span>되며, 60일 경과된 후에는 모든 개인 정보는 완전히 삭제되며 더 이상 복구할 수 없게 됩니다.</p>
				<p class="break-keep">작성된 게시물은 삭제되지 않으며, 익명처리 후 OKKY로 소유권이 귀속됩니다. 게시물 삭제가 필요한 경우에는 
					<a href="mailto:info@okky.kr" class="underline">관리자 메일</a>로 문의해 주시기 바랍니다.</p>
			</div>
			<div>
				<button class="w-100 btn btn-primary btn-lg cancel" type="button">취소</button>
				<button class="w-100 btn btn-primary btn-lg warning" type="button">예, 탈퇴하겠습니다.</button>
			</div>
		</div>
	</div>