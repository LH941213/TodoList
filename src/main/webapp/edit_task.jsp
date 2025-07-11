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
 <%
    Task task = (Task) request.getAttribute("task");
    String contextPath = request.getContextPath();
%>
 
    <form action="<%= contextPath %>/tasks/edit" method="post">
        <input type="hidden" name="id" value="<%= task.getId() %>">
        <input type="hidden" name="createdAt" value="<%= task.getCreatedAt() %>">
        
        <label>タイトル:</label>
        <input type="text" name="title" value="<%= task.getTitle() %>" required><br><br>
        
        <label>説明:</label><br>
        <textarea name="description" rows="4" cols="50"><%= task.getDescription() %></textarea><br><br>
        
        <label>
            <input type="checkbox" name="isCompleted" <%= task.isCompleted() ? "checked" : "" %>>
            完了としてマーク
        </label><br><br>
        
        <button type="submit">アップデート</button>
        <a href="<%= contextPath %>/index.jsp">
        <button type="button">キャンセル</button>
        </a>
    </form>
      <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null && !errorMessage.isEmpty()) {
	%>
    <p><%= errorMessage %></p>
	<%
    	}
	%>
    
</body>
</html>