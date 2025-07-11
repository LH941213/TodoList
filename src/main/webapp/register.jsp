<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/Register" method="post">
    <label>用户名:</label>
    <input type="text" name="username" required>
    <label>密码:</label>
    <input type="password" name="password" required>
    <label>邮箱:</label>
    <input type="email" name="email" required>
    <button type="submit">注册</button>
</form>
	<a href="login.jsp">已有账户？点击登录</a>
	
</body>
</html>