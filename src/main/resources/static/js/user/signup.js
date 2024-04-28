var emailCheck = false;

// 닉네임 유효 여부 저장 변수
let nicknameCheck = false;

function fnCheckEmail() {
    return new Promise((resolve) => {
        let inpEmail = document.getElementById('inp-email').value;
        let email = inpEmail.trim();
        let msgEmail = document.getElementById('msg-email');
        let regEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

        if (email === '') {
            msgEmail.textContent = "이메일을 입력해주세요";
            return;
        } else if (!regEmail.test(email)) {
            msgEmail.textContent = '이메일 형식을 확인하세요.';
            return;
        } else {
            msgEmail.textContent = '';
        }

        fetch('/dokky/user/checkEmail', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ 'userEmail': email })
        })
        .then(response => response.json())
        .then(data => {
            if (data.enableEmail) {
                fnSendCode(email).then(() => {
                    emailCheck = true;
                    resolve();
                });
            } else {
                msgEmail.textContent = '이미 사용 중인 이메일입니다.';
            }
        })
        .catch(() => {
            msgEmail.textContent = '이메일 확인 중 에러가 발생했습니다.';
        });
    });   
}

// 이메일 인증 코드 발송
function fnSendCode(email) {
    return new Promise((resolve) => {
        let msgEmail = document.getElementById('msg-email');
        fetch('/dokky/user/sendCode', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ 'userEmail': email })
        })
        .then(response => response.json())
        .then(data => {
            alert(email + '로 인증코드가 전송되었습니다.');
            document.getElementById('inp-code').disabled = false;
            document.getElementById('btn-verify-code').disabled = false;
            document.getElementById('btn-verify-code').onclick = () => fnVerifyCode(data.code, resolve);
        })
        .catch(() => {
            msgEmail.textContent = '인증코드 발송 중 에러가 발생했습니다.';
        });
    });
}

// 이메일 인증 코드 발송
function fnSendCode(email) {
    return new Promise((resolve, reject) => {
        fetch('/dokky/user/sendCode', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ 'userEmail': email })
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            alert(email + '로 인증코드가 전송되었습니다.');
            document.getElementById('inp-code').disabled = false;
            document.getElementById('btn-verify-code').disabled = false;
            document.getElementById('btn-verify-code').onclick = () => fnVerifyCode(data.code, resolve, reject);
        })
        .catch(() => {
            document.getElementById('msg-email').textContent = '인증코드 발송 중 에러가 발생했습니다.';
            reject(new Error("Error while sending verification code"));
        });
    });
}

// 인증코드 검증
function fnVerifyCode(expectedCode, resolve, reject) {
    let enteredCode = document.getElementById('inp-code').value;
    if (enteredCode === expectedCode) {
        alert('인증되었습니다.');
        emailCheck = true;
        resolve(true);
    } else {
        alert('인증코드가 일치하지 않습니다. 다시 확인해주세요.');
        reject(new Error("Invalid verification code"));
    }
}

function fnCheckPassword() {
    let inpPw = document.getElementById('inp-pw');
    let password = inpPw.value.trim();
    let passwordLength = password.length;
    let validCount = /[A-Za-z]/.test(password) + /[0-9]/.test(password) + /[^A-Za-z0-9]/.test(password);
    let msgPw = document.getElementById('msg-pw');
    if (password === '') {
        msgPw.textContent = "비밀번호를 입력해주세요";
        return false;
    } else if (passwordLength < 4) {
        msgPw.textContent = "비밀번호는 최소 4자 이상이어야 합니다.";
        return false;
    } else if (validCount < 2) {
        msgPw.textContent = '비밀번호 4~12자, 영문/숫자/특수문자 중 2개 이상 포함해주세요.';
        return false;
    } else {
        msgPw.textContent = '사용 가능한 비밀번호입니다.';
        return true;
    }
}

function fnCheckName() {
    let inpName = document.getElementById('inp-name');
    let name = inpName.value.trim();
    let namePattern = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/;
    let msgName = document.getElementById('msg-name');

    if (name === '') {
        msgName.textContent = "닉네임을 입력해주세요";
        nicknameCheck = false; 
    } else if (!namePattern.test(name)) {
        msgName.textContent = '닉네임 2~16자, 영어/숫자/한글로 구성 (공백, 초성, 모음 불가)';
        nicknameCheck = false;
    } else {

        // 중복 검사
        fetch ("/dokky/mypage/checkNickname", {
            method: "POST",
            headers: {'Content-Type': 'application/json'}, 
            body: name
        })
        .then (response => response.text())
        .then (result => {
            if (result == 0) {
                msgName.textContent = '사용 가능한 닉네임입니다.';
                nicknameCheck = ture;
            } else {
                msgName.textContent = '이미 사용 중인 닉네임입니다.';
                nicknameCheck = false;
            }
        });
        
    }
}

function fnCheckMobile() {
    let inpMobile = document.getElementById("inp-mobile");
    let mobileValue = inpMobile.value.replace(/[^0-9]/g, '');
    let mobilePattern = /^010\d{8}$/;
    let msgMobile = document.getElementById('msg-mobile');

    if (!mobilePattern.test(mobileValue)) {
        msgMobile.textContent = '휴대전화 번호를 확인하세요.';
        return false;
    } else {
        msgMobile.textContent = '사용 가능한 전화번호입니다.';
        return true;
    }
}

// 페이지 이동 시 입력값 초기화
window.addEventListener('beforeunload', function() {
    let inputElements = document.querySelectorAll('input[type=text], input[type=email], input[type=password], textarea');
    inputElements.forEach(input => input.value = '');
});

document.getElementById('frm-signup').addEventListener('submit', function(event) {
    event.preventDefault();
    if (!emailCheck) {
        alert('이메일 인증이 필요합니다.');
        return;
    }
    
    if(fnCheckPassword() && nicknameCheck && fnCheckMobile()){
        this.submit();
    }
});

document.getElementById('btn-code').addEventListener('click', fnCheckEmail);

document.getElementById('inp-pw').addEventListener('input', fnCheckPassword);

document.getElementById('inp-name').addEventListener('input', fnCheckName);

document.getElementById('inp-mobile').addEventListener('input', function() {
    this.value = this.value.replace(/[^0-9]/g, '');
});
