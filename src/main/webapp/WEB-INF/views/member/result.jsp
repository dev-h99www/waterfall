<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<h3 align="center">${ sessionScope.loginMember }님 환영합니다.</h3>
		<button onclick="location.href='logout'">로그아웃</button>
</body>
</html>