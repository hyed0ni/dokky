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
    let inpEmail = $('#inp-email');
    let regEmail = /^[A-Za-z0-9-_]{2,}@[A-Za-z0-9]+(\.[A-Za-z]{2,6}){1,2}$/;
    if (!regEmail.test(inpEmail.val())) {
        alert('이메일 형식을 다시 확인해주세요.');
        emailCheck = false;
        return;
    }
    $.ajax({
        url: fnGetContextPath() + '/user/checkEmail',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            'userEmail': inpEmail.val()
        }),
        success: function (resData) {
            if (resData.enableEmail) {
                console.log(resData);
                $('#msg-email').html('');
                $.ajax({
                    url: fnGetContextPath() + '/user/sendCode',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        'userEmail': inpEmail.val()
                    }),
                    success: function (resData) {
                        alert(inpEmail.val() + '로 인증코드를 전송되었습니다.');
                        let inpCode = $('#inp-code');
                        let btnVerifyCode = $('#btn-verify-code');
                        inpCode.prop('disabled', false);
                        btnVerifyCode.prop('disabled', false);
                        btnVerifyCode.on('click', function (evt) {
                            if (resData.code === inpCode.val()) {
                                alert('인증되었습니다.');
                                emailCheck = true;
                            } else {
                                alert('인증되지 않았습니다.');
                                emailCheck = false;
                            }
                        });
                    }
                });
            } else {
                $('#msg-email').html('이미 사용 중인 이메일입니다.');
                emailCheck = false;
                return;
            }
        }
    });
}
  
// 비번 정규식 확인
const fnCheckPassword = () => {
  let inpPw = $('#inp-pw');
  let validCount = /[A-Za-z]/.test(inpPw.val())
                 + /[0-9]/.test(inpPw.val()) 
                 + /[^A-Za-z0-9]/.test(inpPw.val());
  let passwordLength = inpPw.val().length;
  passwordCheck = passwordLength >= 6
               && validCount >= 2;
  let msgPw = $('#msg-pw');
  if(passwordCheck){
    msgPw.html('사용 가능한 비밀번호입니다.');
  } else {
    msgPw.html('비밀번호 4~12자, 영문/숫자/특수문자 중 2개 이상 포함해주세요.');
  }
}
  
// 닉네임 확인
const fnCheckName = () => {
  let inpName = $('#inp-name');
  let name = inpName.val();
  let totalByte = 0;
  for (let i = 0; i < name.length; i++) {
    if (name.charCodeAt(i) > 127) { // 코드값이 127 초과이면 한 글자 당 2바이트 처리한다.
      totalByte += 2;
    } else {
      totalByte++;
    }
  }
  nameCheck = (totalByte <= 100);
  let msgName = $('#msg-name');
  if (!nameCheck) {
    msgName.html('이름은 100 바이트를 초과할 수 없습니다.');
  } else {
    msgName.html('');
  }
}

// 휴대전화 확인
const fnCheckMobile = () => {
  let inpMobile = $('#inp-mobile');
  let mobile = inpMobile.val();
  mobile = mobile.replace(/[^0-9]/g, '');
  mobileCheck = /^010[0-9]{8}$/.test(mobile);
  let msgMobile = $('#msg-mobile');
  if (mobileCheck) {
    msgMobile.html('');
  } else {
    msgMobile.html('휴대전화를 확인하세요.');
  }
}

// 회원가입 절차
const fnSignup = () => {
  $('#frm-signup').on('submit', function (evt) {
    if (!emailCheck) {
      alert('이메일을 확인하세요.');
      evt.preventDefault();
      return;
    } else if (!nameCheck) {
      alert('이름을 확인하세요.');
      evt.preventDefault();
      return;
    } else if (!mobileCheck) {
      alert('휴대전화를 확인하세요.');
      evt.preventDefault();
      return;
    }
  });
}

// 호출
$('#btn-code').on('click', fnCheckEmail);
$('#inp-pw').on('keyup', fnCheckPassword);
$('#inp-name').on('blur', fnCheckName);
$('#inp-mobile').on('blur', fnCheckMobile);
fnSignup();
