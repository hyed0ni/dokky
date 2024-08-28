// 비밀번호 입력 input
const $originPw = $("#origin-pw");
const $newPw = $("#new-pw");
const $newPwChk = $("#new-pw-chk");

// 검증 메시지
const $originPwMsg = $("#origin-pw-msg");
const $newPwMsg = $("#new-pw-msg");
const $newPwChkMsg = $("#new-pw-chk-msg");

// 비밀번호 변경 form
const $modifyForm = $("#modify-form");

// 현재 비밀번호
$originPw.on("blur", () => {
    const isEmpty = empInput($originPw);
    $originPwMsg.toggleClass("block", isEmpty).toggleClass("none", !isEmpty);
});

// 신규 비밀번호 유효성 검사
$newPw.on("change", e => {
	
    const newPwVal = $(e.target).val();

    if (empInput($(e.target))) {
		$newPwMsg.addClass("none").removeClass("block");
        return;
    }

	// 비밀번호 4~12자, 영문/숫자/특수문자 중 2개 이상 포함
	const validCount = /[A-Za-z]/.test(newPwVal)
						+ /[0-9]/.test(newPwVal)
						+ /[^A-Za-z0-9]/.test(newPwVal);

	const passwordLength = newPwVal.length;
	const passwordCheck = passwordLength >= 4
					    && passwordLength <= 12
					    && validCount >= 2;

    $newPwMsg.toggleClass("block", !passwordCheck).toggleClass("none", passwordCheck);
	
});

// 신규 비밀번호
$newPw.on("change", pwCheck);

// 신규 비밀번호 확인
$newPwChk.on("change", pwCheck);

// 비밀번호 변경
$modifyForm.on("submit", e => {

	const newPwEmpExist = empInput($newPw) || $newPwMsg.hasClass("block");
	const newPwChkEmpExist = empInput($newPwChk) || $newPwChkMsg.hasClass("block");

	if (empInput($originPw)) {
		$originPwMsg.addClass("block").removeClass("none");
		prevForm($originPw, e);
	}
	else if (newPwEmpExist) prevForm($newPw, e);
	else if (newPwChkEmpExist) prevForm($newPwChk, e);

});

// 입력 값 존재 여부 확인 함수
function empInput($inp) {
	return $inp.val().trim().length === 0;
}

// 비밀번호 일치 여부 확인 함수
function pwCheck() {

	if (empInput($newPw) || empInput($newPwChk)) {
		$newPwChkMsg.addClass("none").removeClass("block");
		return;
	}
	
	const isValid = $newPw.val() === $newPwChk.val();
	$newPwChkMsg.toggleClass("block", !isValid).toggleClass("none", isValid);

}

// 페이지가 로드될 때 input 내용 초기화
$(window).on("pageshow", () => {
	$modifyForm[0].reset();
});

// form 태그 제출 중단 함수
function prevForm($inp, e) {
	$inp.focus();
	e.preventDefault();
}
