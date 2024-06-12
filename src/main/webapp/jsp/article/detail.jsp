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
<title>Article Detail</title>
</head>
<body>

	<h1>ArticleDetail</h1>

	<nav class="menu-bar" style = "background-color : skyblue">
		<ul style = "display : inline-block">
			<li style = "display : block"><h2><%= article.get("id") %>번 게시글</h2></li>
			<li style = "display : block"><br></li>
			<li style = "display : block">작성자 : <%= article.get("writer") %></li>
			<li style = "display : block">작성일시 : <%= article.get("regDate") %></li>
			<li style = "display : block">수정일시 : <%= article.get("updateDate") %></li>
			<li style = "display : block">글 제목 : <%= article.get("title") %></li>
			<li style = "display : block">글 내용 : <%= article.get("body") %></li>
		</ul>
	</nav>
	
	<br>
	
	<button><a href="/article/delete?id=<%= article.get("id") %>" style = "text-decoration : none">글 삭제하기</a></button>
	<button><a href="list" style = "text-decoration : none">게시글 목록으로 돌아가기</a></button>
	
</body>
</html>