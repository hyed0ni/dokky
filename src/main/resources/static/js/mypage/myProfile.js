// 서버에서 사용자가 작성한 글을 가져와서 화면에 표시하는 함수
function fetchUserPosts() {
    fetch('api/myBoard') // 작성한 글을 가져올 API 엔드포인트로 요청을 보냅니다.
        .then(response => response.json()) // 응답을 JSON 형식으로 파싱합니다.
        .then(resData => {
		if(resData.message) {
			console.log(resData.message);
			} else {
			renderUserPosts(resData)	
			}
		})
        .catch(error => console.error('Error fetching user boards:', error)); // 오류가 발생한 경우 콘솔에 오류를 출력합니다.
}

// 서버에서 가져온 데이터를 HTML 형식으로 변환하여 삽입하는 함수
function renderUserPosts(resData) {
    const userPostsContainer = document.getElementById('userPosts');
    userPostsContainer.innerHTML = ''; // 기존 내용을 지웁니다.

    resData.forEach(item => {
        const boards = item.boardDto;
        boards.forEach(board => {
            // 각각의 작성한 글 항목을 생성합니다.
            const postElement = `
                <div class="board">
                    <div class="userName">${board.user.userName}</div>
                    <div class="title">${board.boardTitle}</div>
                    <div class="hit">${board.boardHit}</div>
                    <div class="createDt">${board.createDt}</div>
                </div>
            `;
            // 작성한 글 항목을 목록에 추가합니다.
            userPostsContainer.innerHTML += postElement;
        });
    });
}
// 작성한 글 버튼 클릭 시 작성한 글을 가져와서 화면에 표시합니다.
const myPostsButton = document.getElementById('myPostsButton');
myPostsButton.addEventListener('click', fetchUserPosts);

// 페이지 로드 시 작성한 글을 가져와서 표시합니다.
window.addEventListener('load', fetchUserPosts);
