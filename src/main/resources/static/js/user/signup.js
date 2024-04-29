// 이메일 유효 여부 저장 변수
const state = {
  emailCheck: false
};

// 닉네임 유효 여부 저장 변수
let nicknameCheck = false;

// DOM 요소 가져오기
const inpEmail = document.getElementById("inp-email");
const inpPw = document.getElementById("inp-pw");
const inpName = document.getElementById("inp-name");
const inpMobile = document.getElementById("inp-mobile");
const msgEmail = document.getElementById("msg-email");
const msgPw = document.getElementById("msg-pw");
const msgName = document.getElementById("msg-name");
const msgMobile = document.getElementById("msg-mobile");
const inpCode = document.getElementById("inp-code");
const btnVerifyCode = document.getElementById("btn-verify-code");

// 모든 입력값 유효성 검사
async function validateForm() {
    try {
        const emailResult = await fnCheckEmail();
        if (!emailResult.isValid) {
            emailResult.element.focus();
            return false;
        }
        
        const passwordResult = fnCheckPassword();
        if (!passwordResult.isValid) {
            passwordResult.element.focus();
            return false;
        }
        
        const nameResult = await fnCheckName();
        if (!nameResult.isValid) {
            nameResult.element.focus();
            return false;
        }
        
        const mobileResult = fnCheckMobile();
        if (!mobileResult.isValid) {
            mobileResult.element.focus();
            return false;
        } 
        
        return true; // 모든 검증 통과
    } catch(error) {
        console.error("Validation failed", error);
        return false;
    }
}

// 이메일 검증
async function fnCheckEmail() {
    const userEmail = inpEmail.value.trim();
    const regEmail = /^[a-zA-Z0-9._%+-]+@(?![a-zA-Z0-9.-]*xn--)[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

    if (userEmail === "") {
        msgEmail.textContent = "이메일을 입력해주세요";
        return { isValid : false, element: inpEmail };
    } else if (!regEmail.test(userEmail)) {
        msgEmail.textContent = "이메일 형식을 확인하세요.";
        return { isValid : false, element: inpEmail };
    }
    
    try {
        const response = await fetch("/dokky/user/checkEmail", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ "userEmail": userEmail })
        });
        const data = await response.json();
        if (!data.enableEmail) {
            msgEmail.textContent = "이미 사용 중인 이메일입니다.";
            return { isValid: false, element: inpEmail };
        }
        return { isValid: true, userEmail: userEmail };  // 사용자 이메일을 반환
    } catch (error) {
        msgEmail.textContent = "이메일 확인 중 에러가 발생했습니다.";
        return { isValid: false, element: inpEmail };
    }
}

// 이메일 인증 코드 발송
async function fnSendCode(userEmail) {
    try {
        const response = await fetch("/dokky/user/sendCode", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ "userEmail": userEmail })
        });
        const data = await response.json();
        alert(`${userEmail}로 인증코드가 전송되었습니다`);
        
        inpCode.disabled = false;
        btnVerifyCode.disabled = false;
        btnVerifyCode.addEventListener("click", () => fnVerifyCode(data.code));
    } catch {
        msgEmail.textContent = "인증코드 발송 중 에러가 발생했습니다.";
        throw new Error("인증코드 발송 중 에러가 발생했습니다.");
    }
}

// 인증코드 검증
async function fnVerifyCode(expectedCode) {
    if (inpCode.value === expectedCode) {
        alert("인증되었습니다.");
        state.emailCheck = true;
        btnVerifyCode.disabled = true;
        console.log(state.emailCheck);
    } else {
        alert("인증코드가 일치하지 않습니다. 다시 확인해주세요.");
        inpCode.focus();
        throw new Error("인증코드가 일치하지 않습니다. 다시 확인해주세요.");
    }
}

// 비밀번호 검증
function fnCheckPassword() {
    const userPw = inpPw.value.trim();
    const passwordLength = userPw.length;
    const validCount = /[A-Za-z]/.test(userPw)
                     + /[0-9]/.test(userPw)
                     + /[^A-Za-z0-9]/.test(userPw);
    
    if (userPw === "") {
        msgPw.textContent = "비밀번호를 입력해주세요";
        return { isValid: false, element: inpPw };
        
    } else if (passwordLength < 4) {
        msgPw.textContent = "비밀번호는 최소 4자 이상이어야 합니다.";
        return { isValid: false, element: inpPw };
        
    } else if (validCount < 2) {
        msgPw.textContent = "비밀번호 4~12자, 영문/숫자/특수문자 중 2개 이상 포함해주세요.";
        return { isValid: false, element: inpPw };
        
    } else {
        msgPw.textContent = "사용 가능한 비밀번호입니다.";
        return { isValid: true, element: inpPw };
    }
}

// 닉네임 검증
async function fnCheckName() {
    const userName = inpName.value.trim();
    const namePattern = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/;

    if (userName === "") {
        msgName.textContent = "닉네임을 입력해주세요";
        nicknameCheck = false;
        return { isValid: false, element: inpName };
    } else if (!namePattern.test(userName)) {
        msgName.textContent = "닉네임 2~8자, 영어/숫자/한글로 구성 (공백, 초성, 모음 불가)";
        nicknameCheck = false;
        return { isValid: false, element: inpName };
    } 
    try {
        const response = await fetch("/dokky/checkNickname", {
            method: "POST",
            headers: { 'Content-Type': 'application/json' },
            body: userName
        });
        const result = await response.json();
        if (result === 0) {
            msgName.textContent = '사용 가능한 닉네임입니다.';
            nicknameCheck = true;
            return { isValid: true, element: inpName };
        } else {
            msgName.textContent = '이미 사용 중인 닉네임입니다.';
            nicknameCheck = false;
            return { isValid: false, element: inpName };
        }
    } catch (error) {
        console.error("Error checking nickname:", error);
        msgName.textContent = '닉네임 확인 중 문제가 발생했습니다.';
        nicknameCheck = false;
        return { isValid: false, element: inpName };
    }
}

// 휴대폰 번호 검증
function fnCheckMobile() {
    const mobileValue = inpMobile.value.replace(/[^0-9]/g, "");
    const mobilePattern = /^010\d{8}$/;

    if (mobileValue === "") {
        msgMobile.textContent = "";
        return { isValid: true, element: inpMobile };
    } else if (!mobilePattern.test(mobileValue)) {
        msgMobile.textContent = "휴대전화 번호를 확인하세요.";
        return { isValid: false, element: inpMobile };
        
    } else {
        msgMobile.textContent = "사용 가능한 전화번호입니다.";
        return { isValid: true, element: inpMobile };
    }
}

// 페이지 이동 시 입력값 초기화
window.addEventListener("beforeunload", function() {
   document.getElementById("frm-signup").reset();
});

// 인증 코드 요청 이벤트
document.getElementById("btn-code").addEventListener("click", async function() {
    if (!state.emailCheck) {  // 이메일이 인증되지 않았다면 인증 코드 발송
        const emailResult = await fnCheckEmail();
        if (emailResult.isValid) {
            try {
                await fnSendCode(emailResult.userEmail);
                state.emailCheck = true; // 인증 성공 상태 저장
            } catch (error) {
                console.error("Error sending code:", error);
                alert("인증 코드 전송 중 문제가 발생했습니다.");
            }
        }
    } else {
        alert("이메일 인증이 이미 완료되었습니다.");
    }
});

// 회원가입
document.getElementById("frm-signup").addEventListener("submit", async function(event) {
    event.preventDefault();
    if (!state.emailCheck) {
        alert("이메일 인증이 필요합니다.");
        inpEmail.focus();
        return;
    }
    // 검증 완료시 회원가입
    if (await validateForm()) {
        this.submit();  // 폼 제출
    } 
});

// 비밀 번호 입력 필드 이벤트 
document.getElementById("inp-pw").addEventListener("input", fnCheckPassword);

// 닉네임 입력 필드 이벤트
document.getElementById("inp-name").addEventListener("input", fnCheckName);

// 휴대폰 번호 입력 필드 숫자만 입력되게 처리하는 이벤트
document.getElementById("inp-mobile").addEventListener("input", function() {
    let inputValue = this.value.replace(/[^0-9]/g, "");
    
    if(inputValue.length > 11) {
        inputValue = inputValue.slice(0, -1);
    }
    
    this.value = inputValue;
    
    fnCheckMobile();
});

