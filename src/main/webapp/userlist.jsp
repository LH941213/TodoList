<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.SevenGroup.todolist.model.User" %>

<%
    List<User> userList = (List<User>) request.getAttribute("userlist");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ユーザー管理</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; margin: 40px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 12px; }
        th { background-color: #2b7bb9; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        form { display: inline; }
        .edit-form input, select { margin: 0 5px; padding: 4px; }
        .submit-btn { padding: 6px 12px; background-color: #2b7bb9; color: white; border: none; cursor: pointer; }
        .submit-btn:hover { background-color: #1d5d91; }
    </style>
</head>
<body>

    <h2>ユーザー管理ページ</h2>
	<form action="${pageContext.request.contextPath}/adduser" method="post" class="edit-form">
    <input type="text" name="username" placeholder="ユーザー名" required>
    <input type="text" name="name" placeholder="氏名" required>
    <input type="email" name="email" placeholder="メール" required>
    <select name="role">
        <option value="member">メンバー</option>
        <option value="admin">管理者</option>
    </select>
    <input type="password" name="password" placeholder="パスワード" required>
    <input type="submit" value="登録" class="submit-btn">
</form>
	
    <table>
        <tr>
            <th>ID</th>
            <th>ユーザー名</th>
            <th>氏名</th>
            <th>メール</th>
            <th>ロール</th>
            <th>編集</th>
        </tr>

        <%
            if (userList != null) {
                for (User u : userList) {
        %>
        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getUsername() %></td>
            <td><%= u.getName() %></td>
            <td><%= u.getEmail() != null ? u.getEmail() : "（未設定）" %></td>
            <td><%= u.getRole() != null ? u.getRole() : "member" %></td>
            <td>
                <form action="edituser" method="post" class="edit-form">
                    <input type="hidden" name="id" value="<%= u.getId() %>">
                    <input type="text" name="name" value="<%= u.getName() %>">
                    <input type="email" name="email" value="<%= u.getEmail() %>">
                    <select name="role">
                        <option value="member" <%= "member".equals(u.getRole()) ? "selected" : "" %>>メンバー</option>
                        <option value="admin" <%= "admin".equals(u.getRole()) ? "selected" : "" %>>管理者</option>
                    </select>
                    <input type="submit" value="更新" class="submit-btn">
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="6" style="text-align:center;">ユーザー情報が取得できませんでした。</td>
        </tr>
        <%
            }
        %>
    </table>
	<a href="${pageContext.request.contextPath}/tasklist">
    <button style="padding: 8px 16px; background-color: #ccc; border: none; cursor: pointer;">
        ホームへ戻る
    </button>
</a>
</body>
</html>