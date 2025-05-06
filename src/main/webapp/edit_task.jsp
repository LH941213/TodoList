<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Edit Task</title>
</head>
<body>
 <h1>Edit Task</h1>
    <form action="edit" method="post">
        <input type="hidden" name="id" value="${task.id}">
        <input type="hidden" name="createdAt" value="${task.createdAt}">
        
        <label>Title*:</label>
        <input type="text" name="title" value="${task.title}" required><br><br>
        
        <label>Description:</label><br>
        <textarea name="description" rows="4" cols="50">${task.description}</textarea><br><br>
        
        <label>
            <input type="checkbox" name="isCompleted" ${task.completed ? 'checked' : ''}>
            Mark as completed
        </label><br><br>
        
        <button type="submit">Update</button>
        <a href="${pageContext.request.contextPath}/tasks/list"><button type="button">Cancel</button></a>
    </form>
    
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
</body>
</html>