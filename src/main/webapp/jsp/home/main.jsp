<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
	
	<%@ include file="/jsp/common/topBar.jsp" %>

	<br/></br>
	
	<button type="button" onclick="location.href='/article/list'">게시판</button>

</body>
</html>