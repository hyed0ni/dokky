import { rebindEventListeners } from "./myProfile.js";


let originalProfileContent = null;

document.addEventListener("DOMContentLoaded", function() {
	const profileContainer = document.getElementById("profile-container");
	originalProfileContent = document.createElement("div");
	Array.from(profileContainer.children).forEach(child => {
		originalProfileContent.appendChild(child.cloneNode(true));
	});
	
	document.getElementById("my-activity").addEventListener("click", function(event) {
		event.preventDefault();
		removeActiveClass();
		this.classList.add("active");
		showActivityRecords();
	});

	const profileLink = document.getElementById("profile-link");
	profileLink.addEventListener("click", function(event) {
		event.preventDefault();
		removeActiveClass();
		this.classList.add("active");
		showProfile();
	});
	
	    rebindEventListeners();
});

function formatDate(dateString) {
	const date = new Date(dateString);
	return date.toLocaleString("ko-KR", {
		year: "numeric",
		month: "2-digit",
		day: "2-digit",
		hour: "2-digit",
		minute: "2-digit",
		hour12: false
	});
};



function removeActiveClass() {
	document.querySelectorAll(".nav-link").forEach(function(link) {
		link.classList.remove("active");
	});
}

function showActivityRecords() {
	const nickname = document.getElementById("user-name").value;
	const profileImg = document.getElementById("profile-img");
	const profileImgSrc = profileImg.getAttribute('data-src'); 
	
	clearContent("profile-container");
	const profileContainer = document.getElementById("profile-container");
	profileContainer.textContent = "";

	// 활동기록 컨테이너 
	const activityRecords = document.createElement("div");
	activityRecords.id = "activity-records";

	//제목
	const header = document.createElement("h4");
	header.classList.add("mb-3");
	header.textContent = "활동 기록";
	activityRecords.appendChild(header);

	//프로필 및 컨테이너
	const myProfileBorder = document.createElement("div");
	myProfileBorder.className = "my-profile-border mb-4";


	const uiBox = document.createElement("div");
	uiBox.className = "ui-box";
	// 프로필 UI
	const myProfileUI = document.createElement("div");
	myProfileUI.className = "my-profile-ui";

	const profileImage = document.createElement("img");
	profileImage.src = profileImgSrc;
	profileImage.className = "profile-img me-4";

	const span = document.createElement("span");
	span.textContent = nickname;
	myProfileUI.appendChild(profileImage);
	myProfileUI.appendChild(span);
	uiBox.appendChild(myProfileUI);
	myProfileBorder.appendChild(uiBox);

	const activityTabs = document.createElement("div");
	activityTabs.className = "activity-tabs"

	//작성한 글
	const boardsLink = document.createElement("a");
	boardsLink.href = "#";
	boardsLink.id = "my-boards-button";
	boardsLink.className = "tab-link";

	const boardsLinkSpan = document.createElement("span");
	boardsLinkSpan.textContent = "작성한 글";
	boardsLink.appendChild(boardsLinkSpan);

	//댓글
	const commentsLink = document.createElement("a");
	commentsLink.href = "#";
	commentsLink.id = "my-comments-button";
	commentsLink.className = "tab-link";

	const commentsLinkSpan = document.createElement("span");
	commentsLinkSpan.textContent = "댓글";
	commentsLink.appendChild(commentsLinkSpan);


	activityTabs.appendChild(boardsLink);
	activityTabs.appendChild(commentsLink);


	//컨텐츠 영역
	const activityContent = document.createElement("div");
	activityContent.id = "activity-content";

	myProfileBorder.appendChild(activityTabs);
	myProfileBorder.appendChild(activityContent);

	activityRecords.appendChild(myProfileBorder);
	profileContainer.appendChild(activityRecords);
	setupActivityTabs();
}

function setupActivityTabs() {
	document.querySelectorAll(".tab-link").forEach(tab => {
		tab.addEventListener("click", function(e) {
			e.preventDefault();
			const tabs = document.querySelectorAll(".tab-link");
			tabs.forEach(tab => tab.classList.remove("active"));
			tab.classList.add("active");
			if (tab.id === "my-boards-button") {
				showBoards();
			} else if (tab.id === "my-comments-button") {
				showComments();
			};
		});
	});
}

function displayMessage(message, container) {
	clearContent(container.id);
	
	const messageElement = document.createElement("div");
	messageElement.className = "no-write";
	messageElement.textContent = message;
	
	/*
	const linkElement = document.createElement("a");
	linkElement.href = linkHref;
	linkElement.textContent = linkText;
	linkElement.className = "add-link"
	messageElement.appendChild(linkElement);
	*/
	container.appendChild(messageElement);	
}


function showContent(apiUrl, render, loadPage) {
	window.scrollTo(0, 0);
	clearContent("activity-content");
		fetch(apiUrl)
		.then(response => response.json())
		.then(resData => {
		const activityContent = document.getElementById("activity-content");
			// window.currentUserName = resData.items.userName;
			if (resData.items && resData.items.length > 0) {
				activityContent.appendChild(render(resData.items));
				if (resData.totalPage > 1) {
					createPaginationContainer();
					renderPagination(resData.currentPage, resData.totalPage, loadPage);
				}
			} else {
				displayMessage(resData.message, activityContent);
			};
		})
		.catch(error => {
			console.error("Error fetching user posts:", error);
		});
}

function showComments(page = 1) {
	const userNo = document.getElementById("user-no").value;
	showContent(`/dokky/api/my-comment/${userNo}?page=${page}`, renderBoardRepeatedWithComments, (newPage) => showComments(newPage));
}
function showBoards(page = 1) {
	const userNo = document.getElementById("user-no").value;
	showContent(`/dokky/api/my-board/${userNo}?page=${page}`, renderUserBoards, (newPage) => showBoards(newPage));

}

function createActivityCard({ boardNo, boardTitle, content, date, link, contentLength = 20, type }) {
	const activityCard = document.createElement("div");
	activityCard.className = "activity-card";

	// 제목
	const activityTitle = document.createElement("div");
	activityTitle.className = "activity-title";

	const activityLink = document.createElement("a");
	activityLink.href = link;
	activityLink.textContent = boardTitle;

	// 등록일
	const activityDate = document.createElement("small");
	activityDate.className = "activity-date";
	activityDate.textContent = formatDate(date);


	activityTitle.appendChild(activityLink);
	

	const activityText = document.createElement("p");
	activityText.className = "activity-text";
	activityText.textContent = content.length > contentLength ? `${content.substring(0, contentLength)}...` : content;

	if(type === 'comment') {
		activityCard.appendChild(activityTitle);
		activityText.appendChild(activityDate); 
		activityCard.appendChild(activityText);
	} else {
		activityTitle.appendChild(activityDate);
		activityCard.appendChild(activityTitle);
		activityCard.appendChild(activityText);
	}
	return activityCard;
}

function createPaginationContainer() {
	const activityContent = document.getElementById("activity-content");
	const paginationContainer = document.createElement("div");
	paginationContainer.id = "pagination-container";
	paginationContainer.className = "pagination";
	activityContent.appendChild(paginationContainer);
}

function clearPaginationContainer(paginationContainer) {
	while (paginationContainer.firstChild) {
		paginationContainer.removeChild(paginationContainer.firstChild);
	}
}

function renderPagination(currentPage, totalPage, loadPage) {
	const paginationContainer = document.getElementById("pagination-container");
	
	clearPaginationContainer(paginationContainer);

    const pageGroupSize = 10;
    let currentGroup = Math.floor((currentPage - 1) / pageGroupSize);
    let startPage = currentGroup * pageGroupSize + 1;
    let endPage = Math.min(startPage + pageGroupSize - 1, totalPage);
	let PrevGroupPage = Math.max(currentPage - 10, 1);
	let NextGroupPage = Math.min(currentPage + 10, totalPage);

	// 10페이지 이전 버튼	
	const prevGroupLink = document.createElement("a");
	prevGroupLink.href = "#";
	prevGroupLink.textContent = "<<";
	prevGroupLink.className = "page-link";
	if(currentPage > startPage) {
	console.log(startPage);
		prevGroupLink.addEventListener("click", (e) => {
			e.preventDefault();
			loadPage(PrevGroupPage);
		});
	} else {
		prevGroupLink.classList.add("disabled");
	};
	
	paginationContainer.appendChild(prevGroupLink);
	
	// 이전 페이지 버튼
	const prevLink = document.createElement("a");
	prevLink.href = "#";
	prevLink.textContent = "<";
	prevLink.className = "page-link";
	if (currentPage > 1) {
		prevLink.addEventListener("click", (e) => {
			e.preventDefault();
			loadPage(currentPage - 1);
		});
	} else {
		prevLink.classList.add("disabled");
	}
	paginationContainer.appendChild(prevLink);


	for (let i = startPage; i <= endPage; i++) {
		const pageLink = document.createElement("a");
		pageLink.href = "#";
		pageLink.textContent = i;
		pageLink.className = "page-link";
		if (i === currentPage) {
			pageLink.classList.add("active");
		}
		pageLink.addEventListener("click", (e) => {
			e.preventDefault();
			loadPage(i)// 페이지 변경 시 콘텐츠 재로드
		});

		paginationContainer.appendChild(pageLink); // 페이지 링크를 컨테이너에 추가
	}

	// 다음 페이지 버튼
	const nextLink = document.createElement("a");
	nextLink.href = "#";
	nextLink.textContent = ">";
	nextLink.className = "page-link";
	if (currentPage < totalPage) {
		nextLink.addEventListener("click", (e) => {
			e.preventDefault();
			loadPage(currentPage + 1);
		});
	} else {
		nextLink.classList.add("disabled");
	}
	paginationContainer.appendChild(nextLink);
	
	// 다음 10페이지 이동 버튼
	const nextGroupLink = document.createElement("a");
	nextGroupLink.href = "#";
	nextGroupLink.textContent = ">>";
	nextGroupLink.className = "page-link";
	if(currentPage < totalPage) {
		nextGroupLink.addEventListener("click", (e) => {
			e.preventDefault();
			loadPage(NextGroupPage);
		});
	} else {
		nextGroupLink.classList.add("disabled");
	}
	
	paginationContainer.appendChild(nextGroupLink);

}


function renderUserBoards(resData) {
	// 게시물 목록 컨테이너
	const postsContainer = document.createElement("div");
	postsContainer.className = "activity-container";

	resData.forEach(board => {
		const postCard = createActivityCard({
			boardNo: board.boardNo,
			boardTitle: board.boardTitle,
			content: board.boardContent,
			date: board.boardCreateDt,
			link: `/dokky/detail?boardNo=${board.boardNo}`,
			type: "post"
		});
		postsContainer.appendChild(postCard);
	});

	return postsContainer;
}


function renderBoardRepeatedWithComments(resData) {

	// 게시물 목록 컨테이너
	const commentsContainer = document.createElement("div");
	commentsContainer.className = "activity-container";

	resData.forEach(board => {
		board.comments.forEach(comment => {
			const commentCard = createActivityCard({
				boardNo: board.boardNo,
				boardTitle: board.boardTitle,
				content: comment.commentContent,
				date: comment.cmtCreateDt,
				link: `/dokky/detail?boardNo=${board.boardNo}`,
				type: "comment"
			});
			commentsContainer.appendChild(commentCard);
		});
	});

	return commentsContainer;
}


function showProfile() {
	const profileContainer = document.getElementById("profile-container");
	while (profileContainer.firstChild) {
		profileContainer.removeChild(profileContainer.firstChild);
	};

	Array.from(originalProfileContent.children).forEach(child => {
		profileContainer.appendChild(child.cloneNode(true));
	});
	rebindEventListeners();
}

function clearContent(content) {
	const activityContent = document.getElementById(content);
	while (activityContent.firstChild) {
		activityContent.removeChild(activityContent.firstChild);
	};
}
