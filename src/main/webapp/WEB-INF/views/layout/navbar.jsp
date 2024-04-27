<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		<nav class="navbar fixed-top">
			<div class="container-fluid">
			<a href="/"><img src="/images/dokky.png" alt="DOKKY 로고" height="30"></a>
				<a class="navbar-brand fw-bold" href="/dokky/main" >DOKKY</a>
				<ul class="navbar-nav ms-auto flex-row">
					<li class="nav-item">
					<c:if test="${sessionScope.user == null}">
						<a role="button" class="btn btn-light me-2" id="signin" href="/dokky/signin">로그인</a>
					 </c:if>
					</li>
					<li class="nav-item">
					<c:if test="${sessionScope.user == null}">
						<a role="button" class="btn btn-primary me-2" id="signup" href="/dokky/signup">회원가입</a>
					</c:if>
					</li>
					<li class="nav-item">
					  <!-- Sign In 안 된 경우 -->
			      <c:if test="${sessionScope.user == null}">  
			      </c:if>
			      <!-- Sign In 된 경우 -->
			      <c:if test="${sessionScope.user != null}">
			        <a href="/dokky/mypage"> ${sessionScope.user.userName}님 </a> 반갑습니다.
			        <a href="/dokky/signout" id="logout">로그아웃</a>
			      </c:if>
					</li>
				</ul>
			</div>
		</nav>
