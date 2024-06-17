<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<style type="text/css">
	button {
		width: 100px;
		height: 30px;
	}
	
	input, textarea {
		width: 300px;
	}
	
	textarea {
		height: 300px;
	}
	
	a {
		color : inherit;
		text-decoration: none;
	}
	
	.small {
		height : 22px;
	}
	
</style>
</head>

<body>

	<h1>회원 로그인</h1>
	
	<form action="/member/doLogin" method="post" onsubmit="check(this); return false;">
		<div> 아이디 : <input type ="text" name = "id" placeholder = "아이디를 입력해주세요"/></div>
		<div> 비밀번호 : <input class = "pw" type = "password" name="pw" placeholder = "비밀번호를 입력해주세요"/><button class = "see" type="button">보기</button></div>
		<button> 로그인 </button>
	</form>
	
	<h3><a class = "a pageLink" href="/home/main">메인 화면으로</a></h3>

	<script> 		
  		function check(form) {
  			let id = form.id.value.trim();
  			let pw = form.pw.value.trim();
  	    	
  	  		if (id.length == 0) {
  	  			alert('id는 필수입력 정보입니다.');
  	  			form.id.focus();
  	  			return;
  	  		}
  	  		
  	  		if (pw.length == 0) {
	  			alert('암호는 필수입력 정보입니다.');
	  			form.pw.focus();
  	  			return;
	  		}
  	  		
  	  		form.submit();
  	  	}
  		
  		let input = document.querySelector(".pw");
  		let change = document.querySelector(".see");
  		change.addEventListener("click", function () {
  		  if (input.type == "password") {
  		    input.type = "text";
  		  } else {
  		    input.type = "password";
  		  }
  		});
  	
	</script>
</body>
</html>