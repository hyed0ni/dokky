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
  let email = inpEmail.val();
//  let regEmail = /^[A-Za-z0-9-_]{2,}@[A-Za-z0-9]+(\.[A-Za-z]{2,6}){1,2}$/;
  let regEmail = /^[a-zA-Z0-9-_]{2,}+@[a-zA-Z0-9]+(\.[a-zA-Z]){3}$/;
  
  if (!regEmail.test(email)) {
      emailCheck = false;
      alert('이메일 형식을 확인하세요.');
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
                  // 인증코드 로그찍기
                  console.log(resData.code);    
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
  pwCheck = passwordLength >= 6
               && validCount >= 2; 
  let msgPw = $('#msg-pw');
  if(pwCheck){
    msgPw.html('사용 가능한 비밀번호입니다.');
  } else {
    msgPw.html('비밀번호 4~12자, 영문/숫자/특수문자 중 2개 이상 포함해주세요.');
  }
}
  
// 닉네임 확인
const fnCheckName = () => {
  let inpName = $('#inp-name');
  let name = inpName.val();
  let nameCheck = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/;
  let msgName = $('#msg-name'); 
  if (!nameCheck.test(name)) { 
    msgName.html('닉네임 2~16자, 영어/숫자/한글로 구성 (공백, 초성, 모음 불가)');
  } else {
    msgName.html('사용 가능한 닉네임입니다.');
  }
}

// 휴대전화 확인
const fnCheckMobile = () => {
  let inpMobile = $('#inp-mobile');
  let mobile = inpMobile.val().replace(/[^0-9]/g, ''); 
  let mobileCheck = /^010\d{8}$/; 
  let msgMobile = $('#msg-mobile');
  if (!mobileCheck.test(mobile)) {
    msgMobile.html('휴대전화 번호를 확인하세요.');
  } else {
    msgMobile.html('사용 가능한 전화번호입니다.');
  }
}

// 입력값이 없을 때 검증 메시지 숨기기
$('#inp-email').on('blur', function() {
  if ($(this).val() === '') {
      $('#msg-email').html('');
  }
});

$('#inp-pw').on('blur', function() {
  if ($(this).val() === '') {
      $('#msg-pw').html('');
  }
});

$('#inp-name').on('blur', function() {
  if ($(this).val() === '') {
      $('#msg-name').html('');
  }
});

$('#inp-mobile').on('blur', function() {
  if ($(this).val() === '') {
      $('#msg-mobile').html('');
  }
});

// 필수입력 절차
const fnSignup = () => {
  $('#frm-signup').on('submit', function (evt) {
    if ($.trim($('#inp-email').val()) === '') {
      evt.preventDefault();
      alert('이메일을 입력해주세요.');
      return;
    } else if ($.trim($('#inp-pw').val()) === '') {
      evt.preventDefault();
      alert('비밀번호를 입력해주세요.');
      return;
    } else if ($.trim($('#inp-name').val()) === '') {
      evt.preventDefault();
      alert('닉네임을 입력해주세요.');
      return;
    }
  });
};

// 페이지 이동 시 입력값 초기화
window.addEventListener('beforeunload', function() {
  let inputElements = $('input[type=text], input[type=email], input[type=password], textarea');
  inputElements.each(function() {
      $(this).val('');
  });
});

// 문서 로딩 시 회원가입 절차 실행
$(document).ready(function() {
    fnSignup();
});

// 호출
$('#btn-code').on('click', fnCheckEmail);
$('#inp-pw').on('keyup', fnCheckPassword);
$('#inp-name').on('blur', fnCheckName);
$('#inp-mobile').on('blur', fnCheckMobile);
//fnSignup();
