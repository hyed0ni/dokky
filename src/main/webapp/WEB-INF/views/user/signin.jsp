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
	  
		    <a href="/"><img class="mb-4" src=".././images/dokky.png"  alt="DOKKY-LOGO" height="40"></a>
		    <h1 class="h3 mb-3 fw-normal">DOKKY에 오신것을 환영합니다.</h1>
		    <h6>DOKKY는 MCP 개발자들을 위한 지식공유의 장입니다.</h6>
		    <br>
		    <h6>-----------  DOKKY 아이디로 로그인  ------------</h6>
		    <br>
		    
		<form method="post"
			  action="/user/signin"
			  id="frm-signin">
		
		    <div class="form-floating">
		      <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
		      <label for="floatingInput">아이디</label>
		    </div>
		    <div class="form-floating">
		      <input type="password" class="form-control" id="floatingPassword" placeholder="●●●●●●">
		      <label for="floatingPassword">비밀번호</label>
		    </div>
		    <br>
		    <button class="w-100 btn btn-lg btn-primary mb-3" type="submit">로그인</button>
		</form>
		  
		<div id="bottom-wrap">
		  <span>아직 회원이 아니신가요?</span>
		  <a href="/user/signup">회원가입</a>
		</div>
		
	</div>
</div>
  
<%@ include file=".././layout/copyright.jsp" %>
<%@ include file=".././layout/footer.jsp" %>