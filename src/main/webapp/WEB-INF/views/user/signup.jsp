<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file=".././layout/header.jsp" %>

<div  align="center">

<a href="/"> <img src=".././images/dokky.png" alt="DOKKY 로고" height="50"> </a>
<br>
<h2>DOKKY에 오신것을 환영합니다.</h2>
<h6>DOKKY는 MCP 개발자들을 위한 지식공유의 장입니다.</h6>
<h6>-----------  회원가입에 필요한 기본정보를 입력해주세요.  ------------</h6>

<div class="wrp2" align="center">
<form method="post"
	  action="/user/signup.do"
	  id="frm-signup">  
	  
  <div>
    <label for="email">아이디</label>
    <div><input type="text" id="email" name="email" placeholder="example@example.com"></div>
    <div><button type="button" id="btn-code" >인증코드받기</button></div>
  </div>
  <div>
    <label for="inp-code">인증코드</label>
    <div><input type="text" id="inp-code" placeholder="인증코드입력" disabled></div>
    <div><button type="button" id="btn-verify-code" disabled>인증하기</button></div>
  </div>
  <div>
  	<label for="pw">비밀번호</label>
  	<div><input type="password" name="pw" id="pw" placeholder="최소 6자 이상(알파벳, 숫자 필수)"></div>
  </div>
  <div>
  	<label for="name">닉네임</label>
  	<div><input type="text" name="name" id="name" placeholder="별명을 알파벳, 한글, 숫자를 10자 이하로 입력해주세요."></div>
  </div>
  <div>
    <label for="gender">성별</label>
    <div>
      <label for="none">선택안함</label>
      <input type="radio" name="gender" value="none" id="none" checked>
    </div>
    <div>
      <label for="man">남자</label>
      <input type="radio" name="gender" value="man" id="man">
    </div>
    <div>
      <label for="woman">여자</label>
      <input type="radio" name="gender" value="woman" id="woman">
    </div>
  </div>
  <div>
  	<label for="mobile">휴대전화</label>
  	<div><input type="text" name="mobile" id="mobile" placeholder="010-0000-0000"></div>
  </div>
  
  <br>
  
  <button type="submit" id="btn-signup">회원가입</button>
</form>
</div>

<div align="center">
  <span>이미 회원이신가요?</span>
  <a href="/user/signin.page">로그인</a>
</div>
</div>

<script>

const fnSignup = () => {
  document.getElementById('frm-signup').addEventListener('submit', (evt) => {
  alert("등록에 성공했습니다!");
  location.href = '/signin.page';
}

</script>


</body>
</html>