<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Task</title>
</head>
<body>
<h1>Add New Task</h1>
<%
        String contextPath = request.getContextPath();
    %>

    <form action="<%= contextPath %>/tasks/add" method="post">
        <label>タイトル:</label>
        <input type="text" name="title" required><br><br>
        
        <label>説明:</label><br>
        <textarea name="description" rows="4" cols="50"></textarea><br><br>
        
        <button type="submit">保存</button>
        <a href="<%= contextPath %>/index.jsp">
        <button type="button">キャンセル</button>
        </a>
    </form>
    
    <%
    String warningMessage = (String) request.getAttribute("warningMessage");
    if (warningMessage != null && !warningMessage.isEmpty()) {
	%>
    <p><%= warningMessage %></p>
	<%
    	}
	%>

</body>
</html>