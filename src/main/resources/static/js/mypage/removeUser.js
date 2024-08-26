// 취소, 탈퇴 버튼
const $cancelBtn = $("#cancel-btn");
const $removeBtn = $("#remove-user-btn");

// 취소 버튼 클릭 시 마이페이지로 이동
$cancelBtn.on("click", () => location.href = "/dokky/mypage");

// 비밀번호 입력 시 탈퇴 버튼 활성화
$("#origin-pw").on("input", e => {
    $removeBtn.toggleClass("disabled", $(e.target).val().length < 4);
});
