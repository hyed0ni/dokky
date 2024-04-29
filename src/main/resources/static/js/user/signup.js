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
  let regEmail = /^[A-Za-z0-9-_]{2,}@[A-Za-z0-9]+(\.[A-Za-z]{2,3}){1,2}$/;
  
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
                  btnVerifyCode.on('click', function () {
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
						$('#msg-email').html('<span class="error-message">이미 사용 중인 이메일입니다.</span>');
            emailCheck = false;
            return;
	        }
      	}
  	});
}
  
let pwenable = false;
let nameenable = false;

const updateSignupButton = () => {
  if (pwenable && nameenable) {
    $("#btn-signup").removeClass("btn-secondary").addClass("btn-primary").attr("type", "submit").prop("disabled", false);
  } else {
    $("#btn-signup").removeClass("btn-primary").addClass("btn-secondary").attr("type", "button").prop("disabled", true);
  }
};

// 비밀번호 정규식 확인
const fnCheckPassword = () => {
  let inpPw = $('#inp-pw');
  let validCount = /[A-Za-z]/.test(inpPw.val())
                 + /[0-9]/.test(inpPw.val()) 
                 + /[^A-Za-z0-9]/.test(inpPw.val());
  let passwordLength = inpPw.val().length;
  pwenable = 4 <= passwordLength && passwordLength <= 12 && validCount >= 2;
  let msgPw = $('#msg-pw');
  if (pwenable) {
    msgPw.html('사용 가능한 비밀번호입니다.');
  } else {
    msgPw.html('<span class="error-message">비밀번호 4~12자, 영문/숫자/특수문자 중 2개 이상 포함해주세요.</span>');
  }
  updateSignupButton();
};
  
// 닉네임 확인
const fnCheckName = () => {
  let inpName = $('#inp-name');
  let name = inpName.val();
  let nameCheck = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,8}$/;
  let msgName = $('#msg-name'); 
  nameenable = nameCheck.test(name);
  if (nameenable) {
    msgName.html('사용 가능한 닉네임입니다.');
  } else {
    msgName.html('<span class="error-message">닉네임 2~8자, 영어/숫자/한글로 구성 (공백, 초성, 모음 불가)</span>');
  }
  updateSignupButton();
};

$(document).ready(() => {
  $('#inp-pw').on('input', fnCheckPassword);
  $('#inp-name').on('input', fnCheckName);
});

// 휴대전화 확인
document.getElementById("inp-mobile").addEventListener("input", e => {
  const inpValue = e.target.value;
  const regExp = /^[0-9]*$/; // 수정된 정규식, 하이픈을 허용하지 않음
  if (!regExp.test(inpValue)) {
      e.target.value = inpValue.replace(/[^\d]/g, ''); // 숫자가 아닌 문자 제거
  }
});

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

// 필수입력 절차
const fnSignup = () => {
  $('#frm-signup').on('submit', function (evt) {
    if ($.trim($('#inp-email').val()) === '') {
      alert('이메일을 입력해주세요.');
      evt.preventDefault();
      return;
    } else if ($.trim($('#inp-code').val()) === '') {
      alert('인증코드를 인증해주세요.');
      evt.preventDefault();
      return;
    } else if ($.trim($('#inp-pw').val()) === '') {
      alert('비밀번호를 입력해주세요.');
      evt.preventDefault();
      return;
    } else if ($.trim($('#inp-name').val()) === '') {
      alert('닉네임을 입력해주세요.');
      evt.preventDefault();
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
$('#inp-name').on('keyup', fnCheckName);