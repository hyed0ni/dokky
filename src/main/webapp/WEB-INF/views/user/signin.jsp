<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../layout/header.jsp" %>

		<link href="/css/index.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>

.bd-placeholder-img {
  font-size: 1.125rem;
  text-anchor: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
}

#floatingInput{
  font-size: 1rem;
  height: 50px;
  margin-bottom: 10px;
}

#floatingPassword{
  font-size: 1rem;
  height: 50px;
}

#bottom-wrap !important {
  font-size: 12px;
}

.btn-primary !important {
  --bs-btn-color: var(--main-color);
}

body{
  padding-top: 150px;
}

</style>


<link href="signin.css" rel="stylesheet">
</head>


<body class="text-center">
<%@ include file=".././layout/navbar.jsp" %>


<main class="form-signin">
  <form >
    <a href="/"><img class="mb-4" src=".././images/dokky.png"  alt="DOKKY-LOGO" height="50"></a>
    <h1 class="h3 mb-3 fw-normal">DOKKY에 오신것을 환영합니다.</h1>
    <h6>DOKKY는 MCP 개발자들을 위한 지식공유의 장입니다.</h6>
    <br>
    <h6>-----------  DOKKY 아이디로 로그인  ------------</h6>
    <br>

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
</main>
  
<%@ include file=".././layout/copyright.jsp" %>
<%@ include file=".././layout/footer.jsp" %>