<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>

<%@ include file="layout/header.jsp" %>
    <div class="login-container">
        <h1>DOKKY 아이디 로그인</h1>
        <form class="login-form">
            <label for="username">아이디</label>
            <input type="text" id="username" name="username" placeholder="아이디">

            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" placeholder="비밀번호">

            <button type="submit">로그인</button>
            <div class="signup-link">
                아직 회원이 아니신가요? <a href="#">회원가입</a>
            </div>
        </form>
    </div>

<%@ include file="layout/footer.jsp" %>