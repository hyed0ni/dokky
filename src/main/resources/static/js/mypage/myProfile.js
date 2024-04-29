export function rebindEventListeners() {

	const originName = document.getElementById("user-name").value;		// 현재 닉네임
	
	const profileImg = document.getElementById("profile-img");			// 프로필 이미지
	const fileInput = document.getElementById("file-input");			// 파일 업로드 input
	const userName = document.getElementById("user-name");				// 닉네임 입력 input
	const userMobile = document.getElementById("user-mobile");			// 휴대전화 입력 input

	const userNameMsg1 = document.getElementById("user-name-msg-1");	// 닉네임 검증 메시지: 형식
	const userNameMsg2 = document.getElementById("user-name-msg-2");	// 닉네임 검증 메시지: 중복
	const userMobileMsg = document.getElementById("user-mobile-msg");	// 휴대전화 검증 메시지

	const modifyForm = document.getElementById("modify-form");			// 회원 정보 수정 form
	const removeBtn = document.getElementById("remove-user-btn");		// 회원 탈퇴 버튼

	// 파일 선택 창 열기
	profileImg.addEventListener("click", () => fileInput.click());

	// 이미지 파일 업로드
	fileInput.addEventListener("change", e => {

		const selectedFile = e.target.files[0];
		if (selectedFile === undefined) return;

		const reader = new FileReader();
		reader.readAsDataURL(selectedFile);
		reader.onload = () => 
			profileImg.src = reader.result;

	});

	// 닉네임 검증
	userName.addEventListener("blur", e => {

		const regExp = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,8}$/;

		// 미입력 상태
		if (empInput(e.target)) {
			userNameMsg1.className = "none";
			userNameMsg2.className = "none";
        	return;
		}

		// 형식 체크
		if (!regExp.test(e.target.value)) {
			userNameMsg1.className = "block";
			userNameMsg2.className = "none";
			return;
		}

		userNameMsg1.className = "none";

		// 현재 닉네임
		if (e.target.value === originName) {
			userNameMsg2.className = "none";
			return;
		}

		// 중복 검사
		fetch ("/dokky/checkNickname", {
			method: "POST",
			headers: {'Content-Type': 'application/json'}, 
			body: e.target.value
		})
		.then (response => response.text())
		.then (result => {
			if (result == 0) userNameMsg2.className = "none";
			else userNameMsg2.className = "block";
		});

	});
	
	// 휴대전화 숫자만 입력 가능
	userMobile.addEventListener("input", e => {
		const inpValue = e.target.value;
		const regExp = /^[0-9]+$/;
		if (!regExp.test(inpValue)) e.target.value = inpValue.slice(0, -1);
	});

	// 휴대전화 검증
	userMobile.addEventListener("blur", e => {

		if (empInput(e.target)) {
			userMobileMsg.className = "none";
        	return;
		} 
		
		const isValid = e.target.value.trim().length >= 11;
		userMobileMsg.className = isValid ? "none" : "block";

	});

	// 회원 정보 수정
	modifyForm.addEventListener("submit", e => {

		const nameEmpExist = empInput(userName)
		 || userNameMsg1.className === "block"
		 || userNameMsg2.className === "block";
		const mobileExist = userMobileMsg.className === "block";

		if (nameEmpExist) prevForm(userName, e);
		if (mobileExist) prevForm(userMobile, e);

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
	
	// 입력 값 존재 여부 확인 함수
	function empInput(inp) {
		return inp.value.trim().length === 0;
	}

	// form 태그 제출 중단 함수
	function prevForm(inp, e) {
		inp.focus();
		e.preventDefault();
	}

}