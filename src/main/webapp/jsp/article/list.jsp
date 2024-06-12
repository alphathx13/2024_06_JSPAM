<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map, java.util.ArrayList" %>    
    
<%
	List<Map<String, Object>> articleList = (List<Map<String, Object>>) request.getAttribute("articleList");
	int cPage = (int) request.getAttribute("cPage");
	int tPage = (int) request.getAttribute("tPage");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article List</title>
</head>
<body>

	<h1><a href="/home/main">메인 페이지로 이동</a></h1>

	<h3><%= cPage %> / <%= tPage %></h3>
	
	<table style="text-align:center;">
		<colgroup>
			<col/>
			<col width="150"/>
			<col width="200"/>
			<!-- 특정 column에만 길이를 부여 -->
		</colgroup>
		<thead>
			<tr>
				<th style="border-radius : 5px; border: 3px solid skyblue; border-collapse: collapse; padding : 5px">글 번호</th>
				<th style="border-radius : 5px; border: 3px solid skyblue; border-collapse: collapse; padding : 5px">글 제목</th>
				<th style="border-radius : 5px; border: 3px solid skyblue; border-collapse: collapse; padding : 5px">수정일시</th>
			</tr>
		</thead>
		<tbody>
			<% for (Map<String, Object> article : articleList) { %>
				<tr>
					<td style="border-radius : 5px; border: 2px solid skyblue; border-collapse: collapse; padding : 5px"><%= article.get("id") %></td>
					<td style="border-radius : 5px; border: 2px solid skyblue; border-collapse: collapse; padding : 5px"><a href="/article/detail?id=<%= article.get("id") %>" style = "text-decoration : none"><%= article.get("title") %></a></td>
					<td style="border-radius : 5px; border: 2px solid skyblue; border-collapse: collapse; padding : 5px"><%= article.get("updateDate") %></td>
				</tr>
			<% } %>	
		</tbody>
	</table>
	
	<br>
	
	<% 
	if (cPage <= 4)
		cPage = 5;
	else if (cPage > tPage-4)
		cPage = tPage-4;
	%>
	
	<div style = "display : inline-block">
		<a href="?page=<%= 1 %>" style = "text-decoration : none; inherit"> 처음으로 </a>
	</div>
	
	<% for (int i = cPage-4; i <= cPage+4; i++) {%>
		<div style = "display : inline-block">
			<a href="?page=<%= i %>" style = "text-decoration : none; inherit"> <%= i %> </a>
		</div>
	<% }%>

	<div style = "display : inline-block">
		<a href="?page=<%= tPage %>" style = "text-decoration : none; inherit"> 마지막 </a>
	</div>
	
	<h1><%= tPage %></h1>
	
</body>
</html>