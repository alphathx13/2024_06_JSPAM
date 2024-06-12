<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String) request.getAttribute("id");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article Detail</title>
<script type="text/javascript">
   alert("<%= id %>번 게시글을 삭제하였습니다.");
   location.href='list'; 
</script>
</head>
<body>


	
</body>
</html>