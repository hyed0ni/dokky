export function rebindEventListeners() {
	// 회원 탈퇴 버튼
	const profileImg = document.getElementById("profile-img");		// 프로필 이미지
	const updateImg = document.getElementById("update-img");		// 이미지 변경 텍스트
	const fileInput = document.getElementById("file-input");		// 파일 업로드 input
	const phoneInputs = document.getElementsByClassName("p-inps");	// 휴대전화 입력 input
	const phone1 = document.getElementById("phone1");
	const phone2 = document.getElementById("phone2");
	const phone3 = document.getElementById("phone3");
	const modifyForm = document.getElementById("modify-form");		// 회원 정보 수정 form
	const removeBtn = document.getElementById("remove-user-btn");	// 회원 탈퇴 버튼

	// 이미지 변경 텍스트 노출
	profileImg.addEventListener("mouseenter", () => updateImg.style.display = "block");
	profileImg.addEventListener("mouseleave", () => updateImg.style.display = "none");

	// 파일 선택 창 열기
	profileImg.addEventListener("click", () => fileInput.click());

	// 이미지 파일 업로드
	fileInput.addEventListener("change", e => {

		const selectedFile = e.target.files[0];
		if (selectedFile === undefined) return;

		const reader = new FileReader();
		reader.readAsDataURL(selectedFile);
		reader.onload = e => 
			profileImg.src = reader.result;

	});

	// 휴대전화 숫자만 입력 가능
	for (const inp of phoneInputs) {
		inp.addEventListener("input", e => {
			const inpValue = e.target.value;
			const regExp = /^[0-9]+$/;
			if (!regExp.test(inpValue)) e.target.value = inpValue.slice(0, -1);
		});
	}

	// 회원 정보 수정
	modifyForm.addEventListener("submit", () => {

		// 휴대전화 input 하나의 문자열로 합치기
		const userMobile = `${phone1.value}${phone2.value}${phone3.value}`;
		document.querySelector("#userMobile").value = userMobile;
		
	});

	// 비밀번호 변경 페이지 이동
	document.getElementById("update-pw-btn").addEventListener("click", () => 
		location.href = "/dokky/modify-password");

	// 회원 탈퇴 버튼 활성화
	document.getElementById("withdrawal").addEventListener("change", e => {
		if (e.target.checked) removeBtn.classList.remove("disabled");
		else removeBtn.classList.add("disabled");
	});

	// 페이지가 로드될 때 계정 삭제 초기화
	window.addEventListener("pageshow", () => {
		document.getElementById("withdrawal").checked = false;
		removeBtn.classList.add("disabled");
	});

	// 회원 탈퇴 페이지 이동
	removeBtn.addEventListener("click", () => 
		location.href = "/dokky/remove-user");
	
}