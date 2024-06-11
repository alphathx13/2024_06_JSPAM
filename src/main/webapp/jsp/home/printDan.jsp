<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String inputDan = request.getParameter("dan");
	String inputLimit = request.getParameter("limit");
	String inputColor = request.getParameter("color");
	
	if (inputDan == null || inputDan == "")
		inputDan = "1";
	if (inputLimit == null || inputLimit == "")
		inputLimit = "1";
	if (inputColor == null || inputColor == "") 
		inputColor = "black";

	int dan = Integer.parseInt(inputDan);
	int limit = Integer.parseInt(inputLimit);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단</title>
</head>
<body style = "color : <%= inputColor %>">
	<h1>== <%= dan %>단 ==</h1>
	<% for(int i = 1; i <= limit; i++) { %>
		<div><%= dan %> * <%= i %> = <%= dan*i %></div>
	<% } %>
	
</body>
</html>