let originalProfileContent = null;

document.addEventListener('DOMContentLoaded', function() {
	const profileContent = document.getElementById('profile-container').innerHTML;
	if (profileContent) {
		originalProfileContent = profileContent;
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

	const profileContainer = document.getElementById('profile-container');
	profileContainer.textContent = '';

	// 활동기록 컨테이너 
	const activityRecords = document.createElement('div');
	activityRecords.id = 'activity-records';

	//제목
	const header = document.createElement('h4');
	header.classList.add('mb-3');
	header.textContent = '활동기록';
	activityRecords.appendChild(header);

	//프로필 및 컨테이너
	const myProfileBorder = document.createElement('div');
	myProfileBorder.className = 'my-profile-border';

	const uiBox = document.createElement('div');
	uiBox.className = 'ui-box';
	// 프로필 UI
	const myProfileUI = document.createElement('div');
	myProfileUI.className = 'my-profile-ui'

	const profileImage = document.createElement('img');
	profileImage.src = '/images/profileImage.jpg'
	profileImage.className = 'profile-img me-4';

	const span = document.createElement('span');
	span.textContent = '모지모지';
	myProfileUI.appendChild(profileImage);
	myProfileUI.appendChild(span);
	uiBox.appendChild(myProfileUI);
	myProfileBorder.appendChild(uiBox);

	const activityTabs = document.createElement('div');
	activityTabs.className = 'activity-tabs'

	//작성한 글
	const boardsLink = document.createElement('a');
	boardsLink.htef = '#';
	boardsLink.id = 'my-boards-button';
	boardsLink.className = 'tab-link';

	const boardsLinkSpan = document.createElement('span');
	boardsLinkSpan.textContent = '작성한 글';
	boardsLink.appendChild(boardsLinkSpan);

	//댓글
	const commentsLink = document.createElement('a');
	commentsLink.htef = '#';
	commentsLink.id = 'my-comments-button';
	commentsLink.className = 'tab-link';

	const commentsLinkSpan = document.createElement('span');
	commentsLinkSpan.textContent = '댓글';
	commentsLink.appendChild(commentsLinkSpan);


	activityTabs.appendChild(boardsLink);
	activityTabs.appendChild(commentsLink);


	//컨텐츠 영역
	const activityContent = document.createElement('div');
	activityContent.id = 'activity-content';

	myProfileBorder.appendChild(activityTabs);
	myProfileBorder.appendChild(activityContent);

	activityRecords.appendChild(myProfileBorder);
	profileContainer.appendChild(activityRecords);
	setupActivityTabs()
}

function setupActivityTabs() {
	document.querySelectorAll('.tab-link').forEach(tab => {
		tab.addEventListener('click', function(e) {
			e.preventDefault();
			const tabs = document.querySelectorAll('.tab-link');
			tabs.forEach(tab => tab.classList.remove('active'));
			tab.classList.add('active');
			if (tab.id === 'my-boards-button') {
				showBoards();
			} else if (tab.id === 'my-comments-button') {
				showComments();
			}
		})
	})

}


function showContent(apiUrl, render) {
	clearContent('activity-content');
	const userNo = document.getElementById("user-no").value;
	const compleUrl = apiUrl + userNo;
	fetch(compleUrl)
		.then(response => response.json())
		.then(resData => {
			const activityContent = document.getElementById('activity-content');
			if (resData.length > 0) {
				activityContent.appendChild(renderUserBoards(resData));
			} else {
				activityContent.textContent = resData.message;
			}
		})
		.catch(error => {
			console.error('Error fetching user posts:', error);
		});
}

function showComments() {
	showContent('/dokky/api/my-comment/', renderBoardRepeatedWithComments);
}

function showBoards() {
	showContent('/dokky/api/my-board/', renderUserBoards);
}

function createActivityCard({ boardNo, boardTitle, content, date, link, contentLength = 20 }) {
	const activityCard = document.createElement('div');
	activityCard.className = 'activity-card';

	// 제목
	const activityTitle = document.createElement('div');
	activityTitle.className = 'activity-title';

	const activityLink = document.createElement('a');
	activityLink.href = link;
	activityLink.textContent = boardTitle;

	// 등록일
	const activityDate = document.createElement('small');
	activityDate.className = 'activity-date';
	activityDate.textContent = new Date(date).toLocaleString();

	activityTitle.appendChild(activityLink);
	activityTitle.appendChild(activityDate);

	const activityContent = document.createElement('p');
	activityContent.className = 'activity-content';
	activityContent.textContent = content.length > contentLength ? `${content.substring(0, contentLength)}...` : content;

	activityCard.appendChild(activityTitle);
	activityCard.appendChild(activityContent);
	return activityCard;
}


function renderUserBoards(resData) {
	// 게시물 목록 컨테이너
	const postsContainer = document.createElement('div');
	postsContainer.className = 'activity-container';

	resData.forEach(board => {
		const postCard = createActivityCard({
			boardNo: board.boardNo,
			boardTitle: board.boardTitle,
			content: board.boardContent,
			date: board.createDt,
			link: `/dokky/detail?boardNo=${board.boardNo}`
		})
		postsContainer.appendChild(postCard);
	});

	return postsContainer;
}


function renderBoardRepeatedWithComments(resData) {

	// 게시물 목록 컨테이너
	const commentsContainer = document.createElement('div');
	commentsContainer.className = 'activity-container';

	resData.forEach(board => {
		board.comments.forEach(comment => {
			const commentCard = createActivityCard({
				boardNo: board.boardNo,
				boardTitle: board.boardTitle,
				content: comment.cmtContent,
				date: comment.createDt,
				link: `/dokky/detail?boardNo=${board.boardNo}`
			});
			commentsContainer.appendChild(commentCard);
		});
	});

	return commentsContainer;
}


function showProfile() {
	const profileContainer = document.getElementById('profile-container');
	if (originalProfileContent) {
		// innerHTML 대신에 안전한 방법을 사용합니다.
		while (profileContainer.firstChild) {
			profileContainer.removeChild(profileContainer.firstChild);
		}

		const parser = new DOMParser();
		const doc = parser.parseFromString(originalProfileContent, 'text/html');

		Array.from(doc.body.childNodes).forEach(child => {
			profileContainer.appendChild(child)
		});

		rebindEventListeners();
	} else {
		console.error('No original profile HTML is stored.');
	}
}

function clearContent(content) {
	const activityContent = document.getElementById(content);
	while (activityContent.firstChild) {
		activityContent.removeChild(activityContent.firstChild);
	}
}

function rebindEventListeners() {
	document.getElementById("update-pw-btn").addEventListener("click", () => {
		location.href = "/dokky/modify-password";
	})
}

