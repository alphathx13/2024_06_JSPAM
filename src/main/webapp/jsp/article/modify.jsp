<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>    
    
<%
	Map<String, Object> article = (Map<String, Object>) request.getAttribute("article");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article Modify</title>
<style type = "text/css">
	a {
		color : inherit;
		text-decoration: none;
	}
	
	ul {
		display : inline-block;
	}
	
	li {
		display : block;
	}
	
	nav {
		background-color: skyblue;
	}
	
	input, textarea {
		width: 300px;
	}
	
	textarea {
		height: 300px;
	}
	
</style>
</head>
<body>

	<h1>Article Modify</h1>

	<form action="/article/doModify" method="post">
		<div> 제목 : <input type ="text" name = "title" placeholder = "<%= article.get("title") %>"/></div>
		<div style = "display : flex;"> 내용 :&nbsp;<textarea name="body" placeholder = "<%= article.get("body") %>"></textarea> </div>
		<input type="hidden" value=<%= article.get("id") %> name = id>
		
		<br/>
		
		<button>글 수정하기</button>
		<button type="button" onclick="location.href='/article/detail?id=<%= article.get("id") %>'">게시글로 돌아가기</button>
	</form>
	
	
</body>
</html>