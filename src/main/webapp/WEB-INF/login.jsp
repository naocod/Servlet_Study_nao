<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
	// 선언문(클래스 안, 클래스 정의, 함수 정의)
%>
<%
	// 스크립틀릿-JSP에서만 씀, 메소드 안
	// < %=wc % > JSP표현식
	Strig wc = (String) request.getAttribute("welcome");
	int[] numbers = (int[]) request.getAttribute("numbers");	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%=wc %>></h1>
	<%
		for(int i = 0;i < numbers.lenth; i++) {
	%>
		<div><%=numbers[i] %></div>
	<%
		}	
	%>			
	<form action="/auth/signin" method="post">
		<input type="text" name="username" placeholder="username">
		<input type="password" name="password" placeholder="password">
		<button type="submit">로그인</button>
	</form>

</body>
</html>