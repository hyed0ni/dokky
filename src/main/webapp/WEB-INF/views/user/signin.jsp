<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file=".././layout/header.jsp" %>

<div class="main">
<div class="wrp1" align="center">
<a href="${contextPath}/">
 <img src=".././images/dokky.png" alt="DOKKY 로고" height="50">
</a> <br>
<h2>DOKKY에 오신것을 환영합니다.</h2>
<h5>DOKKY는 MCP 개발자들을 위한 지식공유의 장입니다.</h5>
<h6>-----------  OKKY 아이디로 로그인  ------------</h6>

</div>

<div class="wrp2" align="center">
  <form method="POST"
        action="/user/signin.do">
        
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
    <a href="/user/signup.page">회원가입</a>
  </div>
  
</div>
  </div>
</body>
</html>