<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../layout/header.jsp" %>
<%@ include file="../layout/navbar.jsp" %>
		<link href="/css/user/signin.css" rel="stylesheet" type="text/css" />
    </head>
<body class="text-center">

<div class="main">
	<div class="signin">
	  
	  		<div class="top">
			    <a href="/"><img class="mb-4" src=".././images/dokky.png"  alt="DOKKY-LOGO" height="30"></a>
			    <p class="h3 mb-3 fw-normal" id="welcome">DOKKY에 오신것을 환영합니다.</p>
			    <p>DOKKY는 MCP 개발자들을 위한 지식공유의 장입니다.</p>
			    <br>
			    <p>-----------  DOKKY 아이디로 로그인  ------------</p>
			    <br>
	  		</div>
		    
		<form method="POST"
			    action="/dokky/signin"
			    id="frm-signin">
		    <div class="form-floating">
		      <label for="floatingInput">아이디</label>
		      <input type="text" class="form-control" name="userEmail" id="floatingInput" placeholder="name@example.com">
		    </div> 
		    <div class="form-floating">
		      <label for="floatingPassword">비밀번호</label>
		      <input type="password" class="form-control" name="userPw" id="floatingPassword" placeholder="●●●●">
		    </div>
		    <br>
	    	<div>
	    		<input type="hidden" name="url" value="${url}">
	    		<button class="w-100 btn btn-lg btn-primary mb-3" type="submit">로그인</button>
	    	</div>
		</form>
		<br>
		<div id="bottom-wrap">
		  <span>아직 회원이 아니신가요?</span>
		  <a href="/dokky/signup">회원가입</a>
		</div>
		
	</div>
</div>

	<!-- 비밀번호 변경 성공 alert -->
	<c:if test="${!empty resultMsg}">
		<script>
			alert("${resultMsg}");
			document.getElementById("origin-pw").focus();
		</script>
	</c:if>
	
	<!-- 로그인 실패 alert -->
	<c:if test="${!empty errorMsg}">
		<script>
			alert("${errorMsg}");
		</script>
	</c:if>
	
	<script>
	</script>

  
<%@ include file=".././layout/copyright.jsp" %>
<%@ include file=".././layout/footer.jsp" %>