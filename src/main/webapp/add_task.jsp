<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>タスク追加</title>
<style>
    body {
        margin: 0;
        padding: 0;
        background-color: #f6f8fa;
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .task-card {
        background-color: #ffffff;
        padding: 35px;
        border-radius: 12px;
        box-shadow: 0 6px 18px rgba(0,0,0,0.08);
        width: 400px;
    }

    .task-card h1 {
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

    .buttons {
        display: flex;
        justify-content: space-between;
    }

    button {
        padding: 10px 18px;
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
    }

    .message {
        margin-top: 15px;
        color: red;
        text-align: center;
        font-size: 14px;
    }
</style>
</head>
<body>
    <div class="task-card">
        <h1>新しいタスクの追加</h1>

        <%
            String contextPath = request.getContextPath();
        %>

        <form action="<%= contextPath %>/tasks/add" method="post">
            <label>タイトル：</label>
            <input type="text" name="title" required>

            <label>説明：</label>
            <textarea name="description" rows="4" cols="50"></textarea>

            <div class="buttons">
                <button type="submit">保存</button>
                <a href="<%= contextPath %>/index.jsp">
                    <button type="button">キャンセル</button>
                </a>
            </div>
        </form>

        <%
            String warningMessage = (String) request.getAttribute("warningMessage");
            if (warningMessage != null && !warningMessage.isEmpty()) {
        %>
        <div class="message"><%= warningMessage %></div>
        <% } %>
    </div>
</body>
</html>