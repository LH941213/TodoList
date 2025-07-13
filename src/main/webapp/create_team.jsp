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
            margin: 30px;
        }
        h2 {
            color: #333;
        }
        .form-section {
            margin-bottom: 20px;
        }
        input[type="text"], textarea {
            width: 400px;
            padding: 5px;
        }
        .member-list {
            max-height: 200px;
            overflow-y: auto;
            border: 1px solid #ccc;
            padding: 10px;
            background-color: #f9f9f9;
        }
        .member-list label {
            display: block;
        }
        .submit-btn {
            padding: 8px 16px;
            background-color: #2b7bb9;
            color: white;
            border: none;
            cursor: pointer;
        }
        .submit-btn:hover {
            background-color: #1d5d91;
        }
    </style>
</head>
<body>

    <h2>新しいチームを作成</h2>

    <form action="CreateTeamServlet" method="post">
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

</body>
</html>