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

	<div>
		<h2>페이지 이동</h2>
		<a href=" <%= request.getContextPath() %>/article/list">게시글 목록</a>
	</div>

	<h1>ArticleDetail</h1>

	<nav class="menu-bar" style = "background-color : skyblue">
		<ul style = "display : inline-block">
			<li style = "display : block">글번호 : <%= article.get("id") %></li>
			<li style = "display : block">글 제목 : <%= article.get("title") %></a></li>
			<li style = "display : block">글 내용 : <%= article.get("body") %></li>
			<li style = "display : block">작성일시 : <%= article.get("regDate") %></li>
			<li style = "display : block">수정일시 : <%= article.get("updateDate") %></li>
			<li style = "display : block">작성자 : <%= article.get("writer") %></li>
		</ul>
	</nav>
	
</body>
</html>