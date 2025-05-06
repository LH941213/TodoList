<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Task</title>
</head>
<body>
<h1>Add New Task</h1>
    <form action="${pageContext.request.contextPath}/tasks/add" method="post">
        <label>Title*:</label>
        <input type="text" name="title" required><br><br>
        
        <label>Description:</label><br>
        <textarea name="description" rows="4" cols="50"></textarea><br><br>
        
        <button type="submit">Save</button>
        <a href="${pageContext.request.contextPath}/tasks/list"><button type="button">Cancel</button></a>
    </form>
    
    <c:if test="${not empty warningMessage}">
        <p style="color: orange;">${warningMessage}</p>
    </c:if>
</body>
</html>