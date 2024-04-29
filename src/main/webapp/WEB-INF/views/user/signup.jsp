<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../layout/header.jsp" %>
<%@ include file="../layout/navbar.jsp" %>
		<link href="/css/user/signup.css" rel="stylesheet" type="text/css" />
    </head>
<body>

<div class="main">
	<div class="signup">
		<div align="center">
		  <a href="/"><img class="mb-4" src=".././images/dokky.png"  alt="DOKKY-LOGO" height="30"></a>
		  <p class="h3 mb-3 fw-normal">DOKKY에 오신것을 환영합니다.</p>
		  <br>
		  <p>DOKKY는 MCP 개발자들을 위한 지식공유의 장입니다.</p>
		  <br>
		  <p>------  회원가입에 필요한 기본정보를 입력해주세요.  -------</p>		
		  </div>
			  
		<form method="POST"
			    action="/dokky/signup"
			        id="frm-signup">
			  
		<div class="col-md-7 col-lg-8">
		<div class="row g-3">
		
		  <div class="col-12">
		    <label for="inp-email" class="form-label">아이디</label>
		    <div class="input-group mb-3">
		      <input type="email" class="form-control frm-ctrl" name="userEmail" id="inp-email" placeholder="you@example.com" autocomplete="off">
		      <button type="button" class="btn btn-primary" id="btn-code">인증코드받기</button>
		    </div>
    		<div id="msg-email"></div>
		  </div>
		
		  <div class="col-12" id="code">
		    <label for="inp-code" class="form-label">인증코드</label>
		    <div class="input-group mb-3">
		      <input type="text" class="form-control frm-ctrl" id="inp-code" name="code" placeholder="인증코드입력">
		      <button type="button" class="btn btn-primary" id="btn-verify-code" disabled="disabled">인증하기</button>
		    </div>
		    <div id="msg-code"></div>
		  </div>
		
		  <div class="col-12">
		    <label for="inp-pw" class="form-label">비밀번호</label>
		    <input type="password" class="form-control" id="inp-pw" name="userPw" placeholder="비밀번호 4~12자, 영문/숫자/특수문자 중 2개 이상 포함">
		    <div id="msg-pw"></div>
		  </div>
		
		  <div class="col-12">
		    <label for="inp-name" class="form-label">닉네임</label>
		    <input type="text" class="form-control" name="userName" id="inp-name" placeholder="닉네임 2~8자, 영어/숫자/한글로 구성 (공백, 초성, 모음 불가)">
		    <div id="msg-name"></div>
		  </div>
		
		  <div class="my-3">
			  <label class="form-label">성별</label>
			  <div class="flex">
				  <div class="form-check">
					  <input id="none" name="userGender" type="radio" value="none" class="form-check-input" checked="checked" required>
					  <label class="form-check-label" for="none">선택 안 함</label>
				  </div>
				  <div class="form-check">
				    <input id="man" name="userGender" type="radio" class="form-check-input" value="man" required>
					  <label class="form-check-label" for="man">남성</label>
				  </div>
				  <div class="form-check">
				    <input id="woman" name="userGender" type="radio" value="woman" class="form-check-input" required>
				    <label class="form-check-label" for="woman">여성</label>
				  </div>
		    </div>
		  </div>
				
		  <div class="col-12">
		    <label for="inp-mobile" class="form-label">휴대전화</label>
		    <input type="text" class="form-control" name="userMoblie" id="inp-mobile" placeholder="- 제외하고 입력해주세요.">
		    <div id="msg-mobile"></div>
		  </div>
		
		  <div align="center">
		    <button class="w-100 btn btn-primary btn-lg" id="btn-signup" type="submit">회원가입</button>
		  </div>
		  <br>
		  <div align="center" class="already">
			<span>이미 회원이신가요?</span>
			<a href="/dokky/signin">로그인</a>
		  </div>
		  
		</div>
	  </div>
		</form>
	</div>
</div>

<script src="/js/user/signup.js"></script>

</body>

<%@ include file=".././layout/copyright.jsp" %>
<%@ include file=".././layout/footer.jsp" %>