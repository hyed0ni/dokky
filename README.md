## 🧑‍💻 DOKKY: 개발자 지식공유 플랫폼
국내 최대 개발자 지식공유 플랫폼인 [OKKY](https://okky.kr/)를 벤치마킹한 사이트입니다.

<br>

## ⌛ 제작 기간
2024년 04월 15일 ~ 2024년 04월 30일 (2주)

<br>

## ⚒️ 개발 환경 및 사용 기술
**[사용 기술]**
<p>
  <img src="https://img.shields.io/badge/java-007396?style=flat-square&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/Oracle-F80000?style=flat-square&logo=Oracle&logoColor=white">
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=HTML5&logoColor=white">
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=CSS3&logoColor=white">
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=black">
  <img src="https://img.shields.io/badge/jQuery-0769AD?style=flat-square&logo=jQuery&logoColor=white">
</p>

**[개발 환경]**
<p>
  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white">
  <img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=Gradle&logoColor=white">      
  <img src="https://img.shields.io/badge/Apache Tomcat-F8DC75?style=flat-square&logo=Apache Tomcat&logoColor=black">
  <img src="https://img.shields.io/badge/Eclipse IDE-2C2255?style=flat-square&logo=Eclipse IDE&logoColor=white">
  <img src="https://img.shields.io/badge/Bootstrap-7952B3?style=flat-square&logo=Bootstrap&logoColor=white">
</p>

**[협업 툴]**
<p>
  <img src="https://img.shields.io/badge/Trello-0052CC?style=flat-square&logo=Trello&logoColor=white">
  <img src="https://img.shields.io/badge/Slack-4A154B?style=flat-square&logo=Slack&logoColor=white">
  <img src="https://img.shields.io/badge/Git-F05032?style=flat-square&logo=Git&logoColor=white">
  <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=white">
</p>

<br>

##  🌱 맡은 역할

### **📣 ROLE**
- 프로젝트 리더
- 트렐로, 구글 시트, 슬랙 관리

### **⚙️ 기능 구현**
- **회원 정보 수정** <br><br>
  ![1_회원정보수정](https://github.com/user-attachments/assets/3203becd-5a76-449c-96b1-d205f4eac569)
  - 닉네임과 휴대전화는 정규 표현식을 통해 올바른 형식이 아닐 경우 검증 메시지를 출력합니다.
  - 중복된 닉네임은 허용되지 않으며, 휴대전화는 숫자만 입력이 가능합니다.
  - 초기 회원가입 시 프로필 이미지는 기본 값으로 설정되며, 선택된 이미지는 미리 보기로 확인할 수 있습니다.
  - 회원 정보 변경 시 헤더 우측의 프로필 이미지와 닉네임이 변경된 회원 정보로 표시됩니다.
 
- **비밀번호 변경** <br><br>
  ![2_비밀번호변경_실패](https://github.com/user-attachments/assets/79969bdb-3f79-4fdc-9072-f38a4a288afb)
  - 정규 표현식을 통해 올바른 형식이 아닐 경우 검증 메시지를 출력합니다.
  - 신규 비밀번호와 신규 비밀번호 확인 값이 서로 일치하지 않을 경우 검증 메시지를 출력합니다.
 
  ![3_비밀번호변경_성공](https://github.com/user-attachments/assets/9e3903f0-1785-49e0-8981-eb72b64fc1ca)
  - 비밀번호 변경 완료 시 세션이 무효화되어 자동 로그아웃되며, 로그인 페이지로 이동합니다.
  - 변경된 비밀번호는 SHA-256 해시 알고리즘을 사용해 암호화한 후 DB에 저장합니다.

- **회원 탈퇴** <br><br>
  ![4_회원탈퇴](https://github.com/user-attachments/assets/4646b4b6-cb1d-429b-a522-a2219faca279)
  - 계정 삭제 정책 동의 체크박스 선택 시 회원 탈퇴 버튼이 활성화됩니다.
  - 비밀번호를 4자리 이상 입력 시 탈퇴 버튼이 활성화되며, 회원 탈퇴 성공 시 메인 페이지로 이동합니다.
  <br><br>
  ```sql
  <!-- 계정 삭제: 탈퇴일 수정 -->
	<delete id="removeUser">
		UPDATE USER_T 
		SET WITHDRAWAL_DT = CURRENT_DATE
		WHERE USER_NO = #{userNo} 
			AND USER_PW = #{originPw}
	</delete>
	
	<!-- 계정 삭제: 데이터 삭제 -->
	<delete id="deleteUserData">
		DELETE FROM USER_T 
		WHERE (CURRENT_DATE - WITHDRAWAL_DT) > 60
	</delete>
  ```
  ```java
  // 탈퇴 회원 데이터 삭제 스케줄러
	@Scheduled(cron = "0 0 0 * * *") // 매일 자정마다 실행
	public void deleteUserData() {
		int result = myPageService.deleteUserData();
	}
  ```
  - 회원 탈퇴 성공 시 회원 탈퇴 일을 현재 날짜로 업데이트합니다.
  - 스케줄러를 이용해 매일 자정마다 회원 탈퇴 일로부터 60일이 경과된 회원의 정보를 삭제합니다.
