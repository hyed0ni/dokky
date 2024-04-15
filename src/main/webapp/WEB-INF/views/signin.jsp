<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DOKKY</title>
</head>
<body>
<style>
.wrp1 {
	margin: 50px auto;
}
</style>
<div class="wrp1" align="center">
<a href="${contextPath}/">
  <img src="${contextPath}/resources/image/okky.svg" alt="dokky" width="50px">
</a>
<h2>DOKKY에 오신것을 환영합니다.</h2>
<h4>DOKKY는 MCP 개발자들을 위한 지식공유의 장입니다.</h4>
</div>
<div class="wrp2" align="center">
  <form method="POST"
        action="${contextPath}/user/signin.do">
    <div>
      <label for="email">아이디</label><br>
      <input type="text" id="email" name="email" placeholder="example@email.com">
    </div>
    <br>
    <div>
      <label for="pw">비밀번호</label><br>
      <input type="password" id="pw" name="pw" placeholder="●●●●">
    </div>
    <br>
  </form>
  <div>
    <button type="submit">로그인</button>
  </div>
  <div>
    <span>아직 회원이 아니신가요?</span>
    <a href="${contextPath}/user/signup.page">회원가입</a>
  </div>
</div>
</body>
</html>