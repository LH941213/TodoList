<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>エラー</title>
<style>
    body {
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        background-color: #fff8f8;
        padding: 40px;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    h1 {
        color: #dc3545;
        font-size: 28px;
        margin-bottom: 20px;
    }

    p {
        font-size: 16px;
        color: #333;
        margin-bottom: 30px;
    }

    .btn-back {
        padding: 10px 20px;
        background-color: #0078D4;
        color: white;
        border: none;
        border-radius: 6px;
        font-size: 15px;
        text-decoration: none;
    }

    .btn-back:hover {
        background-color: #005fa3;
    }
</style>
</head>
<body>
    <h1>エラーが発生しました</h1>
    <p>${errorMessage}</p>
    <a href="${pageContext.request.contextPath}/tasks/list" class="btn-back">タスクリストへ戻る</a>
</body>
</html>