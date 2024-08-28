export function rebindEventListeners() {

    const originName = $("#user-name").val();		// 현재 닉네임

    const $profileImg = $("#profile-img");			// 프로필 이미지
    const $fileInput = $("#file-input");			// 파일 업로드 input
    const $userName = $("#user-name");				// 닉네임 입력 input
    const $userMobile = $("#user-mobile");			// 휴대전화 입력 input

    const $userNameMsg1 = $("#user-name-msg-1");	// 닉네임 검증 메시지: 형식
    const $userNameMsg2 = $("#user-name-msg-2");	// 닉네임 검증 메시지: 중복
    const $userMobileMsg = $("#user-mobile-msg");	// 휴대전화 검증 메시지

    const $modifyForm = $("#modify-form");			// 회원 정보 수정 form
    const $removeBtn = $("#remove-user-btn");		// 회원 탈퇴 버튼

    // 파일 선택 창 열기
    $profileImg.on("click", () => $fileInput.click());

    // 이미지 파일 업로드
    $fileInput.on("change", e => {

        const selectedFile = e.target.files[0];
        if (!selectedFile) return;

        const reader = new FileReader();
        reader.readAsDataURL(selectedFile);
        reader.onload = () => $profileImg.attr("src", reader.result);

    });

    // 닉네임 검증
    $userName.on("blur", e => {

        const regExp = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,8}$/;

        // 미입력 상태
        if (empInput($(e.target))) {
            $userNameMsg1.addClass("none").removeClass("block");
            $userNameMsg2.addClass("none").removeClass("block");
            return;
        }

        // 형식 체크
        if (!regExp.test($(e.target).val())) {
            $userNameMsg1.addClass("block").removeClass("none");
            $userNameMsg2.addClass("none").removeClass("block");
            return;
        }

        $userNameMsg1.addClass("none").removeClass("block");

        // 현재 닉네임
        if ($(e.target).val() === originName) {
            $userNameMsg2.addClass("none").removeClass("block");
            return;
        }

        // 중복 검사
        $.ajax({
	        url: "/dokky/checkNickname", 
	        method: "POST",
	        contentType: "application/json", 
	        data: $(e.target).val(), 
	        success: result => {
	            if (result == 0) $userNameMsg2.addClass("none").removeClass("block");
	            else $userNameMsg2.addClass("block").removeClass("none");
	        }
    	});

    });

    // 휴대전화 숫자만 입력 가능
    $userMobile.on("input", e => {
        const inpValue = $(e.target).val();
        const regExp = /^[0-9]+$/;
        if (!regExp.test(inpValue)) $(e.target).val(inpValue.slice(0, -1));
    });

    // 휴대전화 검증
    $userMobile.on("blur", e => {

        if (empInput($(e.target))) {
            $userMobileMsg.addClass("none").removeClass("block");
            return;
        } 
        
        const isValid = $(e.target).val().trim().length >= 11;
        $userMobileMsg.toggleClass("block", !isValid).toggleClass("none", isValid);

    });

    // 회원 정보 수정
    $modifyForm.on("submit", e => {

        const nameEmpExist = empInput($userName)
                            || $userNameMsg1.hasClass("block")
                            || $userNameMsg2.hasClass("block");
        const mobileExist = $userMobileMsg.hasClass("block");

        if (nameEmpExist) prevForm($userName, e);
        if (mobileExist) prevForm($userMobile, e);

    });

    // 비밀번호 변경 페이지 이동
    $("#update-pw-btn").on("click", () => location.href = "/dokky/modify-password");

    // 회원 탈퇴 버튼 활성화
    $("#withdrawal").on("change", e => {
        $removeBtn.toggleClass("disabled", !e.target.checked);
    });

    // 페이지가 로드될 때 계정 삭제 초기화
    $(window).on("pageshow", () => {
        $("#withdrawal").prop("checked", false);
        $removeBtn.addClass("disabled");
    });

    // 회원 탈퇴 페이지 이동
    $removeBtn.on("click", () => location.href = "/dokky/remove-user");

    // 입력 값 존재 여부 확인 함수
    function empInput($inp) {
        return $inp.val().trim().length === 0;
    }

    // form 태그 제출 중단 함수
    function prevForm($inp, e) {
        $inp.focus();
        e.preventDefault();
    }

}
