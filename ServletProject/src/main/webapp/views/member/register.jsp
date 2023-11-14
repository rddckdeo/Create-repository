<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script src="/resources/js/member/register.js"></script>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/nav.jsp"%>

	<section>
		<div class="signup-form">
			<h2>회원가입</h2>
			<form action="/register.do" method="POST">
				<div class="input-container2"></div>
				<div class="input-container2">
					<label for="new-username">이름:</label> <input type="text"
						id="new-username" name="new-username" onkeyup=" validateName()"
						required> <span id="nameMsg"></span>
				</div>

				<br>
				<div class="input-container2">
					<label for="new-userid">아이디:</label> <input type="text"
						id="new-userid" name="new-userid" onkeyup="duplicateId()" required>
					<span id="idCheck"></span>
				</div>
				<br>
				<div class="input-container2">
					<label for="new-password">비밀번호:</label> <input type="password"
						id="new-password" name="new-password" onkeyup="validatePassword()" required>
						<span id="passwordMsg"></span>
				</div>
				<br>
				<div class="input-container2">
					<label for="passwordChk">비밀번호 확인:</label> <input
						type="password" id="passwordChk" name="passwordChk" onkeyup="validatePassword()"
						required> <span id="passwordChkMsg"></span>
				</div>
				<br>
				<button type="submit">회원가입</button>
			</form>
		</div>
	</section>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>

<script>
	//아이디 중복확인
	function duplicateId() {
		const id = document.getElementById("new-userid").value;
		const idCheck = document.getElementById("idCheck");

		$.ajax({
			type : "POST",
			url : "/duplicateId.do",
			data : {
				id : id
			},
			//	서블릿에서 사용할 변수명 : 현재변수명
			success : function(response) {
				if (response === 'true') {
					idCheck.style.color = "red";
					idCheck.innerHTML = "중복된 아이디입니다.";
				} else {
					idCheck.style.color = "green";
					idCheck.innerHTML = "사용 가능한 아이디입니다.";
				}
			},
			error : function(response) {
				console.log("실패: " + response);
			}
		})
	}

	//이름 유효성 검사
	function validateName() {
		// ^[a-zA-Z0-9]+@  : 시작 문자열인데, 영어숫자 가능, 1개이상 무조건 있어야 함, @는 문자열
		// [a-zA-Z0-9]+\.  : 영어숫자 가능, 1개 이상 무조건 있어야 함, .는 문자열
		// [a-zA-Z]{2,}$   : 마지막 문자열이 영어로 끝나야 함, 2글자 이상

		const nameRegex = /^[가-힣]{2,}$/;
		const name = document.getElementById("new-username").value;
		const nameMsg = document.getElementById("nameMsg");

		if (name == "") {
			nameMsg.innerHTML = "이름을 입력해주세요.";
			nameMsg.style.color = "red";
		} else if (nameRegex.test(name)) {
			nameMsg.innerHTML = "사용 가능한 이름입니다.";
			nameMsg.style.color = "green";
		} else {
			nameMsg.innerHTML = "이름은 한글만 가능합니다.";
			nameMsg.style.color = "red";
		}

	}
	
	//비밀번호 유효성 검사
	   function validatePassword() {
            // 소문자 또는 대문자 최소 1개 이상
            // 특수문자 최소 1개 이상
            // 6자리 이상 20자리 이하
            const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[@$!%*?&\#])[A-Za-z\d@$!%*?&\#]{6,20}$/;
            const password = document.getElementById("new-password").value;
            const pwdMsg = document.getElementById("passwordMsg");
            
            if(password == "") {
            	pwdMsg.innerHTML = "비밀번호를 입력하세요.";
            	pwdMsg.style.color = "red";
            } else if(passwordRegex.test(password)) {
            	pwdMsg.innerHTML = "사용 가능한 비밀번호 입니다.";
            	pwdMsg.style.color = "green";
            } else {
            	pwdMsg.innerHTML = "패스워드 정책에 맞지 않습니다.";
            	pwdMsg.style.color = "red";
            }
			//비밀번호 동일한지 검사
            const passwordChk = document.getElementById("passwordChk").value;
            const passwordChkMsg = document.getElementById("passwordChkMsg");

            if(passwordChk === password) {
            	passwordChkMsg.innerHTML = "패스워드가 동일합니다.";
            	passwordChkMsg.style.color = "green";
            } else {
            	passwordChkMsg.innerHTML = "패스워드가 동일하지 않습니다.";
            	passwordChkMsg.style.color = "red";
            }
        }
</script>

