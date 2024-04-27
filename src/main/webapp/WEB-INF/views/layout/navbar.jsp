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
						<div class="nav-profile-ui">
						<c:if test="${sessionScope.user != null}">
					  	  <c:if test="${empty sessionScope.user.userImg}">
							<img src="/images/dokky_profile.png" id="nav-profile-img" class="nav-img">
						  </c:if>
						</c:if>
					      <!-- Sign In 된 경우 -->
					      <c:if test="${sessionScope.user != null}">
					      <c:if test="${!empty sessionScope.user.userImg}">
					        <a><img src="${sessionScope.user.userUploadPath}${sessionScope.user.userImg}" class="nav-img"></a>
				          </c:if>
				          	<div class="nav-welcome">
						        <span class="nav-username">${sessionScope.user.userName}</span>님
						        <span>반갑습니다.</span>
					        </div>
					       </c:if>
					         <div class="my-menu-content">
		       					 <ul class="my-menu-list">
		        	    			 <li class="my-menu-item" id="my-profile">
		        	    			 <c:if test="${empty sessionScope.user.userImg}">
										<img src="/images/dokky_profile.png" id="nav-profile-img" class="nav-img">
						  			 </c:if>
						  		  	 <c:if test="${!empty sessionScope.user.userImg}">
		         						<img src="${sessionScope.user.userUploadPath}${sessionScope.user.userImg}" class="nav-img">
						  			 </c:if>
		         						<div class="my-menu-info">
			         						<p>${sessionScope.user.userName}</p>
			            					<a href="/dokky/mypage">내 프로필</a>
		            					</div>
		          					</li>
		          					<hr>
		          					<li class="my-menu-item">
		           						<a class="my-menu-logout" href="/dokky/signout">
		           							<i class="fa-solid fa-arrow-right-from-bracket"></i>
		           							<span>로그아웃</span>
		           						</a>
		          				    </li>
		        				</ul>
		     				 </div>
     				 	</div>
					</li>
				</ul>
			</div>
		</nav>
