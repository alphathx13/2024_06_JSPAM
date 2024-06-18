<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int loginMemberNumber = (int) request.getAttribute("loginMemberNumber");
	String loginMemberId = (String) request.getAttribute("loginMemberId");
%>
    
<% if (loginMemberNumber == -1) { %>
	<button class="logout" type="button" onclick="location.href='/member/join'">회원가입</button>
	<button class="logout" type="button" onclick="location.href='/member/login'">로그인</button>
<% } 
else { %>
	<div> <%= loginMemberId %>님 환영합니다. <button class="login" type="button" onclick="location.href='/member/logout'">로그아웃</button></div>
<% } %>