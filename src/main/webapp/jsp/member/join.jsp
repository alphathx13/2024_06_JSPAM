<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>    
    
<%
%>

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
	
 	.pass:hover { 
 		type = "text";
 	} 
</style>
</head>
<body>

	<h1>회원 가입</h1>
	
	<form action="/member/doJoin" method="post" onsubmit="check(this); return false;">
		<div> 아이디 : <input type ="text" name = "id" placeholder = "아이디를 입력해주세요"/></div>
		<div> 비밀번호 : <input id = "pw1" type ="password" name = "password" placeholder = "비밀번호를 입력해주세요"/> <button type="button" onmouseover="view()" onmouseout="viewEnd()">비밀번호 보기</button></div> 
		<div> 비밀번호 확인 : <input id = "pw2" type ="password" name = "passwordCheck" placeholder = "비밀번호를 다시 입력해주세요"/></div> 
		<div> 이름 : <input type ="text" name = "name" placeholder = "이름을 입력해주세요"/></div> 
		
		<br/>
		
		<button>회원가입</button> 
		<button type="button" onclick="location.href='../home/main'">메인 페이지</button>
	</form>

	<script>
  		function view() {
  			document.getElementById("pw1").type='text';
  			document.getElementById("pw2").type='text';
  		}
  		
  		function viewEnd() {
  			document.getElementById("pw1").type='password';
  			document.getElementById("pw2").type='password';
  		}
  		
  		function check(form) {
  			let id = form.id.value.trim();
  			let pw1 = form.password.value.trim();
  			let pw2 = form.passwordCheck.value.trim();
  			let name = form.name.value.trim();
  	    	
  	  		if (id.length == 0) {
  	  			alert('id는 필수입력 정보입니다.');
  	  			form.id.focus();
  	  			return;
  	  		}
  	  		
  	  		if (pw1.length == 0) {
	  			alert('암호는 필수입력 정보입니다.');
	  			form.pw1.focus();
  	  			return;
	  		}
  	  	
  	 		if (pw2.length == 0) {
  				alert('입력해주세요.');
  				form.pw2.focus();
  	  			return;
  			}
  	  
  			if (name.length == 0) {
				alert('이름은 필수입력 정보입니다.');
				form.name.focus();
  	  			return;
			}
  	  		
  	  		if (pw1 != pw2) {
  	  			alert('비밀번호를 확인해주세요.'); 
				form.pw1.value = "";
				form.pw2.value = "";
				form.pw1.focus();
  	  			return;
  	  		}
  	  		
  	  		form.submit();
  	  	}
<!--   		
		function passCheck() {
  	    	let pw1 = document.getElementById('pw1').value;
  	  		let pw2 = document.getElementById('pw2').value;
  	  		if (pw1 != pw2)
  	      		return false;
  	  	}
-->  	  	
	</script>
</body>
</html>