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
	  
	  		<div>
			    <a href="/"><img class="mb-4" src=".././images/dokky.png"  alt="DOKKY-LOGO" height="30"></a>
			    <h1 class="h3 mb-3 fw-normal">DOKKY에 오신것을 환영합니다.</h1>
			    <h6>DOKKY는 MCP 개발자들을 위한 지식공유의 장입니다.</h6>
			    <br>
			    <h6>-----------  DOKKY 아이디로 로그인  ------------</h6>
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
		  
		<div id="bottom-wrap">
		  <span>아직 회원이 아니신가요?</span>
		  <a href="/dokky/signup">회원가입</a>
		</div>
		
	</div>
</div>


<script>
/*document.getElementById('frm-signin').addEventListener('submit', function(event) {
  event.preventDefault(); // 기본 동작 중지

  // 로그인 폼 데이터 가져오기
  var formData = new FormData(this);

  // 서버로 로그인 요청 보내기
  fetch('/dokky/signin', {
    method: 'POST',
    body: formData
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('로그인에 실패하였습니다.');
    }
    // 로그인에 성공하면 이전 페이지로 리다이렉트
    window.location.href = response.url; // 서버에서 리다이렉트 URL을 반환하는 경우
  })
  .catch(error => {
    console.error('로그인 오류:', error);
    // 오류 처리 (예: 오류 메시지 표시)
  });
});*/
</script>

  
<%@ include file=".././layout/copyright.jsp" %>
<%@ include file=".././layout/footer.jsp" %>