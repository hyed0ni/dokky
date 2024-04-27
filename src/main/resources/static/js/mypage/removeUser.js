// 취소, 탈퇴 버튼
const cancelBtn = document.getElementById("cancel-btn");
const removeBtn = document.getElementById("remove-user-btn");

// 취소 버튼 클릭 시 마이페이지로 이동
cancelBtn.addEventListener("click", () => 
    location.href = "/dokky/mypage");

// 비밀번호 입력 시 탈퇴 버튼 활성화
document.getElementById("origin-pw").addEventListener("input", e => {
    if (e.target.value.length >= 4) removeBtn.classList.remove("disabled");
    else removeBtn.classList.add("disabled");
});