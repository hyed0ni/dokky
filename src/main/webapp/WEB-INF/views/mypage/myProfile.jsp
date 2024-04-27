<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../layout/header.jsp" %>
<%@ include file="../layout/navbar.jsp" %>
		<link rel="stylesheet" href="/css/mypage/myProfile.css" type="text/css">
		<link rel="stylesheet" href="/css/mypage/activity.css" type="text/css">
	</head>
<body>

	<div class="main">
		<div class="my-profile">
		<input type="hidden" id="user-no" name="user-no" value="${user.userNo}">
			<!-- 좌측 -->
			<div class="d-flex flex-column flex-shrink-0 p-3 bg-body-tertiary">
			    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
					<span class="fs-4">마이페이지</span>
			    </a>
			    <ul class="nav nav-pills flex-column mb-auto">
					<li class="nav-item"><a href="#" class="nav-link active" id="profile-link" aria-current="page">프로필</a></li>
					<li class="nav-item"><a href="#" class="nav-link" id="my-activity">활동 기록</a></li>
			    </ul>
			</div>
			
			<!-- 우측 -->
			<div id="profile-container" class="col-md-7 col-lg-8 profile-con">
 				<h4 class="mb-3">회원 정보</h4>
 				<form action="mypage" method="post" enctype="multipart/form-data" id="modify-form" class="needs-validation" novalidate>
 					<div>
 						<!-- 프로필 이미지: 기본 값 -->
 						<c:if test="${empty user.userImg}">
 							<img src="/images/dokky_profile.png" id="profile-img"  data-src="/images/dokky_profile.png">
 						</c:if>
 						
 						<!-- 프로필 이미지 -->
 						<c:if test="${!empty user.userImg}">
 							<img src="${user.userUploadPath}${user.userImg}" id="profile-img" data-src="${user.userUploadPath}${user.userImg}">
 						</c:if>
 					
						<div id="update-img">변경</div>
						<input type="file" id="file-input" accept="image/*" name="profileImg">
					</div>
 				
 					<!-- 이메일 -->
	 				<div class="col-12 user-info">
						<label for="email" class="form-label">이메일</label>
						<input type="text" class="form-control" id="email" name="email" value="${user.userEmail}" readonly>
					</div>
					
					<!-- 닉네임 -->
					<div class="col-12 user-info">
						<label for="user-name" class="form-label">닉네임</label>
						<input type="text" class="form-control" id="user-name" name="userName" value="${user.userName}" maxlength="16">
						<div class="none" id="user-name-msg">
							닉네임은 2~8자의 영어/숫자/한글로 구성되어야 하며, <br>
							한글 초성 및 모음은 허용되지 않습니다.
						</div>
					</div>
    
    				<!-- 성별 -->
					<div class="my-3 user-info">
						<label class="form-label">성별</label>
						<div class="flex">
							<div class="form-check">
								<input id="man" name="userGender" value="man" type="radio" class="form-check-input"
										 ${user.userGender == 'man' ? 'checked' : ''} required>
								<label class="form-check-label" for="man">남성</label>
							</div>
							<div class="form-check">
								<input id="woman" name="userGender" value="woman" type="radio" class="form-check-input"
										${user.userGender == 'woman' ? 'checked' : ''} required>
								<label class="form-check-label" for="woman">여성</label>
							</div>
							<div class="form-check">
								<input id="no" name="userGender" value="none" type="radio" class="form-check-input" 
										${user.userGender == 'none' ? 'checked' : ''} required>
								<label class="form-check-label" for="no">선택 안 함</label>
							</div>
						</div>
 					</div>

					<!-- 휴대전화 -->
					<div class="my-3 user-info">
						<label class="form-label">휴대전화</label>
						<div class="flex" style="justify-content: space-between;">
							<div class="col-12">
								<input type="text" class="form-control phone" id="user-mobile" name="userMobile" value="${user.userMobile}" maxlength="11">
								<div class="none" id="user-mobile-msg">휴대전화는 11자리로 구성되어야 합니다.</div>
							</div>
					
							<!-- 저장 -->
							<div class="flex update-user">
								<button class="w-100 btn btn-primary btn-lg" type="submit">저장</button>
							</div>
						</div>
					</div>
				</form>
					
				<hr class="my-4">
				
				<form>
					<!-- 비밀번호 -->
					<div class="flex update-pw">
						<label class="form-label">비밀번호</label>
						<button class="w-100 btn btn-primary btn-lg warning" id="update-pw-btn" type="button">비밀번호 변경</button>
					</div>
				</form>
				
				<hr class="my-4">
				
				<form action="remove-user" method="post" id="remove-user-form">
					<!-- 계정 삭제 -->
					<label class="form-label">계정 삭제</label>
					<div class="mt-3 space-y-2 rounded-md border border-gray-500/50 p-3">
						<p class="text-sm text-gray-500">
							회원 탈퇴 일로부터 모든 계정 정보(이메일, 닉네임, 휴대전화 등)는<br>
							<a class="font-semibold underline" href="#">개인정보 처리 방침</a>에 따라 <strong>60일간 보관(잠김)</strong>되며, 
							60일 경과된 후에는 완전히 삭제되어 더 이상 복구할 수 없게 됩니다.<br>
							작성한 게시물 및 댓글 또한 모두 삭제됩니다.</p>
					</div>
					<div class="form-check flex remove-user">
						<div>
							<input type="checkbox" class="form-check-input" id="withdrawal">
							<label class="form-check-label" for="withdrawal">계정 삭제에 관한 정책을 읽고 이에 동의합니다.</label>
						</div>
						<button class="w-100 btn btn-primary btn-lg warning disabled" id="remove-user-btn" type="button">회원 탈퇴</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script defer type="module" src="/js/mypage/activity.js"></script>

	<!-- 회원 정보 수정 alert  -->
	<c:if test="${!empty resultMsg}">
		<script>
			alert("${resultMsg}");
		</script>
	</c:if>
	
<%@ include file="../layout/copyright.jsp" %>
<%@ include file="../layout/footer.jsp" %>