<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
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

    .register-card {
        background-color: #ffffff;
        padding: 35px;
        border-radius: 12px;
        box-shadow: 0 6px 18px rgba(0,0,0,0.08);
        width: 360px;
    }

    .register-card h2 {
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
    input[type="password"],
    input[type="email"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 6px;
        font-size: 14px;
    }

    button[type="submit"] {
        width: 100%;
        padding: 12px;
        background-color: #28a745;
        color: white;
        border: none;
        border-radius: 6px;
        font-size: 15px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    button[type="submit"]:hover {
        background-color: #218838;
    }

    .link {
        display: block;
        text-align: center;
        margin-top: 15px;
        color: #007bff;
        text-decoration: none;
        font-size: 14px;
    }

    .link:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
    <div class="register-card">
        <h2>アカウント作成</h2>
        <form action="${pageContext.request.contextPath}/Register" method="post">
            <label>氏名：</label>
            <input type="text" name="name" required>

            <label>ユーザー名：</label>
            <input type="text" name="username" required>

            <label>パスワード：</label>
            <input type="password" name="password" required>

            <label>メールアドレス：</label>
            <input type="email" name="email" required>

            <button type="submit">登録</button>
        </form>
        <a href="login.jsp" class="link">すでにアカウントをお持ちですか？ログイン</a>
    </div>
</body>
</html>