/**
 * 
 */

var emailCheck = false;
var pwCheck = false;
var nameCheck = false;
var mobileCheck = false;
 
// 경로 설정
const fnGetContextPath = () => {
	const host = location.host;
	const url = location.href;
	const begin = url.indexOf(host) + host.length;
	const end = url.indexOf('/', begin + 1);
	return url.substring(begin, end);
}
 
// 메일 형식 확인
const fnCheckEmail = () => {
  let inpEmail = document.getElementById('inp-email');
  let regEmail = /^[A-Za-z0-9-_]{2,}@[A-Za-z0-9]+(\.[A-Za-z]{2,6}){1,2}$/;
  if(!regEmail.test(inpEmail.value)){
	  alert('이메일 형식을 다시 확인해주세요.');
	  emailCheck = false;
	  return;
  }
	fetch(fnGetContextPath() + '/user/checkEmail',{
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			'userEmail': inpEmail.value
		})
	})
	.then(response => response.json())
	.then(resData => {
    if(resData.enableEmail){
			console.log(resData);
      document.getElementById('msg-email').innerHTML = '';
      fetch(fnGetContextPath() + '/user/sendCode', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          'userEmail': inpEmail.value
        })
      })
      .then(response => response.json())
      .then(resData => {
        alert(inpEmail.value + '로 인증코드를 전송되었습니다.');
        let inpCode = document.getElementById('inp-code');
        let btnVerifyCode = document.getElementById('btn-verify-code');
        inpCode.disabled = false;
        btnVerifyCode.disabled = false;
        btnVerifyCode.addEventListener('click', (evt) => {
          if(resData.code === inpCode.value) {
            alert('인증되었습니다.');
            emailCheck = true;
          } else {
            alert('인증되지 않았습니다.');
            emailCheck = false;
          }
        })
      })
		} else {
			document.getElementById('msg-email').innerHTML = '이미 사용 중인 이메일입니다.';
			emailCheck = false;
			return;
		}
	})
}
  
// 비번 정규식 확인
const fnCheckPassword = () => {
  let inpPw = document.getElementById('inp-pw');
  let validCount = /[A-Za-z]/.test(inpPw.value)
                 + /[0-9]/.test(inpPw.value) 
                 + /[^A-Za-z0-9]/.test(inpPw.value);
  let passwordLength = inpPw.value.length;
  passwordCheck = passwordLength >= 6
               && validCount >= 2;
  let msgPw = document.getElementById('msg-pw');
  if(passwordCheck){
    msgPw.innerHTML = '사용 가능한 비밀번호입니다.';
  } else {
    msgPw.innerHTML = '비밀번호 4~12자, 영문/숫자/특수문자 중 2개 이상 포함해주세요.';
  }
}
  
// 닉네임 확인
const fnCheckName = () => {
  let inpName = document.getElementById('inp-name');
  let name = inpName.value;
  let totalByte = 0;
  for(let i = 0; i < name.length; i++) {
    if(name.charCodeAt(i) > 127) {  // 코드값이 127 초과이면 한 글자 당 2바이트 처리한다.
      totalByte += 2;
    } else {
      totalByte++;
    }
  }
  nameCheck = (totalByte <= 100);
  let msgName = document.getElementById('msg-name');
  if(!nameCheck){
    msgName.innerHTML = '이름은 100 바이트를 초과할 수 없습니다.';
  } else {
    msgName.innerHTML = '';
  }
}

// 휴대전화 확인
const fnCheckMobile = () => {
  let inpMobile = document.getElementById('inp-mobile');
  let mobile = inpMobile.value;
  mobile = mobile.replaceAll(/[^0-9]/g, '');
  mobileCheck = /^010[0-9]{8}$/.test(mobile);
  let msgMobile = document.getElementById('msg-mobile');
  if(mobileCheck) {
    msgMobile.innerHTML = '';
  } else {
    msgMobile.innerHTML = '휴대전화를 확인하세요.';
  }
}

// 회원가입 절차
const fnSignup = () => {
  document.getElementById('frm-signup').addEventListener('submit', (evt) => {
    if(!emailCheck) {
      alert('이메일을 확인하세요.');
      evt.preventDefault();
      return;
    } else if(!nameCheck) {
      alert('이름을 확인하세요.');
      evt.preventDefault();
      return;
    } else if(!mobileCheck) {
      alert('휴대전화를 확인하세요.');
      evt.preventDefault();
      return;
		}
  })
}

// 호출
document.getElementById('btn-code').addEventListener('click', fnCheckEmail);
document.getElementById('inp-pw').addEventListener('keyup', fnCheckPassword);
document.getElementById('inp-name').addEventListener('blur', fnCheckName);
document.getElementById('inp-mobile').addEventListener('blur', fnCheckMobile);
fnSignup();
