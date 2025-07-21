<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.SevenGroup.todolist.model.User" %>

<%@ page session="true" %>

<%
    // Servlet 预先设置的用户列表
    List<User> userList = (List<User>) request.getAttribute("userList");


    // 当前登录用户 ID，作为创建者
    String currentUserId = session.getAttribute("userId").toString();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>チーム作成</title>
    <style>
    body {
        font-family: 'Segoe UI', sans-serif;
        background-color: #f0f2f5;
        margin: 0;
        padding: 40px;
        display: flex;
        justify-content: center;
    }
    .card {
        background-color: white;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
        padding: 30px 40px;
        width: 600px;
    }
    h2 {
        color: #2b7bb9;
        margin-bottom: 20px;
        text-align: center;
    }
    .form-section {
        margin-bottom: 20px;
    }
    input[type="text"], textarea {
        width: 100%;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        background-color: #fff;
        box-sizing: border-box;
        font-size: 14px;
    }
    textarea {
        resize: vertical;
    }
    .member-list {
        max-height: 200px;
        overflow-y: auto;
        border: 1px solid #ddd;
        padding: 10px;
        border-radius: 4px;
        background-color: #fcfcfc;
    }
    .member-list label {
        display: block;
        padding: 6px;
        border-bottom: 1px solid #eee;
        font-size: 14px;
    }
    .submit-btn {
        width: 100%;
        padding: 10px;
        background-color: #2b7bb9;
        color: white;
        border: none;
        border-radius: 4px;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    .submit-btn:hover {
        background-color: #1a5c8a;
    }
</style>
</head>
<body>
	 <div class="card">
	
    <h2>新しいチームを作成</h2>

    <form action="addteam" method="post">
        <div class="form-section">
            <label for="teamName">チーム名：</label><br>
            <input type="text" name="teamName" id="teamName" required>
        </div>

        <div class="form-section">
            <label for="description">チーム紹介（任意）：</label><br>
            <textarea name="description" id="description" rows="4"></textarea>
        </div>

        <div class="form-section">
            <label>メンバーを選択：</label><br>
            <div class="member-list">
            
                <%
                int currentId = Integer.parseInt(currentUserId);
            	
                for (User u : userList) {
                	
                	if (u.getId() != currentId) {

                   
                %>
                    <label>
                        <input type="checkbox" name="members" value="<%= u.getId() %>">
                        <%= u.getName() %>（<%= u.getUsername() %>）
                    </label>
                <% 
                }
                	} 
                	%>
            </div>
        </div>

        <input type="submit" value="チーム作成" class="submit-btn">
    </form>
	</div>
</body>
</html>