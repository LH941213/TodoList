<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
</head>
<body>
<h1 style="color: red;">Error</h1>
    <p>${errorMessage}</p>
    <a href="${pageContext.request.contextPath}/tasks/list"><button>Back to Task List</button></a>
</body>
</html>