// 비밀번호 입력 input
const originPw = document.getElementById("origin-pw");
const newPw = document.getElementById("new-pw");
const newPwChk = document.getElementById("new-pw-chk");

// 검증 메시지
const originPwMsg = document.getElementById("origin-pw-msg");
const newPwMsg = document.getElementById("new-pw-msg");
const newPwChkMsg = document.getElementById("new-pw-chk-msg");

// 비밀번호 변경 form
const modifyForm = document.getElementById("modify-form");

// 현재 비밀번호
originPw.addEventListener("blur", () => {
	originPwMsg.className = empInput(originPw) ? "block" : "none";
});

// 신규 비밀번호 유효성 검사
newPw.addEventListener("change", (e) => {

    if (empInput(e.target) === 0) {
        newPwMsg.className = "none";
        return;
    }

	// 비밀번호 4~12자, 영문/숫자/특수문자 중 2개 이상 포함
	const validCount = /[A-Za-z]/.test(e.target.value)
						+ /[0-9]/.test(e.target.value)
						+ /[^A-Za-z0-9]/.test(e.target.value);

	const passwordLength = e.target.value.length;
	passwordCheck = passwordLength >= 4
					&& passwordLength <= 12
					&& validCount >= 2;
	
	newPwMsg.className = passwordCheck ? "none" : "block";
	
});

// 신규 비밀번호
newPw.addEventListener("change", pwCheck);

// 신규 비밀번호 확인
newPwChk.addEventListener("change", () => {
    if (empInput(newPw)) newPwChkMsg.className = "block";
	else pwCheck();
});

// 비밀번호 일치 여부 확인 함수
function pwCheck() {

	if (empInput(newPw) || empInput(newPwChk)) {
		newPwChkMsg.className = "none";
		return;
	}
	
	const isValid = newPw.value === newPwChk.value;
	newPwChkMsg.className = isValid ? "none" : "block";

}

// 입력 값 존재 여부 확인 함수
function empInput(inp) {
	return inp.value.trim().length === 0;
}

// 비밀번호 변경
modifyForm.addEventListener("submit", (e) => {

	// 현재 비밀번호 미입력
	if (empInput(originPw)) {
		originPwMsg.className = "block";
		originPw.focus();
		e.preventDefault();
	}

	const empNewPw = empInput(newPw) || empInput(newPwChk);
	const existMsg = document.querySelectorAll("#modify-form .block").length !== 0;

	if (empNewPw || existMsg) e.preventDefault();

});