<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>

		<nav class="navbar navbar-light bg-light fixed-top">
			<div class="container-fluid">
				<a class="navbar-brand" href="/">DOKKY</a>
				<ul class="navbar-nav ms-auto flex-row">
					<li>
						<a role="button" class="btn btn-secondary me-2" href="/user/signin">로그인</a>
					</li>
					<li class="nav-item">
						<a role="button" class="btn btn-primary me-2" href="#">회원가입</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">모지모지</a>
					</li>
				</ul>
			</div>
		</nav>