<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map, java.util.ArrayList" %>    
    
<%
	List<Map<String, Object>> articleList = (List<Map<String, Object>>) request.getAttribute("articleList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article List</title>
</head>
<body>

	<h1>ArticleList</h1>

	<nav class="menu-bar" style = "background-color : skyblue; display : inline-block; padding-right : 30px">
		<% for (Map<String, Object> article : articleList) { %>
			<ul style = "display : inline-block">
				<li style = "display : block">글번호 : <%= article.get("id") %></li>
				<li style = "display : block">글 제목 : <a href="/article/detail?id=<%= article.get("id") %>" style = "text-decoration : none"><%= article.get("title") %></a></li>
				<li style = "display : block">수정일시 : <%= article.get("updateDate") %></li>
			</ul>
		<% } %>
	</nav>
	
</body>
</html>