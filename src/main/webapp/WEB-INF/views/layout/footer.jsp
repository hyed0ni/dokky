<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		<script>
	    function logoutClicked() {
	        // 로그아웃 버튼 클릭 시 페이지를 새로고침하여 화면을 초기화
	        window.location.reload();
	    }

	    // 페이지 쇼 이벤트 핸들러
	    window.addEventListener('pageshow', function(event) {
	        // 로그인 상태가 풀린 경우에만 페이지를 새로고침
	        if (event.persisted) {
	            window.location.reload();
	        }
	    });
		</script>

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>