let originalProfileHtml = null;

document.addEventListener('DOMContentLoaded', function() {
    const profileContainer = document.getElementById('profile-container');
    if (profileContainer) {
        originalProfileHtml = profileContainer.innerHTML;
    }

    // '활동 기록' 링크에 클릭 이벤트 리스너를 추가합니다.
    document.getElementById('my-activity').addEventListener('click', function(event) {
        event.preventDefault();
        removeActiveClass();
        this.classList.add('active');
        showActivityRecords();
    });

    // '프로필' 링크에 클릭 이벤트 리스너를 추가합니다.
    const profileLink = document.getElementById('profile-link');
    profileLink.addEventListener('click', function(event) {
        event.preventDefault();
        removeActiveClass();
        this.classList.add('active');
        showProfile();
    });
});

function removeActiveClass() {
    document.querySelectorAll('.nav-link').forEach(function(link) {
        link.classList.remove('active');
    });
}

function showActivityRecords() {
    const activityHtml = `
        <div id="activity-records">
            <h4 class="mb-3">활동기록</h4>
            <div class="my-profile-border">
                <div class="my-profile-ui">
                    <img src=".././images/profileImage.jpg" class="profile-img me-4"/>
                    <span>모지모지</span>
                </div>
                <div class="activity-tabs">
                    <a href="#" id="my-posts-button" class="tab-link">
	                    <span>작성한 글</span>
                    </a>
                    <a href="#" id="my-comments-button" class="tab-link">
                    	<span>댓글</span>
                    </a>
                </div>
            </div>
            <div id="activity-content">
                <!-- 탭에 따라 변하는 내용을 여기에 렌더링 -->
            </div>
        </div>
    `;

    const profileContainer = document.getElementById('profile-container');
    profileContainer.innerHTML = activityHtml;
    setupActivityTabs();
}

function setupActivityTabs() {
    document.querySelector('.activity-tabs').addEventListener('click', function(event) {
        event.preventDefault();
        const target = event.target.closest('.tab-link');
        if (target) {
            document.querySelectorAll('.tab-link').forEach(tab => {
                tab.classList.remove('active');
            });
            target.classList.add('active');

            switch (target.id) {
                case 'my-posts-button':
                    showPosts();
                    break;
                case 'my-comments-button':
                    showComments();
                    break;
            }
        }
    });
}

function showPosts() {
    fetch('api/myBoard')
        .then(response => response.json())
        .then(resData => {
            const activityContent = document.getElementById('activity-content');
            activityContent.innerHTML = renderUserPosts(resData);
            console.log(resData);
        })
        .catch(error => {
            console.error('Error fetching user posts:', error);
        });
}

function renderUserPosts(resData) {
    let postsHtml = `<table class="table">
    					<thead>
    						<tr>
    							<th>게시글번호</th>
    							<th>제목</th>
    							<th>조회수</th>
    							<th>등록일</th>
							</tr>
						</thead>
							<tbody>`;
    resData.forEach(item => {
        item.boardDto.forEach(board => {
            postsHtml += `<tr>
							<td>${board.boardNo}</td>
							<td><a href="/dokky/detail?boardNo=${board.boardNo}">${board.boardTitle}</a></td>
							<td>${board.boardHit}</td>
							<td>${new Date(board.createDt).toLocaleString()}</td>
						</tr>`;
        });
    });
    postsHtml += '</tbody></table>';
    return postsHtml;
}

function showComments() {
    // 댓글단 글을 불러와서 'activity-content'에 렌더링하는 로직 구현
    console.log('Show comments is called.');
}

function showProfile() {
    const profileContainer = document.getElementById('profile-container');
    if (originalProfileHtml) {
        profileContainer.innerHTML = originalProfileHtml;
    } else {
        console.error('No original profile HTML is stored.');
    }
}
