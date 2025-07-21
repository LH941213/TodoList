<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.SevenGroup.todolist.model.Task" %>

<!DOCTYPE html>
<html>
<head>
<title>タスク編集</title>
<style>
    body {
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f6f8fa;
        padding: 40px;
        display: flex;
        justify-content: center;
    }

    .edit-card {
        background-color: #ffffff;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 6px 14px rgba(0, 0, 0, 0.1);
        width: 480px;
    }

    .edit-card h1 {
        text-align: center;
        margin-bottom: 25px;
        color: #333;
    }

    label {
        display: block;
        margin-bottom: 6px;
        font-weight: bold;
        color: #555;
    }

    input[type="text"],
    textarea {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 6px;
        font-size: 14px;
        resize: vertical;
    }

    input[type="checkbox"] {
        margin-right: 8px;
    }

    button {
        padding: 10px 20px;
        border: none;
        border-radius: 6px;
        font-size: 15px;
        cursor: pointer;
    }

    button[type="submit"] {
        background-color: #0078D4;
        color: white;
    }

    button[type="button"] {
        background-color: #6c757d;
        color: white;
        margin-left: 10px;
    }

    .error-message {
        color: red;
        text-align: center;
        margin-top: 15px;
        font-size: 14px;
    }
</style>
</head>
<body>
    <div class="edit-card">
        <h1>タスクを編集する</h1>
        <%
            Task task = (Task) request.getAttribute("task");
            String contextPath = request.getContextPath();
        %>

        <form action="<%= contextPath %>/edittask" method="post">
            <input type="hidden" name="id" value="<%= task.getId() %>">
            <input type="hidden" name="createdAt" value="<%= task.getCreatedAt() %>">

            <label>タイトル：</label>
            <input type="text" name="title" value="<%= task.getTitle() %>" required>

            <label>詳細：</label>
            <textarea name="description" rows="4"><%= task.getDescription() %></textarea>

            <label>
                <input type="checkbox" name="isCompleted" <%= task.isCompleted() ? "checked" : "" %>>
                完了済みにする
            </label>

            <div style="margin-top: 20px;">
                <button type="submit">更新</button>
                <a href="<%= contextPath %>/index.jsp">
                    <button type="button">キャンセル</button>
                </a>
            </div>
        </form>

        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
        <div class="error-message"><%= errorMessage %></div>
        <% } %>
    </div>
</body>
</html>