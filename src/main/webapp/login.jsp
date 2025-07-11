<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/Login" method="post">
    <label>ユーザー名:</label>
    <input type="text" name="username"><br><br>
    <label>パスワード：</label>
    <input type="password" name="password"><br><br>
    <button type="submit">登录</button><br><br>
</form>
	
</body>
</html>