<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>

<%@ include file="layout/header.jsp" %>
<%@ include file="layout/navbar.jsp" %>
<!-- 부트스트랩 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<link href="/static/board/style.css" rel="stylesheet" type="text/css">
<!-- 부트스트랩 JS -->
<style>


.form-title:focus,
  .form-text:focus {
    border: 0.5px solid #ced4da; /* 얇은 선 테두리 */
  }


 .author-date-container {
    display: flex;
    justify-content: flex-end;
    margin-top: 10px;
  }
   .author-date-container span {
    margin-left: 200px;
  }
  

.form-title{
 width: 200%;
 border: 1px solid #ced4da; /* 얇은 선 테두리 */
 
 border-radius: 10px;
}

.btns{
  float: right;
  display: flex;
  justify-content:flex-end;
  margin-top:20px;

}

.btn{
	text-align: center;
	display: flex;
	
	justify-content: space-between;
	

}

.btn1{
	display :inline-block;
	

	    
	
}
.btn1:first-child {
	margin-right: 10px; 
}


.form-text {

     width: 500px; /* 원하는 너비로 조절 */
    height: 500px; /* 원하는 높이로 조절 */
    border: 1px solid #ced4da; /* 얇은 선 테두리 */
    border-radius: 10px; /* 테두리 둥글게 */
    padding: .375rem .75rem; /* 내부 여백 */
    
    
    
    
}

input:focus {outline:none;}


.input-group {
  margin-bottom: 20px; /* Add margin between input groups */
}

.input-group {
  margin-bottom: 20px; /* 제목 입력란과 본문 입력란 사이에 20px의 여백 추가 */
}
.toptext{
     text-align: left;
    margin-left: -600px;
  margin-bottom: 20px;
  }
 .topt{
    text-align: left;
    margin-left: -300px;
 margin-bottom: 20px;
 }

</style>
 <h3 class="toptext">함께 할 때 더 즐거운 순간</h3>
 <div class="topt">${sessionScope.user.email}님 지식공유 플랫폼 DOKKY에서 다양한 사람을 만나고 생각의 폭을 넓혀보세요.</div>
  
<h4>등록/수정</h4>

<div class="author-date-container">
  <span>작성자: ${sessionScope.user.email}</span>
  
</div>



<div class="main">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>


<body class="bg-light" data-new-gr-c-s-check-loaded="14.1167.0" data-gr-ext-installed="">
<div class="container">
  <main>
  
  
  
    
   
          <label for="title" class="form-label">제목</label>
          <div class="input-group">
            
            <input type="text" class="form-title" placeholder="제목을 입력해주세요.">
          </div>
          
          <label for="title" class="form-label">본문</label>
          <div class="input-group">
            <input type="text" class="form-text" placeholder="내용을 입력해주세요.">
          </div>
        
    
          
           
        
          <hr class="my-4">
          
         <div class="btns">
           <button class="btn btn-secondary btn1 float-right start-50 translate-middle-y" type="submit">취소</button>
           <button class="btn btn-primary btn1 float-right  start-50 translate-middle-y" type="submit">작성</button>
         </div>
        </form>
      </div>
    </div>
  </main>

</div>
    <script src="/docs/5.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
      <script src="form-validation.js"></script>
</body>


</div>
<%@ include file="layout/copyright.jsp" %>
<%@ include file="layout/footer.jsp" %>