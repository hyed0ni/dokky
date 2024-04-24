
export function rebindEventListeners() {
	// 회원 탈퇴 버튼
	const removeBtn = document.getElementById("remove-user-btn");
	const updateBtn = document.getElementById("update-pw-btn");

	// 비밀번호 변경 페이지 이동
	updateBtn.addEventListener("click", () => {
		console.log("변경 버튼 누름")
		location.href = "/dokky/modify-password";
	});
	
	// 체크박스 체크 시 회원 탈퇴 버튼 활성화
	document.getElementById("withdrawal").addEventListener("change", (e) => {
		if (e.target.checked) removeBtn.classList.remove("disabled");
		else removeBtn.classList.add("disabled");
	});
	
	// 페이지가 로드될 때 체크 해제
	window.addEventListener("pageshow", e => {
		document.getElementById("withdrawal").checked = false;
	});
	
	// 회원 탈퇴 페이지 이동
	removeBtn.addEventListener("click", () => {
		location.href = "/dokky/remove-user";
	});
}