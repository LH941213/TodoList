<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f0f2f5;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .login-card {
        background-color: #ffffff;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        width: 320px;
    }

    .login-card h2 {
        margin-bottom: 20px;
        text-align: center;
        color: #333;
    }

    label {
        display: block;
        margin-bottom: 8px;
        font-weight: bold;
        color: #555;
    }

    input[type="text"],
    input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 18px;
        border: 1px solid #ccc;
        border-radius: 6px;
        font-size: 14px;
    }

    button[type="submit"] {
        width: 100%;
        padding: 12px;
        background-color: #0078D4;
        color: white;
        border: none;
        border-radius: 6px;
        font-size: 15px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    button[type="submit"]:hover {
        background-color: #005fa3;
    }
</style>
</head>
<body>
    <div class="login-card">
        <h2>ログイン</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <label>ユーザー名：</label>
            <input type="text" name="username" required>

            <label>パスワード：</label>
            <input type="password" name="password" required>

            <button type="submit">ログイン</button>
        </form>
    </div>
</body>
</html>