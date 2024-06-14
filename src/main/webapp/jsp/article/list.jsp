<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map, java.util.ArrayList" %>    
    
<%
	List<Map<String, Object>> articleList = (List<Map<String, Object>>) request.getAttribute("articleList");
	int cPage = (int) request.getAttribute("cPage");
	int tPage = (int) request.getAttribute("tPage");
	int from = (int) request.getAttribute("from");
	int end = (int) request.getAttribute("end");
	int tempPage = cPage;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article List</title>
<style type="text/css">

	a {
		color: inherit;
		text-decoration: none;
	}
	
	.cPage {
		color : red;
		font-weight: bold;
		font-size: 1.5rem;
	}
	
	.pageLink:hover {
		color : skyblue;
		font-weight: bold;
	}

	.tableStyle {
		border-radius : 5px; 
		border: 3px solid skyblue; 
		border-collapse: collapse; 
		padding : 5px
	}
	
	select {
  		margin-bottom: 10px;
  		padding: 4px;
  		border-radius: 9px;
}
	
</style>
</head>
<body>

	<h3><%= cPage %> / <%= tPage %></h3>
	<form action="" method="post"> 
		<label for=""></label>
		<input type="hidden" name ="page" value="1"/>
		<select onchange="this.form.submit()" name="itemsInPage">
			<option value="" selected disabled> 한페이지에 보이는 게시글
			<option value="10"> 10개
			<option value="20"> 20개
			<option value="30"> 30개
			<option value="50"> 50개
			<option value="100"> 100개
		</select>
	</form>
	
	<table style="text-align:center;">
		<colgroup>
			<col/>
			<col width="150"/>
			<col width="200"/>
			<!-- 특정 column에만 길이를 부여 -->
		</colgroup>
		<thead>
			<tr>
				<th class = "tableStyle">글 번호</th>
				<th class = "tableStyle">글 제목</th>
				<th class = "tableStyle">수정일시</th>
			</tr>
		</thead>
		<tbody>
			<% for (Map<String, Object> article : articleList) { %>
				<tr>
					<td class = "tableStyle"><%= article.get("id") %></td>
					<td class = "tableStyle"><a href="/article/detail?id=<%= article.get("id") %>"><%= article.get("title") %></a></td>
					<td class = "tableStyle"><%= article.get("updateDate") %></td>
				</tr>
			<% } %>	
		</tbody>
	</table>
 	
	<button type="button" onclick="location.href='write'">글 작성하기</button>
	
	<div>
		<% if (from != 1) { %>
			<a class = "pageLink" href="?page=1">≪</a>
			<a class = "pageLink" href="?page=<%= from - 1 %>">◀</a>
		<% } 
		if (end > tPage) {
			end = tPage;
		} %>
		<% for (int i = from; i <= end; i++) { %>
			<a class="<%= cPage == i ? "cPage" : "pageLink" %>" href="?page=<%= i %>"><%= i %></a>
		<% } %>
		<% if (end != tPage) { %>
			<a class = "pageLink"  href="?page=<%= end + 1 %>">▶</a>
			<a class = "pageLink"  href="?page=<%= tPage %>">≫</a>
		<% } %>
	</div>

<!-- 현재 페이지를 중심으로 좌우로 5페이지를 보여주는 방식
	<%
	if (tempPage <= 4)
		tempPage = 5;
	else if (tempPage > tPage-4)
		tempPage = tPage-4;
	%>
	
	<a class = "a pageLink" href="?page=1"> 첫페이지 </a>
	
	<% for (int i = tempPage-4; i <= tempPage+4; i++) {%>
		<a class = "<%= i == cPage? "cPage" : "a pageLink" %>"  href="?page=<%= i %>"> <%= i %> </a>
	<% }%>

	<a class = "a pageLink" href="?page=<%= tPage %>"> 끝페이지 </a>
-->	


	<h3><a class = "a pageLink" href="/home/main">메인 화면</a></h3>
</body>
</html>