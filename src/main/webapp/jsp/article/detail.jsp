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
</style>
</head>
<body>

	<h1>ArticleDetail</h1>

	<nav class="menu-bar">
		<ul>
			<li><h2><%= article.get("id") %>번 게시글</h2></li>
			<li><br></li>
			<li>작성일시 : <%= article.get("regDate") %></li>
			<li>수정일시 : <%= article.get("updateDate") %></li>
			<li>글 제목 : <%= article.get("title") %></li>
			<li>글 내용 : <%= article.get("body") %></li>
		</ul>
	</nav>
	
	<br>
	
	<button><a href="modify?id=<%= article.get("id") %>">글 수정하기</a></button>
	<button><a href="delete?id=<%= article.get("id") %>" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false">글 삭제하기</a></button>
	<button type="button" onclick="location.href='list'">게시글 목록으로 돌아가기</button>
	
</body>
</html>