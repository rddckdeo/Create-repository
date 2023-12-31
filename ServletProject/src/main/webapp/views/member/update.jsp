<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<%@ include file="../common/nav.jsp"%>
	<section>
		<div class="container">
			<h1>회원 정보 수정</h1>
			<form action="/memberUpdate.do" method="post" id = "updateForm">
				<div class="form-group">
					<label for="username">이름</label> <input type="text" id="userName" name="userName" value="${member.name }"
						placeholder="이름">
				</div>
				<div class="form-group">
					<label for="id">아이디</label> <input type="text" id="userId" name="userId" value="${member.id }"
						placeholder="아이디">
				</div>
				<div class="form-group">
					<label for="password">비밀번호</label> <input type="password"
						id="userPwd" name="userPwd" placeholder="********">
				</div>
				<div class="form-group">
					<label for="indate">가입일</label> <input type="text" id="userIndate" value="${member.indate }"
						placeholder="가입일" disabled>
				</div>
				<button type="submit">수정</button>
				<button type="button" onclick = "deleteBtn();">회원탈퇴</button>
			</form>
		</div>
	</section>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>

<script>
	function deleteBtn(){
		let form = document.getElementById("updateForm");
		
		if(confirm("정말로 삭제하시겠습니까?")){
			form.action = "/memberDelete.do";
			form.submit();
		}
	}

</script>