<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../layout/header.jsp" %>
<link href="/css/board/list.css" rel="stylesheet" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
</head>
<body>
<%@ include file="../layout/navbar.jsp"%>
<div class="main">
    <div class="box"></div>
    <div class="table-container">
        <div class="row mb-3">
            <div class="col">
                <div class="search-container">
                    <form action="/dokky/main" method="GET" name="searchform" onsubmit="return searchformaction(this,1)">
                        <input type="hidden" name="page" value="1" />
                        <input type="text" placeholder="검색어를 입력해주세요." name="search" value="${param.search}">
                        <button type="submit" class="search-btn">
                            <i class="fa fa-search"></i>
                        </button>
                        <a role="button" class="btn btn-primary me-2 " id="add" href="/dokky/add">작성하기</a>
                    </form>
                </div>
            </div>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col" class="align-middle">작성자</th>
                    <th scope="col" class="align-middle">제목</th>
                    <th scope="col" class="align-middle">내용</th>
                    <th scope="col" class="align-middle">댓글수</th>
                    <th scope="col" class="align-middle">조회수</th>
                    <th scope="col" class="align-middle">등록일</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${boardList.size() > 0}">
                        <c:forEach var="board" items="${boardList}">
                            <tr>
                                <td class="align-middle">
                                    <c:if test="${board.user.userImg == null}">
                                        <img src="/images/dokky_profile.png" alt="DOKKY 로고" height="30" class="nav-img">
                                        ${board.user.userName}
                                    </c:if>
                                    <c:if test="${board.user.userImg != null}">
                                        <img src="${board.user.userUploadPath}${board.user.userImg}" class="nav-img">
                                        ${board.user.userName}
                                    </c:if>
                                </td>
                                <td class="tab align-middle"><a href="/dokky/detail?boardNo=${board.boardNo}">${board.boardTitle}</a></td>
                                <td class="tab align-middle">${board.boardContent}</td>
                                <td class="tab align-middle"><i class="fa-regular fa-comment"></i>&nbsp;${board.cmtNo}</td>
                                <td class="tab align-middle"><i class="fa-regular fa-eye"></i>&nbsp;${board.boardHit}</td>
                                <td class="tab align-middle"><fmt:formatDate value="${board.boardCreateDt}" pattern="yyyy-MM-dd HH:mm" /></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6">검색된 게시물이 없습니다</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        
        <nav aria-label="Page navigation example">
				<ul class="pagination">
					<li class="page-item">
					<a class="page-link ${currentPage == 1 ? 'disabled' : ''}" href="javascript:searchformaction(document.searchform, ${prevPage})" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
					<c:forEach begin="${startPage}" end="${endPage}" var="p">
					<li class="page-item">
						<a class="page-link ${currentPage == p ? 'active' : ''}" href="javascript:searchformaction(document.searchform, ${p})">${p}</a>
					</li>
					</c:forEach>
					<li class="page-item">
					<a class="page-link ${currentPage == maxPage ? 'disabled' : ''}" href="javascript:searchformaction(document.searchform, ${nextPage})" aria-label="Next"> 
					<span aria-hidden="true">&raquo;</span></a></li>
				</ul>
			</nav>
		</div>
	</div>

<%@ include file="../layout/copyright.jsp" %>
<%@ include file="../layout/footer.jsp" %>
<script type="text/javascript">

// 전역 변수 
var boardListSize = ${boardList.size()};

// 글자수 제한 및 ... 표시 함수
function limitText(text, limit) {
    return text.length > limit ? text.substring(0, limit) + '...' : text;
}

// 게시물 제목과 내용의 길이 제한 적용
$(document).ready(function() {
    $('.table tbody tr').each(function() {
        var contentCell = $(this).find('td:eq(2)'); // 내용 열
        var maxLength = 20; // 최대 길이 설정

        var contentText = contentCell.text();

        contentCell.text(limitText(contentText, maxLength));
    });
});

// 페이징 검색 게시물 없을 시 hide 처리
if(boardListSize != 0){
    let page = $('.pagination').show();    
}else{
    let page = $('.pagination').hide();    
}

// 검색 기능 처리
function searchformaction(f, p) {
    if (f.search == '') {
        alert("검색어를 입력하세요");
        f.search.focus();
        return false;
    }
        f.page.value = p;
        f.submit();
    }
</script>