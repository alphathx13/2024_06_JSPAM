<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int loginMemberNumber = (int) request.getAttribute("loginMemberNumber");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style type = "text/css">
	a {
		color : inherit;
		text-decoration: none;
	}
</style>
</head>
<body>
	<h2>메인 페이지</h2>
	
	<br/>
	<button type="button" onclick="location.href='/article/list'">게시판</button>
	<br/><br/>
	
	<% if (loginMemberNumber == -1) { %>
		<button class="logout" type="button" onclick="location.href='/member/join'">회원가입</button>
		<button class="logout" type="button" onclick="location.href='/member/login'">로그인</button>
	<% } 
	else { %>
		<div> <%= loginMemberNumber %>님 환영합니다.</div>
		<button class="login" type="button" onclick="location.href='/member/logout'">로그아웃</button>
	<% } %>
	
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
  		}
	
		let pw = document.querySelector(".pw");
  		let change = document.querySelector(".see");
  		change.addEventListener("click", function () {
  		  if (pw.type == "password") {
  		    pw.type = "text";
  		  } else {
  		    pw.type = "password";
  		  }
  		});
  		  		
  		}
	</script>
</body>
</html>