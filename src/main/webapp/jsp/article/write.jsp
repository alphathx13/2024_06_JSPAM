<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>    
    
<%
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article Write</title>
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
</style>
</head>
<body>

	<h1>Article Write</h1>
	
	<form action="/article/doWrite" method="post">
		<div> 제목 : <input type ="text" name = "title" placeholder = "입력해주세요"/></div>
		<div style = "display : flex;"> 내용 :&nbsp;<textarea name="body"></textarea> </div>
		
		<br/>
		
		<button>작성</button>
		<button type="button" onclick="location.href='list'">게시글 리스트</button>
	</form>

</body>
</html>